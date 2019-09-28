package com.yoite.planethelp.events

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.yoite.planethelp.BaseActivity
import com.yoite.planethelp.PlanetHelpApp
import com.yoite.planethelp.R
import com.yoite.planethelp.common.OnItemSelectedListener
import com.yoite.planethelp.event.EventActivity
import com.yoite.planethelp.events.repository.models.CategoryType
import com.yoite.planethelp.events.repository.models.EventModel
import com.yoite.planethelp.events.repository.models.GoalType
import com.yoite.planethelp.utils.*
import javax.inject.Inject


class EventsActivity: BaseActivity(), OnMapReadyCallback, EventsView {

    companion object {
        const val GPS_PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
        const val REQUEST_START_GPS = 0
    }

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private var hasGpsPermission: Boolean = false

    private var googleMap: GoogleMap? = null

    private lateinit var mapBtn: ImageView
    private lateinit var listBtn: ImageView
    private lateinit var voluntaerSwitch: SwitchCompat
    private lateinit var mapListContainer: ConstraintLayout

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var eventRecycler: RecyclerView
    private lateinit var eventAdapter: EventsAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter : EventsPresenterImpl

    @ProvidePresenter
    fun providePresenter() : EventsPresenterImpl = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        PlanetHelpApp.diManager.eventsComponent?.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_events)

        initToolbar()
        initGoogleMap()
        initControls()
        initBottomSheet()
    }

    // ===========================================================
    // ToolBar
    // ===========================================================

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.events_menu, menu)
        return true
    }

    // ===========================================================
    // Map
    // ===========================================================

    private fun initGoogleMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {

            override fun onLocationChanged(location: Location?) {
                if (googleMap != null && location != null) {
                    googleMap?.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            LatLng(
                                location.latitude,
                                location.longitude
                            ), 13.0F
                        )
                    )
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {
            }
        }

        checkPermission()
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            googleMap = it
            it.setMapStyle(MapStyleOptions.loadRawResourceStyle(this@EventsActivity, R.raw.style_json))
            checkPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocationManager() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            var location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }

            googleMap?.isMyLocationEnabled = true
            if (googleMap != null && location != null) {
                googleMap?.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            location.latitude,
                            location.longitude
                        ), 13.0F
                    )
                )
            }
        }
    }

    // ===========================================================
    // Permissions
    // ===========================================================

    private fun checkPermission() {
        hasGpsPermission = checkPermissionGranted(this, GPS_PERMISSION)
        if (!hasGpsPermission) {
            requestsPermission(this, GPS_PERMISSION, REQUEST_START_GPS)
        } else {
//            presenter.onPermissionReceived(hasGpsPermission)
            initLocationManager()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_START_GPS) {
            onRequestsPermissionsResult(grantResults, object : RPResultListener {
                override fun onPermissionGranted() {
                    if (checkPermissionGranted(this@EventsActivity, GPS_PERMISSION)) {
                        initLocationManager()
                    } else {
//                        presenter.onPermissionReceived(false)
                    }
                }

                override fun onPermissionDenied() {
//                    presenter.onPermissionReceived(false)
                }
            })
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // ===========================================================
    // BottomSheet
    // ===========================================================

    private fun initBottomSheet() {
        val layout = findViewById<ConstraintLayout>(R.id.events_activity_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(layout)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        onRenderFinished(layout, Runnable {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)

            val position: IntArray = intArrayOf(0, 0)
            mapListContainer.getLocationOnScreen(position)

            layout.maxHeight = size.y - position.last() - mapListContainer.height - dpToPx(8.0F, this)

            bottomSheetBehavior.peekHeight = size.y - position.last() - mapListContainer.height - dpToPx(8.0F, this)
        })
    }

    // ===========================================================
    // Controls
    // ===========================================================

    private fun initControls() {
        mapBtn = findViewById(R.id.activity_events_map_selector)
        listBtn = findViewById(R.id.activity_events_list_selector)
        voluntaerSwitch = findViewById(R.id.activity_events_only_volunteer_switch)
        mapListContainer = findViewById(R.id.activity_events_map_list_selectors_container)

        mapBtn.setOnClickListener {
            presenter.onMapAction()
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                presenter.onMapAction()
            }
        }

        listBtn.setOnClickListener {
            presenter.onListAction()
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
                presenter.onListAction()
            }
        }

        voluntaerSwitch.setOnCheckedChangeListener { _, b ->
            presenter.onOnlyVoluntareAction(b)
        }

        eventAdapter = EventsAdapter(object : OnItemSelectedListener<EventModel> {
            override fun onItemSelected(t: EventModel) {
                presenter.onEventAction(t)
            }
        })

        eventRecycler = findViewById(R.id.events_activity_event_list)
        eventRecycler.layoutManager = LinearLayoutManager(this)
        eventRecycler.adapter = eventAdapter
    }

    // ===========================================================
    // EventsView
    // ===========================================================

    override fun showEventsList() {
        bottomSheetBehavior.peekHeight = mapListContainer.bottom
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        mapBtn.setImageResource(R.drawable.ic_map_switch)
        listBtn.setImageResource(R.drawable.ic_switch_list_selected)
    }

    override fun showMap() {
        bottomSheetBehavior.peekHeight = 0
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        mapBtn.setImageResource(R.drawable.ic_switch_map_selected)
        listBtn.setImageResource(R.drawable.ic_switch_list)
    }

    override fun showEvents(events: List<EventModel>) {
        eventAdapter.eventsList.clear()
        eventAdapter.eventsList.addAll(events)
        eventAdapter.notifyDataSetChanged()

        supportActionBar!!.title = "${events.size} События"

        if (googleMap != null) {
            events.forEach {

                val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val v = inflater.inflate(R.layout.marker_layout, null)

                val title = v.findViewById<TextView>(R.id.marker_title)
                val goal = v.findViewById<TextView>(R.id.marker_goal)
                val likes = v.findViewById<TextView>(R.id.marker_like_count)
                val image = v.findViewById<ImageView>(R.id.item_event_image)

                goal.text = when(it.goal) {
                    GoalType.Volunteering -> "Волонтерство"
                    GoalType.Money -> "Деньги"
                    else -> ""
                }
                likes.text = it.likeCount.toString()

                when(it.category) {
                    CategoryType.Ecology -> {
                        title.text = "Экология"
                        image.setImageResource(R.mipmap.ic_ecology)
                    }
                    CategoryType.Animals -> {
                        title.text = "Животные"
                        image.setImageResource(R.mipmap.ic_panda)
                    }
                    CategoryType.Emergency -> {
                        title.text = "Опасности"
                        image.setImageResource(R.mipmap.ic_emegrency)
                    }
                    CategoryType.Health -> {
                        title.text = "Здоровье"
                        image.setImageResource(R.mipmap.ic_blood)
                    }
                    CategoryType.Homeless -> {
                        title.text = "Общество"
                        image.setImageResource(R.mipmap.ic_stuff)
                    }
                    CategoryType.Charity -> {
                        title.text = "Волонтерство"
                        image.setImageResource(R.mipmap.ic_charity)
                    }
                    CategoryType.Children -> {
                        title.text = "Дети"
                        image.setImageResource(R.mipmap.ic_stuff)
                    }
                    else -> {
                        title.text = ""
                    }
                }

                val b = loadBitmapFromView(v)

                if (b != null) {
                    googleMap!!.addMarker(
                        MarkerOptions()
                            .title(it.caption)
                            .icon(BitmapDescriptorFactory.fromBitmap(b))
                            .position(LatLng(it.lat, it.long))
                    )
                }
            }
        }
    }

    override fun showFilters() {

    }

    override fun showEvent(event: EventModel) {
        val intent = Intent(this, EventActivity::class.java).apply{
            putExtra(EventActivity.MODEL_TAG, event)
        }
        startActivity(intent)
    }
}
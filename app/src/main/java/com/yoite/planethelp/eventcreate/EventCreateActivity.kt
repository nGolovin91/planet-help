package com.yoite.planethelp.eventcreate

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.PopupWindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.BaseActivity
import com.yoite.planethelp.R
import com.yoite.planethelp.common.OnItemSelectedListener
import com.yoite.planethelp.events.repository.models.CategoryType


class EventCreateActivity : BaseActivity() {

    private lateinit var titleInput: EditText
    private lateinit var categoryPicker: TextView
    private lateinit var addPhotoControl: ConstraintLayout
    private lateinit var whereInput: EditText
    private lateinit var typeRadioGroup: RadioGroup
    private lateinit var peopleCountInput: EditText
    private lateinit var targetInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        initToolBar()
        initControls()
    }

    private fun initToolBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setTitle("Создаем новое событие")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initControls() {
        titleInput = findViewById(R.id.create_event_title_input)
        categoryPicker = findViewById(R.id.create_event_category_input)
        addPhotoControl = findViewById(R.id.create_event_add_photo)
        whereInput = findViewById(R.id.create_event_where_input)
        typeRadioGroup = findViewById(R.id.create_event_event_type_picker)
        peopleCountInput = findViewById(R.id.create_event_people_count_input)
        targetInput = findViewById(R.id.create_event_target_input)

        categoryPicker.setOnClickListener {
            showCategories()
        }
    }

    private fun showCategories() {
        val v = LayoutInflater.from(this).inflate(R.layout.popup_categories, null)
        val recyclerView = v.findViewById<RecyclerView>(R.id.popup_categories_recycler)

        val pw = PopupWindow(v,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val adapter = CategoriesAdapter(object : OnItemSelectedListener<CategoryType>{
            override fun onItemSelected(t: CategoryType) {
                categoryPicker.text = when (t) {
                    CategoryType.Ecology -> "Экология"
                    CategoryType.Animals -> "Животные"
                    CategoryType.Emergency -> "Опасности"
                    CategoryType.Health -> "Здоровье"
                    CategoryType.Homeless -> "Общество"
                    CategoryType.Charity -> "Волонтерство"
                    CategoryType.Children -> "Дети"
                    else -> ""
                }
                pw.dismiss()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        pw.isOutsideTouchable = true
        PopupWindowCompat.setOverlapAnchor(pw, true)
        PopupWindowCompat.showAsDropDown(pw, categoryPicker, 0, 0, Gravity.NO_GRAVITY)
    }

}
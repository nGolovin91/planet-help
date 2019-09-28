package com.yoite.planethelp.event

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.BaseActivity
import com.yoite.planethelp.R
import com.yoite.planethelp.events.repository.models.EventModel
import com.yoite.planethelp.events.repository.models.GoalType
import com.yoite.planethelp.utils.onRenderFinished


class EventActivity : BaseActivity() {

    companion object {
        const val MODEL_TAG = "EventActivity.EventModel"
    }

    private lateinit var title: TextView
    private lateinit var owner: TextView
    private lateinit var likeCount: TextView
    private lateinit var targetDescription: TextView
    private lateinit var targetSum: TextView
    private lateinit var targetProgress: View
    private lateinit var totalProgress: View
    private lateinit var targetSumTitle: TextView

    private lateinit var photoRecycler: RecyclerView
    private lateinit var achivmentReccycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        title = findViewById(R.id.event_title)
        owner = findViewById(R.id.event_who)
        likeCount = findViewById(R.id.event_likes)
        targetDescription = findViewById(R.id.event_target_description)
        targetSum = findViewById(R.id.event_target_sum)
        targetProgress = findViewById(R.id.event_target_sum_progress)
        totalProgress = findViewById(R.id.event_target_sum_total)
        targetSumTitle = findViewById(R.id.event_target_sum_title)

        photoRecycler = findViewById(R.id.event_photo_recycler)
        achivmentReccycler = findViewById(R.id.event_achivments_recycler)

        val eventModel = (intent.getSerializableExtra(MODEL_TAG) as EventModel)

        title.text = eventModel.caption
        owner.text = eventModel.advertModel.name
        likeCount.text = eventModel.likeCount.toString()
        targetDescription.text = eventModel.description

        when (eventModel.goal) {
            GoalType.Volunteering -> {
                targetProgress.visibility = View.GONE
                totalProgress.visibility = View.GONE
                targetSum.visibility = View.GONE
                targetSumTitle.visibility = View.GONE
            }
            GoalType.Money -> {
                targetProgress.visibility = View.VISIBLE
                totalProgress.visibility = View.VISIBLE
                targetSum.visibility = View.VISIBLE
                targetSumTitle.visibility = View.VISIBLE

                if (eventModel.goalTarget != 0) {
                    onRenderFinished(totalProgress, Runnable {
                        targetProgress.minimumWidth = totalProgress.width * (eventModel.goalCount / eventModel.goalTarget)
                        targetProgress.invalidate()
                    })
                } else {
                    onRenderFinished(totalProgress, Runnable {
                        targetProgress.minimumWidth = totalProgress.width * 0
                        targetProgress.invalidate()
                    })
                }

                targetSum.text = "${eventModel.goalCount} из ${eventModel.goalTarget} рублей"
            }
            else -> {
                targetProgress.visibility = View.GONE
                totalProgress.visibility = View.GONE
                targetSum.visibility = View.GONE
                targetSumTitle.visibility = View.GONE
            }
        }

        val adapter = GiftAdapter()

        achivmentReccycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        achivmentReccycler.adapter = adapter

        val list = emptyList<GiftModel>().toMutableList()
        list.add(GiftModel(500, "Стикерпак Вконтакте"))
        list.add(GiftModel(1500, "Футболка с пандой"))

        adapter.gifts = list
        adapter.notifyDataSetChanged()

        val photoAdapter = PhotoAdapter()

        photoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        photoRecycler.adapter = photoAdapter
    }

}
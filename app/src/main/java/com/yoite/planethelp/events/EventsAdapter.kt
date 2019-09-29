package com.yoite.planethelp.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.R
import com.yoite.planethelp.common.OnItemSelectedListener
import com.yoite.planethelp.events.repository.models.CategoryType
import com.yoite.planethelp.events.repository.models.EventModel
import com.yoite.planethelp.events.repository.models.GoalType
import com.yoite.planethelp.utils.onRenderFinished


class EventsAdapter(
    private var selectedListener: OnItemSelectedListener<EventModel>
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    val eventsList: MutableList<EventModel> = emptyList<EventModel>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventsList[position], selectedListener)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.item_event_image)
        private val title = itemView.findViewById<TextView>(R.id.item_event_title)
        private val goal = itemView.findViewById<TextView>(R.id.item_event_goal)
        private val description = itemView.findViewById<TextView>(R.id.item_event_description)
        private val likeCount = itemView.findViewById<TextView>(R.id.item_event_like_count)
        private val goalTotalMoney = itemView.findViewById<View>(R.id.item_event_goal_money_total)
        private val goalProgressMoney = itemView.findViewById<View>(R.id.item_event_goal_money_progress)

        fun bind(model: EventModel, listener : OnItemSelectedListener<EventModel>) {

            title.text = model.caption

            when (model.goal) {
                GoalType.Volunteering -> {
                    goal.text = "Волонтерство"
                    goalTotalMoney.visibility = View.GONE
                    goalProgressMoney.visibility = View.GONE
                }
                GoalType.Money -> {
                    goal.text = "Необходимые деньги"
                    goalTotalMoney.visibility = View.VISIBLE
                    goalProgressMoney.visibility = View.VISIBLE
                    if (model.goalTarget != 0) {
                        onRenderFinished(goalTotalMoney, Runnable {
                            goalProgressMoney.minimumWidth = (goalTotalMoney.width.toFloat() * (model.goalCount.toFloat() / model.goalTarget.toFloat())).toInt()
                            goalProgressMoney.invalidate()
                        })
                    } else {
                        onRenderFinished(goalTotalMoney, Runnable {
                            goalProgressMoney.minimumWidth = goalTotalMoney.width * 0
                            goalProgressMoney.invalidate()
                        })
                    }
                }
                else -> {
                    goal.text = ""
                    goalTotalMoney.visibility = View.GONE
                    goalProgressMoney.visibility = View.GONE
                }
            }

            when (model.category) {
                CategoryType.Ecology -> {
                    image.setImageResource(R.mipmap.ic_ecology)
                }
                CategoryType.Animals -> {
                    image.setImageResource(R.mipmap.ic_panda)
                }
                CategoryType.Emergency -> {
                    image.setImageResource(R.mipmap.ic_emegrency)
                }
                CategoryType.Health -> {
                    image.setImageResource(R.mipmap.ic_blood)
                }
                CategoryType.Homeless -> {
                    image.setImageResource(R.mipmap.ic_stuff)
                }
                CategoryType.Charity -> {
                    image.setImageResource(R.mipmap.ic_charity)
                }
                CategoryType.Children -> image.setImageResource(R.mipmap.ic_stuff)
                CategoryType.Undefined -> image.setImageResource(R.mipmap.ic_stuff)
            }

            likeCount.text = model.likeCount.toString()
            description.text = model.description

            itemView.setOnClickListener {
                listener.onItemSelected(model)
            }
        }
    }
}
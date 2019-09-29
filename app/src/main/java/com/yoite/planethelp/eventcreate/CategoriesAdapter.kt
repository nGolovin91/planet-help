package com.yoite.planethelp.eventcreate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.R
import com.yoite.planethelp.common.OnItemSelectedListener
import com.yoite.planethelp.events.repository.models.CategoryType


class CategoriesAdapter(
    private val selectedListener: OnItemSelectedListener<CategoryType>
): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int {
        return CategoryType.values().size - 1
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(selectedListener, CategoryType.values()[position])
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameText = itemView.findViewById<TextView>(R.id.item_category_name)

        fun bind(listener : OnItemSelectedListener<CategoryType>, category: CategoryType) {
            nameText.text = when (category) {
                CategoryType.Ecology -> "Экология"
                CategoryType.Animals -> "Животные"
                CategoryType.Emergency -> "Опасности"
                CategoryType.Health -> "Здоровье"
                CategoryType.Homeless -> "Общество"
                CategoryType.Charity -> "Волонтерство"
                CategoryType.Children -> "Дети"
                else -> ""
            }

            itemView.setOnClickListener {
                listener.onItemSelected(category)
            }
        }
    }
}
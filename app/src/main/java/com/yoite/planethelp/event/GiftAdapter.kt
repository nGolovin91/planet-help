package com.yoite.planethelp.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yoite.planethelp.R


class GiftAdapter: RecyclerView.Adapter<GiftAdapter.GiftHolder>() {

    var gifts: List<GiftModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftHolder {
        return GiftHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_gift, parent, false))
    }

    override fun getItemCount(): Int {
        return gifts.size
    }

    override fun onBindViewHolder(holder: GiftHolder, position: Int) {
        holder.onBind(gifts[position])
    }


    class GiftHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val giftSum = itemView.findViewById<TextView>(R.id.item_gift_sum)
        private val giftName = itemView.findViewById<TextView>(R.id.item_gift_name)

        fun onBind(giftModel: GiftModel) {
            giftSum.text = "${giftModel.sum} рублей"
            giftName.text = giftModel.gift
        }
    }
}
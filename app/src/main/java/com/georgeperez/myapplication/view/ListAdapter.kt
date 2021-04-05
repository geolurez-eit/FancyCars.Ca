package com.georgeperez.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.georgeperez.myapplication.R
import com.georgeperez.myapplication.model.Car
import com.georgeperez.myapplication.viewmodel.FancyCarsViewModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var carData: Car? = Car()
    private var allCars: Map<Car, String>? = mapOf()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.listitem_imageview)
        val name: TextView = itemView.findViewById(R.id.listitem_name_textview)
        val make: TextView = itemView.findViewById(R.id.listitem_make_textview)
        val model: TextView = itemView.findViewById(R.id.listitem_model_textview)
        val availability: TextView = itemView.findViewById(R.id.listitem_availability_textview)
        val card: CardView = itemView.findViewById(R.id.listitem_card)
        val buy: Button = itemView.findViewById(R.id.listitem_buy_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        carData = allCars?.toList()?.get(position)?.first
        holder.apply {
            name.text = carData?.name?.trim() ?: "No Data"
            make.text = carData?.make?.trim() ?: "No Data"
            model.text = carData?.model?.trim() ?: "No Data"
            availability.text = allCars?.toList()?.get(position)?.second?.trim()
                ?: holder.itemView.context.getString(R.string.availability_placeholder)
            Glide.with(this.itemView.context)
                .load(carData?.img)
                .placeholder(R.drawable.sample_car)
                .into(image)
            if (checkAvailability(position))
                buy.visibility = View.VISIBLE
            else
                buy.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return allCars?.toList()?.size ?: 0
    }

    private fun checkAvailability(position: Int): Boolean =
        (allCars?.toList()?.get(position)?.second == "In Dealership")

    fun update() {
        allCars = FancyCarsViewModel.allCarData
        notifyDataSetChanged()
    }

}
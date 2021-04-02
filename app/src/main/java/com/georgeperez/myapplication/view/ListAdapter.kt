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
import com.georgeperez.myapplication.model.CarResponse
import com.georgeperez.myapplication.viewmodel.FancyCarsViewModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var carData: CarResponse.Car? = CarResponse.Car()
    private var availabilityData: HashMap<Int, String>? = HashMap()
    private var carList: List<CarResponse.Car>? = listOf()

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
        carData = carList?.get(position)
        availabilityData = FancyCarsViewModel.availability.value
        holder.apply {
            name.text = carData?.name?.trim() ?: "No Data"
            make.text = carData?.make?.trim() ?: "No Data"
            model.text = carData?.model?.trim() ?: "No Data"
            availability.text = availabilityData?.get(carData?.id ?: 0)
            Glide.with(this.itemView.context)
                .load(carData?.img)
                .placeholder(R.drawable.sample_car)
                .into(image)
            if (checkAvailability(carData?.id ?: 0))
                buy.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return carList?.size ?: 0
    }

    private fun checkAvailability(id: Int): Boolean =
        (availabilityData?.get(id) == "In Dealership")

    fun update() {
        //carList = FancyCarsViewModel.cars.value?.list
        //availabilityData = FancyCarsViewModel.availability.value

        carList = listOf(
            CarResponse.Car(1, "", "Fast Car", "Dodge", "Viper", 1990),
            CarResponse.Car(2, "", "Red Car", "Toyota", "Camry", 1992),
            CarResponse.Car(3, "", "Blue Car", "Ford", "F150", 2000),
            CarResponse.Car(4, "", "Green Car", "GM", "Laughter", 1990),
            CarResponse.Car(5, "", "Yellow Car", "Honda", "Civic", 2020),
            CarResponse.Car(6, "", "Gold Car", "Chrysler", "Pacifica", 1995),
            CarResponse.Car(7, "", "Silver Car", "Jeep", "Cherokee", 1990),
            CarResponse.Car(8, "", "Shiny Car", "Hummer", "Viper", 2006),
            CarResponse.Car(9, "", "Black Car", "Dodge", "Caravan", 2005),
            CarResponse.Car(10, "", "White Car", "Nissan", "Express", 1990),
        )
        availabilityData?.put(1,"In Dealership")
        availabilityData?.put(2,"Out of Stock")
        availabilityData?.put(3,"Unavailable")
        availabilityData?.put(4,"Unavailable")
        availabilityData?.put(5,"Out of Stock")
        availabilityData?.put(6,"In Dealership")
        availabilityData?.put(7,"In Dealership")
        availabilityData?.put(8,"Out of Stock")
        availabilityData?.put(9,"Unavailable")
        availabilityData?.put(10,"In Dealership")

        notifyDataSetChanged()
    }

}
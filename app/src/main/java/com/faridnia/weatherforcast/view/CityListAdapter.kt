package com.faridnia.weatherforcast.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.model.forcastresponse.City
import kotlinx.android.synthetic.main.city_item_layout.view.*

class CityListAdapter(private var cityList: List<City>) :
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    var onItemClick: ((City) -> Unit)? = null

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityNameTextView: TextView = view.cityItemNameTextView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(cityList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item_layout, parent, false)
        return CityViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.cityNameTextView.text = cityList[position].name
    }

    fun update(list: List<City>) {
        this.cityList = list
        notifyDataSetChanged()
    }
}
package com.kendhammer.john.countries.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kendhammer.john.countries.R
import com.kendhammer.john.countries.model.Country
import com.kendhammer.john.countries.util.getProgressDrawable
import com.kendhammer.john.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updatteCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val flagImage = view.iv_flag
        private val countryCapital = view.tv_capital
        private val countryName = view.tv_name
        private val progress_drawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.countryName
            countryCapital.text = country.capital
            flagImage.loadImage(country.flag, progress_drawable)
        }
    }


}
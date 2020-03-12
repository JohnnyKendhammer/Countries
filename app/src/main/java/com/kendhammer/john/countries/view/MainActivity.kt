package com.kendhammer.john.countries.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kendhammer.john.countries.R
import com.kendhammer.john.countries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CountryViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.refresh()

        rvCountryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                rvCountryList.visibility = View.VISIBLE
                countriesAdapter.updatteCountries(it)
            }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            isError?.let {
                tv_list_error.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pb_loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    tv_list_error.visibility = View.GONE
                    rvCountryList.visibility = View.GONE
                }
            }
        })
    }
}

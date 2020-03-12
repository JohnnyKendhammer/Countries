package com.kendhammer.john.countries.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kendhammer.john.countries.di.DaggerApiComponent
import com.kendhammer.john.countries.model.CountriesService
import com.kendhammer.john.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CountryViewModel : ViewModel() {

    private val TAG = "ViewModel"

    @Inject
    lateinit var countriesService: CountriesService

    init {
        DaggerApiComponent.create().inject(this)
    }
    //Since ViewModel is using RxJava to get information from the service, we need to clear the connection
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService.getCountries()
                //Relegates processing to a new background thread
            .subscribeOn(Schedulers.newThread())
                //Result of processing is observed on MainThread
            .observeOn(AndroidSchedulers.mainThread())
                //Define what we do when we get the information
            .subscribeWith(object: DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(value: List<Country>?) {
                    countries.value = value
                    countryLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "Error: ${e?.message}")
                    countryLoadError.value = true
                    loading.value = false
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
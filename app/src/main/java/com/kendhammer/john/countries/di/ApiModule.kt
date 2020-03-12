package com.kendhammer.john.countries.di

import com.kendhammer.john.countries.model.CountriesAPI
import com.kendhammer.john.countries.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesAPI(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            //Transform json code into Kotlin code
            .addConverterFactory(GsonConverterFactory.create())
            //Transform data in type Country into an Observable variable
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }

    @Provides
    fun provideCountriesService(): CountriesService{
        return CountriesService()
    }
}
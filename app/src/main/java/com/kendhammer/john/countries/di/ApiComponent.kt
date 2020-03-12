package com.kendhammer.john.countries.di

import com.kendhammer.john.countries.model.CountriesService
import com.kendhammer.john.countries.viewmodel.CountryViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: CountryViewModel)
}
package com.example.countriesapp.data.di

import com.example.countriesapp.data.countries.remote.CountriesApi
import com.example.countriesapp.data.countries.repository.CountriesRepository
import com.example.countriesapp.data.states.remote.StatesApi
import com.example.countriesapp.data.states.repository.StatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://countriesnow.space/api/v0.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(apiCountries: CountriesApi): CountriesRepository {
        return CountriesRepository(apiCountries)
    }

    @Provides
    @Singleton
    fun provideStatesApi(retrofit: Retrofit): StatesApi {
        return retrofit.create(StatesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStatesRepository(apiStates: StatesApi): StatesRepository {
        return StatesRepository(apiStates)
    }
}
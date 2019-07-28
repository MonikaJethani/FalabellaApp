package com.monika.falabellaapp

import android.app.Application
import androidx.room.Room
import com.monika.falabellaapp.api.BeerCraftApi
import com.monika.falabellaapp.db.BeerDatabase
import com.monika.falabellaapp.repository.BeerRepository
import com.monika.falabellaapp.viewmodel.BeersListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App: Application() {

    //For the sake of simplicity, for now we use this instead of Dagger
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var beerApi: BeerCraftApi
        private lateinit var beerRepository: BeerRepository
        private lateinit var beerListViewModel: BeersListViewModel
        private lateinit var appDatabase: BeerDatabase

        fun injectBeerApi() = beerApi

        fun injectBeerListViewModel() = beerListViewModel

        fun injectBeerDao() = appDatabase.beerDao()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://starlord.hackerearth.com/")
            .build()

        beerApi = retrofit.create(BeerCraftApi::class.java)
        appDatabase = Room.databaseBuilder(applicationContext,
            BeerDatabase::class.java, "beer_database").build()

        beerRepository = BeerRepository(beerApi, appDatabase.beerDao())
        beerListViewModel = BeersListViewModel(beerRepository)
    }
}

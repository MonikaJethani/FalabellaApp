package com.monika.falabellaapp.api

import com.monika.falabellaapp.model.Beer
import io.reactivex.Observable
import retrofit2.http.GET

interface BeerCraftApi {

    @GET("beercraft")
    fun getBeers(): Observable<List<Beer>>

}
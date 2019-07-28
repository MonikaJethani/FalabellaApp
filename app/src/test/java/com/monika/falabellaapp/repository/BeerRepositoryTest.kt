package com.monika.falabellaapp.repository

import com.monika.falabellaapp.api.BeerCraftApi
import com.monika.falabellaapp.db.BeerDao
import com.monika.falabellaapp.model.Beer
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class BeerRepositoryTest {

    lateinit var beerRepository: BeerRepository
    lateinit var beerApi: BeerCraftApi
    lateinit var beerDao: BeerDao

    @Before
    fun setup() {
        beerApi = mock()
        beerDao = mock()
        `when`(beerDao.getBeers()).thenReturn(Single.just(emptyList()))
        beerRepository = BeerRepository(beerApi, beerDao)
    }

    @Test
    fun test_emptyCache_noDataOnApi_returnsEmptyList() {
        `when`(beerApi.getBeers()).thenReturn(Observable.just(emptyList<Beer>()))

        beerRepository.getBeers().test()
            .assertValue { it.isEmpty() }
    }

    @Test
    fun test_emptyCache_hasDataOnApi_returnsApiData() {
        `when`(beerApi.getBeers()).thenReturn(Observable.just(listOf(aRandomBeer())))

        beerRepository.getBeers().test()
            .assertValueCount(1)
            .assertValue { it.size == 1 }
    }


    fun aRandomBeer() = Beer("0.05","",1436,"Pub Beer","American Pale Lager",12.0f)
}
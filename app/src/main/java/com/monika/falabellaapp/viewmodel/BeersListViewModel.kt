package com.monika.falabellaapp.viewmodel

import com.monika.falabellaapp.repository.BeerRepository
import io.reactivex.Observable
import timber.log.Timber

class BeersListViewModel(private val beerRepository: BeerRepository) {

    fun getUsers(): Observable<BeersList> {
        //Create the data for your UI, the users lists and maybe some additional data needed as well
        return beerRepository.getBeers()
            .map {
                Timber.d("Mapping users to UIData...")
                BeersList(it, "List of Beers")
            }
            .onErrorReturn {
                Timber.d("An error occurred $it")
                BeersList(emptyList(), "An error occurred", it)
            }
    }
}

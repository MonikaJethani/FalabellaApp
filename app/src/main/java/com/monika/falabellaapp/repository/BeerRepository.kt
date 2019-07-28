package com.monika.falabellaapp.repository

import com.monika.falabellaapp.api.BeerCraftApi
import com.monika.falabellaapp.db.BeerDao
import com.monika.falabellaapp.model.Beer
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class BeerRepository(val userApi: BeerCraftApi, val userDao: BeerDao) {

    fun getBeers(): Observable<List<Beer>> {
        return Observable.concatArray(
            getBeersFromDb(),
            getBeersFromApi())
    }


    fun getBeersFromDb(): Observable<List<Beer>> {
        return userDao.getBeers().filter { it.isNotEmpty() }
            .toObservable()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from DB...")
            }
    }

    fun getBeersFromApi(): Observable<List<Beer>> {
        return userApi.getBeers()
            .doOnNext {
                Timber.d("Dispatching ${it.size} users from API...")
                storeBeersInDb(it)
            }
    }

    fun storeBeersInDb(users: List<Beer>) {
        Observable.fromCallable { userDao.insertAll(users) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${users.size} users from API in DB...")
            }
    }

}

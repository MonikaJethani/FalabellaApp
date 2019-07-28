package com.monika.falabellaapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.monika.falabellaapp.App

import com.monika.falabellaapp.R
import com.monika.falabellaapp.adapter.BeerListAdapter
import com.monika.falabellaapp.viewmodel.BeersList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_beer_list.*
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException

class BeerListFragment : MvvmFragment() {

    val userListViewModel = App.injectBeerListViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_beer_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        subscribe(userListViewModel.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("Received UIModel $it users.")
                showBeers(it)
            }, {
                Timber.w(it)
                showError()
            }))
    }

    fun showBeers(data: BeersList) {
        if (data.error == null) {
            beersList.adapter = BeerListAdapter(data.beers)
            beersList.layoutManager = LinearLayoutManager(context)
        } else if (data.error is ConnectException || data.error is UnknownHostException) {
            Timber.d("No connection, maybe inform user that data loaded from DB.")
        } else {
            showError()
        }
    }

    fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }

}

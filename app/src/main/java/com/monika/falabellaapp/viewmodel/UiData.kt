package com.monika.falabellaapp.viewmodel

import com.monika.falabellaapp.model.Beer

data class BeersList(val beers: List<Beer>, val message: String, val error: Throwable? = null)
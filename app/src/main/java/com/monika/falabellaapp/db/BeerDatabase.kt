package com.monika.falabellaapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.monika.falabellaapp.model.Beer


@Database(entities = arrayOf(Beer::class), version = 1)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
}
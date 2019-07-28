package com.monika.falabellaapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monika.falabellaapp.model.Beer
import io.reactivex.Single

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers")
    fun getBeers(): Single<List<Beer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Beer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Beer>)
}
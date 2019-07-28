package com.monika.falabellaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beers")
data class Beer(val abv:String,
                val ibu:String,
                @PrimaryKey
                val id:Long,
                val name:String,
                val style:String,
                val ounces:Float)
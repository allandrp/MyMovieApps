package com.alland.mymovieapps.core.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey
    var id: Int,

    var title: String,

    var rating: Float,

    var releaseDate: String,

    var synopsis: String,

    var imagePath: String,

    var backdropPath: String
)

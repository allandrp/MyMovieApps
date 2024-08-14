package com.alland.mymovieapps.core.domain.model

data class MovieDomainModel(
    var id: Int,
    var title: String,
    var rating: Float,
    var releaseDate: String,
    var synopsis: String,
    var imagePath: String,
    var backdropPath: String,
    var isFavourite: Boolean
)

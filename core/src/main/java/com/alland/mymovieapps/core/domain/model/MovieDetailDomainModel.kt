package com.alland.mymovieapps.core.domain.model

data class MovieDetailDomainModel(
    var id: Int,
    var title: String,
    var rating: Float,
    var releaseDate: String,
    var synopsis: String,
    var posterPath: String,
    var listGenres: List<GenreDomainModel>
)
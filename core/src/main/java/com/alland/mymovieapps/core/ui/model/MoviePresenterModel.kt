package com.alland.mymovieapps.core.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviePresenterModel(
    var id: Int,
    var title: String,
    var rating: Float,
    var releaseDate: String,
    var synopsis: String,
    var imagePath: String,
    var backdropPath: String,
    var isFavourite: Boolean
): Parcelable

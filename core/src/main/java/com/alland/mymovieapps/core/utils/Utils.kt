package com.alland.mymovieapps.core.utils

import com.alland.mymovieapps.core.data.local.room.MovieEntity
import com.alland.mymovieapps.core.data.remote.response.MovieApiResponse
import com.alland.mymovieapps.core.data.remote.response.ResultsItem
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.ui.model.MoviePresenterModel
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun mapDomainModelToPresenterModel(data: MovieDomainModel): MoviePresenterModel {
        return MoviePresenterModel(
            id = data.id,
            title = data.title,
            rating = data.rating,
            releaseDate = data.releaseDate,
            synopsis = data.synopsis,
            imagePath = data.imagePath,
            backdropPath = data.backdropPath
        )
    }

    fun mapPresenterModelToDomainModel(data: MoviePresenterModel): MovieDomainModel {
        return MovieDomainModel(
            id = data.id,
            title = data.title,
            rating = data.rating,
            releaseDate = data.releaseDate,
            synopsis = data.synopsis,
            imagePath = data.imagePath,
            backdropPath = data.backdropPath
        )
    }

    fun mapMovieResponseToEntity(data: List<ResultsItem>): List<MovieEntity> {
        return data.map { response ->
            MovieEntity(
                id = response.id,
                title = response.title,
                rating = response.voteAverage.toFloat(),
                releaseDate = response.releaseDate,
                synopsis = response.overview,
                imagePath = response.posterPath,
                backdropPath = response.backdropPath
            )
        }
    }

    fun mapMovieEntityToDomainModel(data: List<MovieEntity>): List<MovieDomainModel> {
        return data.map {
            MovieDomainModel(
                id = it.id,
                title = it.title,
                rating = it.rating,
                releaseDate = it.releaseDate,
                synopsis = it.synopsis,
                imagePath = it.imagePath,
                backdropPath = it.backdropPath
            )
        }
    }

    fun mapMovieDomainToEntity(data: MovieDomainModel): MovieEntity {
        return MovieEntity(
            id = data.id,
            title = data.title,
            rating = data.rating,
            releaseDate = data.releaseDate,
            synopsis = data.synopsis,
            imagePath = data.imagePath,
            backdropPath = data.backdropPath

        )
    }

    fun formatDate(originalDate: String): String {
        // Define the original and desired date formats
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val desiredFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        // Parse the original date string to a Date object
        val date = originalFormat.parse(originalDate)

        // Convert the Date object to the desired format
        return desiredFormat.format(date)
    }
}
package com.alland.mymovieapps.core.utils

import com.alland.mymovieapps.core.data.local.room.MovieEntity
import com.alland.mymovieapps.core.data.remote.response.GenresItem
import com.alland.mymovieapps.core.data.remote.response.MovieApiResponse
import com.alland.mymovieapps.core.data.remote.response.MovieDetailApiResponse
import com.alland.mymovieapps.core.data.remote.response.ResultsItem
import com.alland.mymovieapps.core.domain.model.GenreDomainModel
import com.alland.mymovieapps.core.domain.model.MovieDetailDomainModel
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
            backdropPath = data.backdropPath,
            isFavourite = data.isFavourite
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
            backdropPath = data.backdropPath,
            isFavourite = data.isFavourite
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
                backdropPath = it.backdropPath,
                isFavourite = it.isFavourite ?: false
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
            backdropPath = data.backdropPath,
            isFavourite = data.isFavourite
        )
    }

    fun mapMovieDetailResponseToMovieDetailDomain(data: MovieDetailApiResponse): MovieDetailDomainModel {
        return MovieDetailDomainModel(
            id = data.id,
            title = data.title,
            rating = data.voteAverage.toFloat(),
            releaseDate = data.releaseDate,
            synopsis = data.overview,
            listGenres = data.genres.map { mapGenreResponseToGenreModel(it) },
            posterPath = data.posterPath
        )
    }

    fun mapGenreResponseToGenreModel(data: GenresItem): GenreDomainModel {
        return GenreDomainModel(
            id = data.id,
            name = data.name,
        )
    }

    fun formatDate(originalDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val desiredFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = originalFormat.parse(originalDate)

        return desiredFormat.format(date)
    }
}
package com.alland.mymovieapps.core.domain

import com.alland.mymovieapps.core.data.local.room.MovieEntity
import com.alland.mymovieapps.core.domain.model.MovieDetailDomainModel
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getNowShowingMovies(): Flow<Result<List<MovieDomainModel>>>
    fun getDetailMovie(id: Int): Flow<Result<MovieDetailDomainModel>>
    fun isMovieFavourite(id: Int): Flow<Boolean>
    suspend fun setFavouriteMovie(movie: MovieDomainModel)
    fun getFavouriteMovies(): Flow<List<MovieDomainModel>>

}
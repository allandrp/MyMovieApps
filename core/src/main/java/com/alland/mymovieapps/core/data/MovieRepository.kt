package com.alland.mymovieapps.core.data

import android.util.Log
import com.alland.mymovieapps.core.data.local.LocalDataSource
import com.alland.mymovieapps.core.data.local.room.MovieEntity
import com.alland.mymovieapps.core.data.remote.ApiResponse
import com.alland.mymovieapps.core.data.remote.RemoteDataSource
import com.alland.mymovieapps.core.data.remote.response.ResultsItem
import com.alland.mymovieapps.core.domain.IMovieRepository
import com.alland.mymovieapps.core.domain.model.MovieDetailDomainModel
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override fun getNowPlayingMovies(): Flow<Result<List<MovieDomainModel>>> {
        return object : NetworkBoundResource<List<MovieDomainModel>, List<ResultsItem>>() {
            override fun loadDB(): Flow<List<MovieDomainModel>> {
                return localDataSource.getAllMovies().map { Utils.mapMovieEntityToDomainModel(it) }
            }

            override fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getNowPlayingMovies()
            }

            override suspend fun savedCallResult(data: List<ResultsItem>) {
                localDataSource.insertMovie(Utils.mapMovieResponseToEntity(data))
            }

            override fun shouldFetch(data: List<MovieDomainModel>?): Boolean {
                return data.isNullOrEmpty()
            }

        }.asFlow()
    }

    override fun getFavouriteMovies(): Flow<List<MovieDomainModel>> {
        return localDataSource.getFavouriteMovies().map {
            Utils.mapMovieEntityToDomainModel(it)
        }
    }

    override fun getDetailMovie(id: Int): Flow<Result<MovieDetailDomainModel>> = flow {
        emit(Result.Loading)
        val response = remoteDataSource.getDetailMovie(id).first()

        when(response){
            is ApiResponse.Success -> {
                emit(Result.Success(Utils.mapMovieDetailResponseToMovieDetailDomain(response.data)))
            }
            ApiResponse.Empty -> {
                emit(Result.Empty)
            }
            is ApiResponse.Error -> {
                emit(Result.Error(response.message))
            }
        }
    }

    override fun isMovieFavourite(id: Int): Flow<Boolean> {
        return localDataSource.isMovieFavourite(id)
    }

    override suspend fun updateMovieData(movie: MovieEntity) {
        return withContext(Dispatchers.IO){
            Log.d("update_repository", movie.toString())
            localDataSource.updateMovie(movie)
        }
    }

}
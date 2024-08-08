package com.alland.mymovieapps.core.data

import com.alland.mymovieapps.core.data.local.LocalDataSource
import com.alland.mymovieapps.core.data.remote.ApiResponse
import com.alland.mymovieapps.core.data.remote.RemoteDataSource
import com.alland.mymovieapps.core.data.remote.response.ResultsItem
import com.alland.mymovieapps.core.domain.IMovieRepository
import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import com.alland.mymovieapps.core.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getUpcomingMovies(): Flow<Result<List<MovieDomainModel>>> {
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

}
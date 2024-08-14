package com.alland.mymovieapps.core.data.remote

import android.util.Log
import com.alland.mymovieapps.core.data.remote.response.MovieApiResponse
import com.alland.mymovieapps.core.data.remote.response.MovieDetailApiResponse
import com.alland.mymovieapps.core.data.remote.response.ResultsItem
import com.alland.mymovieapps.core.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class RemoteDataSource @Inject constructor(private val apiService: MovieApiService) {

    fun getNowPlayingMovies(): Flow<ApiResponse<List<ResultsItem>>> = flow {
        val response = apiService.getNowPlayingMovies().results

        if (response.isEmpty()) {
            emit(ApiResponse.Empty)
        } else {
            emit(ApiResponse.Success(response))
        }
    }.catch { e ->
        emit(ApiResponse.Error(e.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)

    fun getDetailMovie(movieId: Int): Flow<ApiResponse<MovieDetailApiResponse>> = flow {
        val response = apiService.getMovieDetail(movieId)

        if (response != null) {
            emit(ApiResponse.Success(response))
        } else {
            emit(ApiResponse.Empty)
        }

    }.catch { e ->
        emit(ApiResponse.Error(e.message ?: "Unknown Error"))
    }.flowOn(Dispatchers.IO)


    companion object {
        private const val TAG = "RemoteDataSource"
    }
}
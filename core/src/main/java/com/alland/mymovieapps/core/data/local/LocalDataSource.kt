package com.alland.mymovieapps.core.data.local

import com.alland.mymovieapps.core.data.local.room.MovieDao
import com.alland.mymovieapps.core.data.local.room.MovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    suspend fun insertMovie(listMovie: List<MovieEntity>){
        return movieDao.insert(listMovie)
    }

}
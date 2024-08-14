package com.alland.mymovieapps.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listMovie: List<MovieEntity>)

    @Update
    suspend fun update(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavourite = 1 ORDER BY id ASC")
    fun getFavouriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND isFavourite = 1)")
    fun isMovieFavourite(id: Int): Flow<Boolean>
}
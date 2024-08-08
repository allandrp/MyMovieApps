package com.alland.mymovieapps.core.domain

import com.alland.mymovieapps.core.domain.model.MovieDomainModel
import com.alland.mymovieapps.core.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getNowShowingMovies(): Flow<Result<List<MovieDomainModel>>> {
        return movieRepository.getNowPlayingMovies()
    }
}
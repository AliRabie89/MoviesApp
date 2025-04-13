package com.ali.moviesApp.main.data.repositories

import com.ali.moviesApp.main.common.extensions.toMovieFavoriteEntity
import com.ali.moviesApp.main.common.extensions.toMovieUiModel
import com.ali.moviesApp.main.common.extensions.toMoviesEntity
import com.ali.moviesApp.main.common.extensions.toMoviesUiModel
import com.ali.moviesApp.main.data.remote.dtos.EndPointResult
import com.ali.moviesApp.main.data.remote.endPoints.MoviesEndPoints
import com.ali.moviesApp.main.data.room.MoviesDAO
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

class MoviesRepositoryImp(private val moviesEndPoints: MoviesEndPoints, private val moviesDAO: MoviesDAO) : MoviesRepository {
    override suspend fun getMovies(): EndPointResult<List<MovieUiModel>> {
        return try {
            val response = moviesEndPoints.getMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.map { it.toMoviesUiModel() }.orEmpty()
                movies.forEach { movie ->
                    movie.isInFavorites = moviesDAO.isMovieFavorite(movie.id.toInt())
                    moviesDAO.insertMovie(movie.toMoviesEntity())
                }
                EndPointResult.Success(movies)
            } else {
                val localMovies = moviesDAO.getAllMovies().map { it.toMoviesUiModel() }
                if (localMovies.isNotEmpty()) {
                    EndPointResult.Success(localMovies)
                } else {
                    EndPointResult.Error(response.code(), response.errorBody()?.string())
                }
            }
        } catch (e: Exception) {
            val localMovies = moviesDAO.getAllMovies().map { it.toMoviesUiModel() }
            if (localMovies.isNotEmpty()) {
                EndPointResult.Success(localMovies)
            } else {
                EndPointResult.Error(-1, e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getMovie(id: Int): EndPointResult<MovieUiModel> {
        return try {
            val response = moviesEndPoints.getMovie(id)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                if (movieResponse != null) {
                    val movie = movieResponse.toMoviesUiModel()
                    movie.isInFavorites = moviesDAO.isMovieFavorite(movie.id.toInt())
                    moviesDAO.insertMovie(movie.toMoviesEntity())
                    EndPointResult.Success(movie)
                } else {
                    EndPointResult.Error(response.code(), "No movie data in the response body")
                }
            } else {
                val localMovie = moviesDAO.getMovieById(id)?.toMoviesUiModel()
                if (localMovie != null) {
                    EndPointResult.Success(localMovie)
                } else {
                    EndPointResult.Error(response.code(), response.errorBody()?.string() ?: "Unknown error")
                }
            }
        } catch (e: Exception) {
            val localMovie = moviesDAO.getMovieById(id)?.toMoviesUiModel()
            if (localMovie != null) {
                EndPointResult.Success(localMovie)
            } else {
                EndPointResult.Error(-1, e.message ?: "Unknown error")
            }
        }

    }

    override suspend fun addToFavorites(model: MovieUiModel, isFavorite: Boolean) {
        if(isFavorite){
            moviesDAO.insertMovieInFavorites(model.toMovieFavoriteEntity())
        }else{
            moviesDAO.deleteMovieFromFavorites(model.toMovieFavoriteEntity())
        }
    }

    override suspend fun getAllFavoriteMovies(): List<MovieUiModel> {
        return moviesDAO.getAllFavoriteMovies().map { it.toMovieUiModel() }
    }
}

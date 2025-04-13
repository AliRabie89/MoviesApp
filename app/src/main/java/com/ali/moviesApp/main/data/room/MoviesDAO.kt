

package com.ali.moviesApp.main.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDAO {
    @Query("SELECT * FROM MOVIES WHERE id = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?
    @Query("SELECT * FROM MOVIES")
    suspend fun getAllMovies(): List<MovieEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieInFavorites(movie: FavoriteMovieEntity)
    @Delete
    suspend fun deleteMovieFromFavorites(movie: FavoriteMovieEntity)
    @Query("SELECT * FROM FAVORITES")
    suspend fun getAllFavoriteMovies(): List<FavoriteMovieEntity>
    @Query("SELECT EXISTS(SELECT 1 FROM FAVORITES WHERE id = :movieId LIMIT 1)")
    suspend fun isMovieFavorite(movieId: Int): Boolean
}
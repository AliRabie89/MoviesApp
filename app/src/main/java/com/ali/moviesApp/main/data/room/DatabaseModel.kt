

package com.ali.moviesApp.main.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MovieEntity::class,FavoriteMovieEntity::class],
    version = 5,
    exportSchema = false
)
abstract class DatabaseModel : RoomDatabase() {
    abstract fun ordersDAO(): MoviesDAO
}
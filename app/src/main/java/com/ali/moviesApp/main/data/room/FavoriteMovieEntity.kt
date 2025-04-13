

package com.ali.moviesApp.main.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovieEntity (
    @PrimaryKey var id       : Int = 0,
    var title      : String? = null,
    var year : String? = null,
    var poster         : String? = null,
    var isFavorite  : Boolean? = null,
    var plot : String? = null
)
package com.ali.moviesApp.main.common.extensions

import com.ali.moviesApp.main.data.remote.dtos.MovieDTO
import com.ali.moviesApp.main.data.room.FavoriteMovieEntity
import com.ali.moviesApp.main.data.room.MovieEntity
import com.ali.moviesApp.main.domain.uiModels.MovieUiModel

fun MovieDTO.toMoviesUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id.orEmpty(),
        title = title.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        poster = poster.orEmpty(),
        isInFavorites = false,
        plot = plot.orEmpty()
    )
}

fun MovieEntity.toMoviesUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id.toString(),
        title = title.orEmpty(),
        releaseDate = year.orEmpty(),
        poster = poster.orEmpty(),
        isInFavorites = isFavorite!!,
        plot = plot.orEmpty()
    )
}

fun MovieUiModel.toMoviesEntity(): MovieEntity {
    return MovieEntity(
        id = id.toInt(),
        title = title,
        year = releaseDate,
        poster = poster,
        isFavorite = isInFavorites,
        plot = plot
    )
}

fun MovieUiModel.toMovieFavoriteEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = id.toInt(),
        title = title.orEmpty(),
        year = releaseDate.orEmpty(),
        poster = poster.orEmpty(),
        isFavorite = isInFavorites
    )
}

fun FavoriteMovieEntity.toMovieUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id.toString(),
        title = title.orEmpty(),
        releaseDate = year.orEmpty(),
        poster = poster.orEmpty(),
        isInFavorites = isFavorite!!,
        plot = plot.orEmpty()
    )
}
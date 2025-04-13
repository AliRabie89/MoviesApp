package com.ali.moviesApp.main.domain.uiModels

import com.ali.moviesApp.main.base.Diffable

data class MovieUiModel (val id : String,
                         val title : String ,
                         val poster : String ,
                         val releaseDate : String,
                         var isInFavorites : Boolean,
                         var plot : String)
    : Diffable {
    override fun isSameItem(other: Diffable): Boolean {
        return other is MovieUiModel && this.id == other.id
    }

    override fun hasSameContent(other: Diffable): Boolean {
        return other is MovieUiModel && this.title == other.title
    }
}
package com.ali.moviesApp.main.di

import com.ali.moviesApp.main.data.remote.endPoints.MoviesEndPoints
import com.ali.moviesApp.main.data.repositories.MoviesRepositoryImp
import com.ali.moviesApp.main.data.room.MoviesDAO
import com.ali.moviesApp.main.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideMoviesRepository(accountEndPoints: MoviesEndPoints,moviesDAO: MoviesDAO): MoviesRepository {
        return MoviesRepositoryImp(accountEndPoints,moviesDAO)
    }
}
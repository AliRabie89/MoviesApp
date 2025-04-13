package com.ali.moviesApp.main.di.useCasesModules

import com.ali.moviesApp.main.domain.repository.MoviesRepository
import com.ali.moviesApp.main.domain.useCases.AddToFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetAllFavUseCase
import com.ali.moviesApp.main.domain.useCases.GetMovieUseCase
import com.ali.moviesApp.main.domain.useCases.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MoviesUseCaseModule {

    @Provides
    fun provideGetMoviesUseCase(moviesRepository: MoviesRepository): GetMoviesUseCase {
        return GetMoviesUseCase(moviesRepository)
    }

    @Provides
    fun provideGetMovieUseCase(moviesRepository: MoviesRepository):GetMovieUseCase{
        return GetMovieUseCase(moviesRepository)
    }

    @Provides
    fun provideAddTovaFavoriteUseCase(moviesRepository: MoviesRepository):AddToFavUseCase{
        return AddToFavUseCase(moviesRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(moviesRepository: MoviesRepository):GetAllFavUseCase{
        return GetAllFavUseCase(moviesRepository)
    }
}
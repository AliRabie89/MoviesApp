

package com.ali.moviesApp.main.di

import android.app.Application
import androidx.room.Room
import com.ali.moviesApp.main.data.room.MoviesDAO
import com.ali.moviesApp.main.data.room.DatabaseModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDB(application: Application): DatabaseModel {
        return Room.databaseBuilder(application, DatabaseModel::class.java, "MOVIES-APP-DB")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOrdersDao(db: DatabaseModel): MoviesDAO {
        return db.ordersDAO()
    }
}
package com.sebqv97.imagesearchapp.feature_image_search.di

import android.app.Application
import androidx.room.Room
import com.sebqv97.imagesearchapp.feature_image_search.data.local.ImagesDao
import com.sebqv97.imagesearchapp.feature_image_search.data.local.ImagesDatabase
import com.sebqv97.imagesearchapp.feature_image_search.data.remote.ImagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSetsModule {
    @Singleton
    @Provides
    fun provideImagesApi(retrofit: Retrofit): ImagesApi = retrofit.create(ImagesApi::class.java)


    @Provides
    @Singleton
    fun provideDatabase(application: Application): ImagesDatabase =
        Room.databaseBuilder(
            application, ImagesDatabase::class.java,"Images_db"
        ).build()

    @Provides
    @Singleton
    fun provideDatabaseDao(database: ImagesDatabase): ImagesDao = database.imagesDao
}
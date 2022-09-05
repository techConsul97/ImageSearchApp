package com.sebqv97.imagesearchapp.feature_image_search.di

import com.sebqv97.imagesearchapp.feature_image_search.data.local.ImagesDao
import com.sebqv97.imagesearchapp.feature_image_search.data.remote.ImagesApi
import com.sebqv97.imagesearchapp.feature_image_search.data.repository.ImagesRepositoryImpl
import com.sebqv97.imagesearchapp.feature_image_search.domain.repository.ImagesRepository
import com.sebqv97.imagesearchapp.feature_image_search.domain.use_case.GetImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainLayerModule {

    @Provides
    fun provideImagesRepository(api: ImagesApi,dao: ImagesDao): ImagesRepository = ImagesRepositoryImpl(api,dao)

    @Provides
    fun provideGetImagesUseCase(repository: ImagesRepository):GetImagesUseCase = GetImagesUseCase(repository)

}
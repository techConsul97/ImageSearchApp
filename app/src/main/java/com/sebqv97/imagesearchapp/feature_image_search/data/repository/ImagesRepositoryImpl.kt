package com.sebqv97.imagesearchapp.feature_image_search.data.repository

import com.sebqv97.imagesearchapp.feature_image_search.data.local.ImagesDao
import com.sebqv97.imagesearchapp.feature_image_search.data.remote.ImagesApi
import com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto.ImagesDto
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.FavoriteImages
import com.sebqv97.imagesearchapp.feature_image_search.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(
    private val imagesApi: ImagesApi,
    private val imagesDao: ImagesDao
):ImagesRepository {
    override suspend fun getImagesFromApi(): Response<List<ImagesDto>> {
        return imagesApi.getImages()
    }

    override suspend fun addFavoriteForUser(user: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteForUser(user: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavoriteImagesForUser(user: String): Flow<List<FavoriteImages>> {
        TODO("Not yet implemented")
    }

}
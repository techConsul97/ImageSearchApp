package com.sebqv97.imagesearchapp.feature_image_search.domain.repository

import com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto.ResponseDto
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.FavoriteImages
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface ImagesRepository {

    suspend fun getImagesFromApi(searchedWord:String,
                                 pageNumber:Int,
                                 pageSize:Int):Response<ResponseDto>

    suspend fun addFavoriteForUser(user:String) // Maybe needs rethink

    suspend fun removeFavoriteForUser(user:String) // As well, maybe

    suspend fun getAllFavoriteImagesForUser(user:String): Flow<List<FavoriteImages>>
}
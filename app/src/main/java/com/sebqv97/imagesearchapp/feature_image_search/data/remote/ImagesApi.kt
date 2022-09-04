package com.sebqv97.imagesearchapp.feature_image_search.data.remote

import com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto.ImagesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImagesApi {



    @Headers("") //add headers
    @GET(GET_IMAGES_ENDPOINT)
    suspend fun getImages():Response<List<ImagesDto>> // add query params that include pagination

    companion object{
        const val BASE_URL = ""
        const val GET_IMAGES_ENDPOINT = ""
        const val API_KEY = ""
    }
}
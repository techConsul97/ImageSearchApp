package com.sebqv97.imagesearchapp.feature_image_search.data.remote

import com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesApi {
    @Headers(
        API_KEY,
        ApI_HOST
    ) //add headers
    @GET(GET_IMAGES_ENDPOINT)
    suspend fun getImages(
        //Mandatory
        @Query("q")searchedWord:String,
        @Query("pageNumber")pageNumber:Int,
        @Query("pageSize")pageSize:Int,
        @Query("autoCorrect")autoCorrect:Boolean = false,

        @Query("safeSearch")filterContent:Boolean = true

    ):Response<ResponseDto> // add query params that include pagination

    companion object{
        const val BASE_URL = "https://contextualwebsearch-websearch-v1.p.rapidapi.com/"
        const val GET_IMAGES_ENDPOINT = "api/Search/ImageSearchAPI"
        const val API_KEY = "X-RapidAPI-Key:bb36e8f204msh8ddda9134c62172p1a04d7jsn6a9f499e73aa"
        const val ApI_HOST = "X-RapidAPI-Host:contextualwebsearch-websearch-v1.p.rapidapi.com"
    }
}
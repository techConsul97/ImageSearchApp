package com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.ImagesResponse

data class ResponseDto(
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("_type")
    val type: String,
    @SerializedName("value")
    val value: List<ImageDto>
){
    fun toImagesResponse() = ImagesResponse(totalCount = totalCount, images = value.map { it.toImage() })
}
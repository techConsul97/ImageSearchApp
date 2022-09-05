package com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.Image

data class ImageDto(
    @SerializedName("base64Encoding")
    val base64Encoding: Any,
    @SerializedName("height")
    val height: Int,
    @SerializedName("imageWebSearchUrl")
    val imageWebSearchUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnailHeight")
    val thumbnailHeight: Int,
    @SerializedName("thumbnailWidth")
    val thumbnailWidth: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("webpageUrl")
    val webpageUrl: String,
    @SerializedName("width")
    val width: Int
){
    fun toImage() = Image(
        imageHeight = height,
        imageWidth = width,
        imageTitle = title,
        imageUrl = url,
        imagePageProviderUrl = webpageUrl
    )
}
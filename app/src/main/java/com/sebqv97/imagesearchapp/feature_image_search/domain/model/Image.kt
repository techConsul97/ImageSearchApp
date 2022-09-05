package com.sebqv97.imagesearchapp.feature_image_search.domain.model

import com.google.gson.annotations.SerializedName

data class Image(


    val imageHeight: Int,
    val imageWidth: Int,
    val imageTitle: String,
    val imageUrl: String,
    val imagePageProviderUrl: String
)
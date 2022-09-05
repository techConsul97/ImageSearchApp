package com.sebqv97.imagesearchapp.feature_image_search.domain.model



data class ImagesResponse(
    val totalCount: Int,
    val images:List<Image>
)

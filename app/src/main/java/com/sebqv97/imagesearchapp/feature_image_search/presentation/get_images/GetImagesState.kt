package com.sebqv97.imagesearchapp.feature_image_search.presentation.get_images

import com.sebqv97.imagesearchapp.core.util.ErrorTypes
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.Image

data class GetImagesState(

    val isLoading:Boolean = false, //Loading

    val images : List<Image>, //Success
    val totalCount: Int? = null,//Success

    val encounteredError:ErrorTypes? = null //Error
)

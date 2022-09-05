package com.sebqv97.imagesearchapp.feature_image_search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebqv97.imagesearchapp.firebase_auth.data.model.AuthUser


@Entity(tableName = "favorite_images")
data class FavoriteImagesEntity(
    @PrimaryKey(autoGenerate = true)
    val authUser: Int
)

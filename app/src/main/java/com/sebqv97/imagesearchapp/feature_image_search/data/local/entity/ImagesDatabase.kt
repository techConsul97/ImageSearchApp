package com.sebqv97.imagesearchapp.feature_image_search.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebqv97.imagesearchapp.feature_image_search.data.local.ImagesDao



@Database(entities = [FavoriteImagesEntity::class], version = 1, exportSchema = false)
abstract class ImagesDatabase:RoomDatabase() {
    abstract val imagesDao:ImagesDao
}
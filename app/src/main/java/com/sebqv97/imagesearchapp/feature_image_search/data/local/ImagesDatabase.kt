package com.sebqv97.imagesearchapp.feature_image_search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sebqv97.imagesearchapp.feature_image_search.data.local.entity.FavoriteImagesEntity


@Database(entities = [FavoriteImagesEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ImagesDatabase: RoomDatabase() {
    abstract val imagesDao:ImagesDao
}
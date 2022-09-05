package com.sebqv97.imagesearchapp.feature_image_search.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sebqv97.imagesearchapp.core.util.generic_data_converters.JsonParser

@ProvidedTypeConverter
class Converters (private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return jsonParser.fromJson<List<String>>(json, object : TypeToken<List<String>>() {}.type)
            ?: listOf<String>()
    }

    @TypeConverter
    fun toJson(complexData: List<String>): String {
        return jsonParser.toJson(complexData, object : TypeToken<List<String>>() {}.type)
    }
}
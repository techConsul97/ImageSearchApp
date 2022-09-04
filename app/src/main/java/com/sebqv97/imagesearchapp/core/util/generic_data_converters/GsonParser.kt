package com.sebqv97.imagesearchapp.core.util.generic_data_converters

import com.google.gson.Gson

import java.lang.reflect.Type
import javax.inject.Inject

class GsonParser : JsonParser {

    @Inject
    lateinit var gson: Gson

    override fun <T> toJson(obj: T, type: Type): String {
        return gson.toJson(obj,type)
    }

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json,type)
    }
}
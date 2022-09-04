package com.sebqv97.imagesearchapp.core.util.generic_data_converters

import java.lang.reflect.Type

interface JsonParser {

    fun <T>toJson(obj:T, type: Type) :String

    fun <T>fromJson(json:String,type: Type):T?
}
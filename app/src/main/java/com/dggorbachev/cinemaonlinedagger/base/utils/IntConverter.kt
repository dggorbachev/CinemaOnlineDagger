package com.dggorbachev.cinemaonlinedagger.base.utils

import androidx.room.TypeConverter
import com.dggorbachev.cinemaonlinedagger.base.genericType
import com.google.gson.Gson

class IntConverter {
    @TypeConverter
    fun fromListInt(list: List<Int>?): String? {
        if (list == null) return null
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListInt(data: String): List<Int>? {
        val listIntType = genericType<List<Int>>()
        return Gson().fromJson(data, listIntType)
    }

}
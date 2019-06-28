package com.example.kotlinfirstapp.converter

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun stringAsStringList(strings: String?): ArrayList<String> {
        val list = mutableListOf<String>()
        strings
            ?.split(",")
            ?.forEach {
                list.add(it)
            }

        return list as ArrayList<String>
    }

    @TypeConverter
    fun stringListAsString(strings: ArrayList<String>?): String {
        var result = ""
        strings?.forEach { element ->
            result += "$element,"
        }
        return result.removeSuffix(",")
    }
}

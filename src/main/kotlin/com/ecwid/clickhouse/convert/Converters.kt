package com.ecwid.clickhouse.convert

import java.util.*

abstract class BaseConverter<T> {

    internal abstract fun convertFromString(string: String): T

    fun toValue(str: String?): T {
        requireNotNull(str)
        return convertFromString(str)
    }

    fun toNullableValue(str: String?): T? {
        return if (str == null) {
            null
        } else {
            convertFromString(str)
        }
    }

    fun toArray(array: List<String?>): List<T> {
        return array.map(::toValue)
    }

    fun toNullableArray(array: List<String?>): List<T?> {
        return array.map(::toNullableValue)
    }
}

abstract class DateConverter {

    abstract fun convertFromString(string: String, timeZone: TimeZone): Date

    fun toValue(str: String?, timeZone: TimeZone): Date {
        requireNotNull(str)
        return convertFromString(str, timeZone)
    }

    fun toNullableValue(str: String?, timeZone: TimeZone): Date? {
        return if (str == null) {
            null
        } else {
            convertFromString(str, timeZone)
        }
    }

    fun toArray(array: List<String?>, timeZone: TimeZone): List<Date> {
        return array.map { toValue(it, timeZone) }
    }

    fun toNullableArray(array: List<String?>, timeZone: TimeZone): List<Date?> {
        return array.map { toNullableValue(it, timeZone) }
    }
}

open class StringConverter {

    fun toValue(str: String?): String {
        requireNotNull(str)
        return str
    }

    fun toNullableValue(str: String?): String? {
        return str
    }

    fun toArray(array: List<String?>): List<String> {
        return array.map(::toValue)
    }

    fun toNullableArray(array: List<String?>): List<String?> {
        return array
    }
}
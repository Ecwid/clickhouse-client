package com.ecwid.clickhouse.convert

import java.util.*

abstract class BaseConverter<T> {

    internal abstract fun convertFromString(string: String): T

    fun to(str: String?): T {
        requireNotNull(str)
        return convertFromString(str)
    }

    fun toNullable(str: String?): T? {
        return if (str == null) {
            null
        } else {
            convertFromString(str)
        }
    }

    fun toArray(array: List<String?>): List<T> {
        return array.map(::to)
    }

    fun toNullableArray(array: List<String?>): List<T?> {
        return array.map(::toNullable)
    }
}

abstract class DateConverter {

    abstract fun convertFromString(string: String, timeZone: TimeZone): Date

    fun to(str: String?, timeZone: TimeZone): Date {
        requireNotNull(str)
        return convertFromString(str, timeZone)
    }

    fun toNullable(str: String?, timeZone: TimeZone): Date? {
        return if (str == null) {
            null
        } else {
            convertFromString(str, timeZone)
        }
    }

    fun toArray(array: List<String?>, timeZone: TimeZone): List<Date> {
        return array.map { to(it, timeZone) }
    }

    fun toNullableArray(array: List<String?>, timeZone: TimeZone): List<Date?> {
        return array.map { toNullable(it, timeZone) }
    }
}

open class StringConverter {

    fun to(str: String?): String {
        requireNotNull(str)
        return str
    }

    fun toNullable(str: String?): String? {
        return str
    }

    fun toArray(array: List<String?>): List<String> {
        return array.map(::to)
    }

    fun toNullableArray(array: List<String?>): List<String?> {
        return array
    }
}
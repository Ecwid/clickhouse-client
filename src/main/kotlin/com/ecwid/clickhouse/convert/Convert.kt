package com.ecwid.clickhouse.convert

import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

object Convert {

    object Int8 : BaseConverter<Byte>() {
        override fun convertFromString(string: String) = string.toByte()
    }

    object Int16 : BaseConverter<Short>() {
        override fun convertFromString(string: String) = string.toShort()
    }

    object Int32 : BaseConverter<Int>() {
        override fun convertFromString(string: String) = string.toInt()
    }

    object Int64 : BaseConverter<Long>() {
        override fun convertFromString(string: String) = string.toLong()
    }

    object UInt32 : BaseConverter<Int>() {
        override fun convertFromString(string: String) = Integer.parseUnsignedInt(string)
    }

    object UInt64 : BaseConverter<Long>() {
        override fun convertFromString(string: String) = java.lang.Long.parseUnsignedLong(string)
    }

    object Float32 : BaseConverter<Float>() {
        override fun convertFromString(string: String) = string.toFloat()
    }

    object Float64 : BaseConverter<Double>() {
        override fun convertFromString(string: String) = string.toDouble()
    }

    object Decimal : BaseConverter<BigDecimal>() {
        override fun convertFromString(string: String) = string.toBigDecimal()
    }

    object Str : StringConverter()

    object DateTime : DateConverter() {

        private val DATETIME_FORMAT = ThreadLocal.withInitial {
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }

        override fun convertFromString(string: String, timeZone: TimeZone): java.util.Date {
            val format = DATETIME_FORMAT.get()
            format.timeZone = timeZone
            return format.parse(string)
        }
    }

    object Date : DateConverter() {

        private val DATE_FORMAT = ThreadLocal.withInitial {
            SimpleDateFormat("yyyy-MM-dd")
        }

        override fun convertFromString(string: String, timeZone: TimeZone): java.util.Date {
            val format = DATE_FORMAT.get()
            format.timeZone = timeZone
            return format.parse(string)
        }
    }

}
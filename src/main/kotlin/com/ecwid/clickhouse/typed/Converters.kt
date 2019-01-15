package com.ecwid.clickhouse.typed

import java.math.BigDecimal
import java.math.BigInteger
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// ------------------ Int8 ------------------
internal fun String?.toInt8(): Byte {
    requireNotNull(this)
    return this.toByte()
}

internal fun String?.toInt8Nullable(): Byte? {
    return this?.toByte()
}

// ------------------ Int16 ------------------
internal fun String?.toInt16(): Short {
    requireNotNull(this)
    return this.toShort()
}

internal fun String?.toInt16Nullable(): Short? {
    return this?.toShort()
}

// ------------------ Int32 ------------------
internal fun String?.toInt32(): Int {
    requireNotNull(this)
    return this.toInt()
}

internal fun String?.toInt32Nullable(): Int? {
    return this?.toInt()
}

internal fun String?.toUInt32(): Int {
    requireNotNull(this)
    return Integer.parseUnsignedInt(this)
}

internal fun String?.toUInt32Nullable(): Int? {
    return if (this == null) {
        null
    } else {
        Integer.parseUnsignedInt(this)
    }
}

internal fun String?.toUInt32AsBigInt(): BigInteger {
    requireNotNull(this)
    return BigInteger(this)
}

internal fun String?.toUInt32NullableAsBigInt(): BigInteger? {
    return if (this == null) {
        null
    } else {
        BigInteger(this)
    }
}

// ------------------ Int64 ------------------
internal fun String?.toInt64(): Long {
    requireNotNull(this)
    return this.toLong()
}

internal fun String?.toInt64Nullable(): Long? {
    return this?.toLong()
}

internal fun String?.toUInt64(): Long {
    requireNotNull(this)
    return java.lang.Long.parseUnsignedLong(this)
}

internal fun String?.toUInt64Nullable(): Long? {
    return if (this == null) {
        null
    } else {
        java.lang.Long.parseUnsignedLong(this)
    }
}

// ------------------ Float32 ------------------
internal fun String?.toFloat32(): Float {
    requireNotNull(this)
    return this.toFloat()
}

internal fun String?.toFloat32Nullable(): Float? {
    return this?.toFloat()
}

// ------------------ Float64 ------------------
internal fun String?.toFloat64(): Double {
    requireNotNull(this)
    return this.toDouble()
}

internal fun String?.toFloat64Nullable(): Double? {
    return this?.toDouble()
}

// ------------------ Decimal ------------------
internal fun String?.toDecimal(): BigDecimal {
    requireNotNull(this)
    return this.toBigDecimal()
}

internal fun String?.toDecimalNullable(): BigDecimal? {
    return this?.toBigDecimal()
}

// ------------------ Date & DateTime ------------------
private val DATE_FORMAT = ThreadLocal.withInitial {
    SimpleDateFormat("yyyy-MM-dd")
}

private val DATETIME_FORMAT = ThreadLocal.withInitial {
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}

internal fun String?.toDate(timeZone: TimeZone): Date {
    requireNotNull(this)

    val format = DATE_FORMAT.get()
    format.timeZone = timeZone
    return format.parse(this)
}

internal fun String?.toDateNullable(timeZone: TimeZone): Date? {
    val date = this ?: return null

    val format = DATE_FORMAT.get()
    format.timeZone = timeZone
    return format.parse(date)
}

internal fun String?.toDateTime(timeZone: TimeZone): Date {
    requireNotNull(this)

    val format = DATETIME_FORMAT.get()
    format.timeZone = timeZone
    return format.parse(this)
}

internal fun String?.toDateTimeNullable(timeZone: TimeZone): Date? {
    val date = this ?: return null

    val format = DATETIME_FORMAT.get()
    format.timeZone = timeZone
    return format.parse(date)
}

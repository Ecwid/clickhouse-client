package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.raw.RawRow
import java.math.BigDecimal
import java.util.*

data class TypedRow(
    private val defaultTimeZone: TimeZone,
    private val rawRow: RawRow
) {

    // ----------------- INT_8 --------------------
    fun getInt8(columnIndex: Int): Byte {
        return rawRow.getScalarValue(columnIndex).toInt8()
    }

    fun getInt8Array(columnIndex: Int): List<Byte> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt8)
    }

    fun getInt8Nullable(columnIndex: Int): Byte? {
        return rawRow.getScalarValue(columnIndex)?.toInt8Nullable()
    }

    fun getInt8NullableArray(columnIndex: Int): List<Byte?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt8Nullable)
    }

    fun getInt8(columnName: String): Byte {
        return rawRow.getScalarValue(columnName).toInt8()
    }

    fun getInt8Array(columnName: String): List<Byte> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt8)
    }

    fun getInt8Nullable(columnName: String): Byte? {
        return rawRow.getScalarValue(columnName).toInt8Nullable()
    }

    fun getInt8NullableArray(columnName: String): List<Byte?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt8Nullable)
    }

    // ----------------- INT_8 --------------------

    fun getInt16(columnIndex: Int): Short {
        return rawRow.getScalarValue(columnIndex).toInt16()
    }

    fun getInt16Array(columnIndex: Int): List<Short> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt16)
    }

    fun getInt16Nullable(columnIndex: Int): Short? {
        return rawRow.getScalarValue(columnIndex)?.toInt16Nullable()
    }

    fun getInt16NullableArray(columnIndex: Int): List<Short?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt16Nullable)
    }

    fun getInt16(columnName: String): Short {
        return rawRow.getScalarValue(columnName).toInt16()
    }

    fun getInt16Array(columnName: String): List<Short> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt16)
    }

    fun getInt16Nullable(columnName: String): Short? {
        return rawRow.getScalarValue(columnName).toInt16Nullable()
    }

    fun getInt16NullableArray(columnName: String): List<Short?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt16Nullable)
    }

    // ----------------- INT_32 --------------------

    fun getInt32(columnIndex: Int): Int {
        return rawRow.getScalarValue(columnIndex).toInt32()
    }

    fun getInt32Array(columnIndex: Int): List<Int> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32)
    }

    fun getInt32Nullable(columnIndex: Int): Int? {
        return rawRow.getScalarValue(columnIndex)?.toInt32Nullable()
    }

    fun getInt32NullableArray(columnIndex: Int): List<Int?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32Nullable)
    }

    fun getInt32(columnName: String): Int {
        return rawRow.getScalarValue(columnName).toInt32()
    }

    fun getInt32Array(columnName: String): List<Int> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32)
    }

    fun getInt32Nullable(columnName: String): Int? {
        return rawRow.getScalarValue(columnName).toInt32Nullable()
    }

    fun getInt32NullableArray(columnName: String): List<Int?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32Nullable)
    }

    // ----------------- INT_64 --------------------

    fun getInt64(columnIndex: Int): Long {
        return rawRow.getScalarValue(columnIndex).toInt64()
    }

    fun getInt64Array(columnIndex: Int): List<Long> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt64)
    }

    fun getInt64Nullable(columnIndex: Int): Long? {
        return rawRow.getScalarValue(columnIndex)?.toInt64Nullable()
    }

    fun getInt64NullableArray(columnIndex: Int): List<Long?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt64Nullable)
    }

    fun getInt64(columnName: String): Long {
        return rawRow.getScalarValue(columnName).toInt64()
    }

    fun getInt64Array(columnName: String): List<Long> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt64)
    }

    fun getInt64Nullable(columnName: String): Long? {
        return rawRow.getScalarValue(columnName).toInt64Nullable()
    }

    fun getInt64NullableArray(columnName: String): List<Long?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt64Nullable)
    }

    // ----------------- UINT_8 --------------------

    fun getUInt8(columnIndex: Int): Int {
        return rawRow.getScalarValue(columnIndex).toInt32()
    }

    fun getUInt8Array(columnIndex: Int): List<Int> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32)
    }

    fun getUInt8Nullable(columnIndex: Int): Int? {
        return rawRow.getScalarValue(columnIndex)?.toInt32Nullable()
    }

    fun getUInt8NullableArray(columnIndex: Int): List<Int?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32Nullable)
    }

    fun getUInt8(columnName: String): Int {
        return rawRow.getScalarValue(columnName).toInt32()
    }

    fun getUInt8Array(columnName: String): List<Int> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32)
    }

    fun getUInt8Nullable(columnName: String): Int? {
        return rawRow.getScalarValue(columnName).toInt32Nullable()
    }

    fun getUInt8NullableArray(columnName: String): List<Int?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32Nullable)
    }

    // ----------------- UINT_16 --------------------

    fun getUInt16(columnIndex: Int): Int {
        return rawRow.getScalarValue(columnIndex).toInt32()
    }

    fun getUInt16Array(columnIndex: Int): List<Int> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32)
    }

    fun getUInt16Nullable(columnIndex: Int): Int? {
        return rawRow.getScalarValue(columnIndex)?.toInt32Nullable()
    }

    fun getUInt16NullableArray(columnIndex: Int): List<Int?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toInt32Nullable)
    }

    fun getUInt16(columnName: String): Int {
        return rawRow.getScalarValue(columnName).toInt32()
    }

    fun getUInt16Array(columnName: String): List<Int> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32)
    }

    fun getUInt16Nullable(columnName: String): Int? {
        return rawRow.getScalarValue(columnName).toInt32Nullable()
    }

    fun getUInt16NullableArray(columnName: String): List<Int?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toInt32Nullable)
    }

    // ----------------- UINT_32 --------------------

    fun getUInt32(columnIndex: Int): Int {
        return rawRow.getScalarValue(columnIndex).toUInt32()
    }

    fun getUInt32Array(columnIndex: Int): List<Int> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toUInt32)
    }

    fun getUInt32Nullable(columnIndex: Int): Int? {
        return rawRow.getScalarValue(columnIndex)?.toUInt32Nullable()
    }

    fun getUInt32NullableArray(columnIndex: Int): List<Int?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toUInt32Nullable)
    }

    fun getUInt32(columnName: String): Int {
        return rawRow.getScalarValue(columnName).toUInt32()
    }

    fun getUInt32Array(columnName: String): List<Int> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toUInt32)
    }

    fun getUInt32Nullable(columnName: String): Int? {
        return rawRow.getScalarValue(columnName).toUInt32Nullable()
    }

    fun getUInt32NullableArray(columnName: String): List<Int?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toUInt32Nullable)
    }
    // ----------------- UINT_64 --------------------

    fun getUInt64(columnIndex: Int): Long {
        return rawRow.getScalarValue(columnIndex).toUInt64()
    }

    fun getUInt64Array(columnIndex: Int): List<Long> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toUInt64)
    }

    fun getUInt64Nullable(columnIndex: Int): Long? {
        return rawRow.getScalarValue(columnIndex)?.toUInt64Nullable()
    }

    fun getUInt64NullableArray(columnIndex: Int): List<Long?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toUInt64Nullable)
    }

    fun getUInt64(columnName: String): Long {
        return rawRow.getScalarValue(columnName).toUInt64()
    }

    fun getUInt64Array(columnName: String): List<Long> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toUInt64)
    }

    fun getUInt64Nullable(columnName: String): Long? {
        return rawRow.getScalarValue(columnName).toUInt64Nullable()
    }

    fun getUInt64NullableArray(columnName: String): List<Long?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toUInt64Nullable)
    }

    // ----------------- FLOAT_32 --------------------
    fun getFloat32(columnIndex: Int): Float {
        return rawRow.getScalarValue(columnIndex).toFloat32()
    }

    fun getFloat32Array(columnIndex: Int): List<Float> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toFloat32)
    }

    fun getFloat32Nullable(columnIndex: Int): Float? {
        return rawRow.getScalarValue(columnIndex)?.toFloat32Nullable()
    }

    fun getFloat32NullableArray(columnIndex: Int): List<Float?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toFloat32Nullable)
    }

    fun getFloat32(columnName: String): Float {
        return rawRow.getScalarValue(columnName).toFloat32()
    }

    fun getFloat32Array(columnName: String): List<Float> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toFloat32)
    }

    fun getFloat32Nullable(columnName: String): Float? {
        return rawRow.getScalarValue(columnName).toFloat32Nullable()
    }

    fun getFloat32NullableArray(columnName: String): List<Float?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toFloat32Nullable)
    }

    // ----------------- FLOAT_64 --------------------

    fun getFloat64(columnIndex: Int): Double {
        return rawRow.getScalarValue(columnIndex).toFloat64()
    }

    fun getFloat64Array(columnIndex: Int): List<Double> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toFloat64)
    }

    fun getFloat64Nullable(columnIndex: Int): Double? {
        return rawRow.getScalarValue(columnIndex)?.toFloat64Nullable()
    }

    fun getFloat64NullableArray(columnIndex: Int): List<Double?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toFloat64Nullable)
    }

    fun getFloat64(columnName: String): Double {
        return rawRow.getScalarValue(columnName).toFloat64()
    }

    fun getFloat64Array(columnName: String): List<Double> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toFloat64)
    }

    fun getFloat64Nullable(columnName: String): Double? {
        return rawRow.getScalarValue(columnName).toFloat64Nullable()
    }

    fun getFloat64NullableArray(columnName: String): List<Double?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toFloat64Nullable)
    }

    // ----------------- STRING --------------------
    fun getString(columnIndex: Int): String {
        return requireNotNull(rawRow.getScalarValue(columnIndex))
    }

    fun getStringArray(columnIndex: Int): List<String> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(::requireNotNull)
    }

    fun getStringNullable(columnIndex: Int): String? {
        return rawRow.getScalarValue(columnIndex)
    }

    fun getStringNullableArray(columnIndex: Int): List<String?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array
    }

    fun getString(columnName: String): String {
        return requireNotNull(rawRow.getScalarValue(columnName))
    }

    fun getStringArray(columnName: String): List<String> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(::requireNotNull)
    }

    fun getStringNullable(columnName: String): String? {
        return rawRow.getScalarValue(columnName)
    }

    fun getStringNullableArray(columnName: String): List<String?> {
        val array = rawRow.getArrayValue(columnName)
        return array
    }

    // ----------------- DATETIME --------------------

    fun getDateTime(columnIndex: Int): Date {
        return rawRow.getScalarValue(columnIndex).toDateTime(defaultTimeZone)
    }

    fun getDateTime(columnIndex: Int, timeZone: TimeZone): Date {
        return rawRow.getScalarValue(columnIndex).toDateTime(timeZone)
    }

    fun getDateTimeArray(columnIndex: Int): List<Date> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateTime(defaultTimeZone) }
    }

    fun getDateTimeArray(columnIndex: Int, timeZone: TimeZone): List<Date> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateTime(timeZone) }
    }

    fun getDateTimeNullable(columnIndex: Int): Date? {
        return rawRow.getScalarValue(columnIndex)?.toDateTimeNullable(defaultTimeZone)
    }

    fun getDateTimeNullable(columnIndex: Int, timeZone: TimeZone): Date? {
        return rawRow.getScalarValue(columnIndex)?.toDateTimeNullable(timeZone)
    }

    fun getDateTimeNullableArray(columnIndex: Int): List<Date?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateTimeNullable(defaultTimeZone) }
    }

    fun getDateTimeNullableArray(columnIndex: Int, timeZone: TimeZone): List<Date?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateTimeNullable(timeZone) }
    }

    fun getDateTime(columnName: String): Date {
        return rawRow.getScalarValue(columnName).toDateTime(defaultTimeZone)
    }

    fun getDateTime(columnName: String, timeZone: TimeZone): Date {
        return rawRow.getScalarValue(columnName).toDateTime(timeZone)
    }

    fun getDateTimeArray(columnName: String): List<Date> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateTime(defaultTimeZone) }
    }

    fun getDateTimeArray(columnName: String, timeZone: TimeZone): List<Date> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateTime(timeZone) }
    }

    fun getDateTimeNullable(columnName: String): Date? {
        return rawRow.getScalarValue(columnName).toDateTimeNullable(defaultTimeZone)
    }

    fun getDateTimeNullable(columnName: String, timeZone: TimeZone): Date? {
        return rawRow.getScalarValue(columnName).toDateTimeNullable(timeZone)
    }

    fun getDateTimeNullableArray(columnName: String): List<Date?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateTimeNullable(defaultTimeZone) }
    }

    fun getDateTimeNullableArray(columnName: String, timeZone: TimeZone): List<Date?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateTimeNullable(timeZone) }
    }
    // ----------------- DATE --------------------

    fun getDate(columnIndex: Int): Date {
        return rawRow.getScalarValue(columnIndex).toDate(defaultTimeZone)
    }

    fun getDate(columnIndex: Int, timeZone: TimeZone): Date {
        return rawRow.getScalarValue(columnIndex).toDate(timeZone)
    }

    fun getDateArray(columnIndex: Int): List<Date> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDate(defaultTimeZone) }
    }

    fun getDateArray(columnIndex: Int, timeZone: TimeZone): List<Date> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDate(timeZone) }
    }

    fun getDateNullable(columnIndex: Int): Date? {
        return rawRow.getScalarValue(columnIndex)?.toDateNullable(defaultTimeZone)
    }

    fun getDateNullable(columnIndex: Int, timeZone: TimeZone): Date? {
        return rawRow.getScalarValue(columnIndex)?.toDateNullable(timeZone)
    }

    fun getDateNullableArray(columnIndex: Int): List<Date?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateNullable(defaultTimeZone) }
    }

    fun getDateNullableArray(columnIndex: Int, timeZone: TimeZone): List<Date?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map { it.toDateNullable(timeZone) }
    }

    fun getDate(columnName: String): Date {
        return rawRow.getScalarValue(columnName).toDate(defaultTimeZone)
    }

    fun getDate(columnName: String, timeZone: TimeZone): Date {
        return rawRow.getScalarValue(columnName).toDate(timeZone)
    }

    fun getDateArray(columnName: String): List<Date> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDate(defaultTimeZone) }
    }

    fun getDateArray(columnName: String, timeZone: TimeZone): List<Date> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDate(timeZone) }
    }

    fun getDateNullable(columnName: String): Date? {
        return rawRow.getScalarValue(columnName).toDateNullable(defaultTimeZone)
    }

    fun getDateNullable(columnName: String, timeZone: TimeZone): Date? {
        return rawRow.getScalarValue(columnName).toDateNullable(timeZone)
    }

    fun getDateNullableArray(columnName: String): List<Date?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateNullable(defaultTimeZone) }
    }

    fun getDateNullableArray(columnName: String, timeZone: TimeZone): List<Date?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map { it.toDateNullable(timeZone) }
    }

    // ----------------- DECIMAL --------------------

    fun getDecimal(columnIndex: Int): BigDecimal {
        return rawRow.getScalarValue(columnIndex).toDecimal()
    }

    fun getDecimalArray(columnIndex: Int): List<BigDecimal> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toDecimal)
    }

    fun getDecimalNullable(columnIndex: Int): BigDecimal? {
        return rawRow.getScalarValue(columnIndex)?.toDecimalNullable()
    }

    fun getDecimalNullableArray(columnIndex: Int): List<BigDecimal?> {
        val array = rawRow.getArrayValue(columnIndex)
        return array.map(String?::toDecimalNullable)
    }

    fun getDecimal(columnName: String): BigDecimal {
        return rawRow.getScalarValue(columnName).toDecimal()
    }

    fun getDecimalArray(columnName: String): List<BigDecimal> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toDecimal)
    }

    fun getDecimalNullable(columnName: String): BigDecimal? {
        return rawRow.getScalarValue(columnName).toDecimalNullable()
    }

    fun getDecimalNullableArray(columnName: String): List<BigDecimal?> {
        val array = rawRow.getArrayValue(columnName)
        return array.map(String?::toDecimalNullable)
    }

}
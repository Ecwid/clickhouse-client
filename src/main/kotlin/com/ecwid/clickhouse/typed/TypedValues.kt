package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.convert.Convert
import com.ecwid.clickhouse.raw.RawValues
import java.math.BigDecimal
import java.util.*

class TypedValues {

    private val rawValues = RawValues()

    fun getRawValues() = rawValues

    // ----------------- INT_8 --------------------
    fun setInt8(columnIndex: Int, value: Byte) {
        rawValues.setScalar(columnIndex, Convert.Int8.fromValue(value))
    }

    fun setInt8Array(columnIndex: Int, array: List<Byte>) {
        rawValues.setArray(columnIndex, Convert.Int8.fromArray(array))
    }

    fun setInt8Nullable(columnIndex: Int, value: Byte?) {
        rawValues.setScalar(columnIndex, Convert.Int8.fromNullableValue(value))
    }

    fun setInt8NullableArray(columnIndex: Int, array: List<Byte?>) {
        rawValues.setArray(columnIndex, Convert.Int8.fromNullableArray(array))
    }

    // ----------------- INT_16 --------------------
    fun setInt16(columnIndex: Int, value: Short) {
        rawValues.setScalar(columnIndex, Convert.Int16.fromValue(value))
    }

    fun setInt16Array(columnIndex: Int, array: List<Short>) {
        rawValues.setArray(columnIndex, Convert.Int16.fromArray(array))
    }

    fun setInt16Nullable(columnIndex: Int, value: Short?) {
        rawValues.setScalar(columnIndex, Convert.Int16.fromNullableValue(value))
    }

    fun setInt16NullableArray(columnIndex: Int, array: List<Short?>) {
        rawValues.setArray(columnIndex, Convert.Int16.fromNullableArray(array))
    }

    // ----------------- INT_32 --------------------
    fun setInt32(columnIndex: Int, value: Int) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromValue(value))
    }

    fun setInt32Array(columnIndex: Int, array: List<Int>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromArray(array))
    }

    fun setInt32Nullable(columnIndex: Int, value: Int?) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromNullableValue(value))
    }

    fun setInt32NullableArray(columnIndex: Int, array: List<Int?>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromNullableArray(array))
    }

    // ----------------- INT_64 --------------------
    fun setInt64(columnIndex: Int, value: Long) {
        rawValues.setScalar(columnIndex, Convert.Int64.fromValue(value))
    }

    fun setInt64Array(columnIndex: Int, array: List<Long>) {
        rawValues.setArray(columnIndex, Convert.Int64.fromArray(array))
    }

    fun setInt64Nullable(columnIndex: Int, value: Long?) {
        rawValues.setScalar(columnIndex, Convert.Int64.fromNullableValue(value))
    }

    fun setInt64NullableArray(columnIndex: Int, array: List<Long?>) {
        rawValues.setArray(columnIndex, Convert.Int64.fromNullableArray(array))
    }

    // ----------------- UINT_8 --------------------
    fun setUInt8(columnIndex: Int, value: Int) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromValue(value))
    }

    fun setUInt8Array(columnIndex: Int, array: List<Int>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromArray(array))
    }

    fun setUInt8Nullable(columnIndex: Int, value: Int?) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromNullableValue(value))
    }

    fun setUInt8NullableArray(columnIndex: Int, array: List<Int?>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromNullableArray(array))
    }

    // ----------------- UINT_16 --------------------
    fun setUInt16(columnIndex: Int, value: Int) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromValue(value))
    }

    fun setUInt16Array(columnIndex: Int, array: List<Int>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromArray(array))
    }

    fun setUInt16Nullable(columnIndex: Int, value: Int?) {
        rawValues.setScalar(columnIndex, Convert.Int32.fromNullableValue(value))
    }

    fun setUInt16NullableArray(columnIndex: Int, array: List<Int?>) {
        rawValues.setArray(columnIndex, Convert.Int32.fromNullableArray(array))
    }

    // ----------------- UINT_32 --------------------
    fun setUInt32(columnIndex: Int, value: Int) {
        rawValues.setScalar(columnIndex, Convert.UInt32.fromValue(value))
    }

    fun setUInt32Array(columnIndex: Int, array: List<Int>) {
        rawValues.setArray(columnIndex, Convert.UInt32.fromArray(array))
    }

    fun setUInt32Nullable(columnIndex: Int, value: Int?) {
        rawValues.setScalar(columnIndex, Convert.UInt32.fromNullableValue(value))
    }

    fun setUInt32NullableArray(columnIndex: Int, array: List<Int?>) {
        rawValues.setArray(columnIndex, Convert.UInt32.fromNullableArray(array))
    }

    // ----------------- UINT_64 --------------------
    fun setUInt64(columnIndex: Int, value: Long) {
        rawValues.setScalar(columnIndex, Convert.UInt64.fromValue(value))
    }

    fun setUInt64Array(columnIndex: Int, array: List<Long>) {
        rawValues.setArray(columnIndex, Convert.UInt64.fromArray(array))
    }

    fun setUInt64Nullable(columnIndex: Int, value: Long?) {
        rawValues.setScalar(columnIndex, Convert.UInt64.fromNullableValue(value))
    }

    fun setUInt64NullableArray(columnIndex: Int, array: List<Long?>) {
        rawValues.setArray(columnIndex, Convert.UInt64.fromNullableArray(array))
    }

    // ----------------- Float32 --------------------
    fun setFloat32(columnIndex: Int, value: Float) {
        rawValues.setScalar(columnIndex, Convert.Float32.fromValue(value))
    }

    fun setFloat32Array(columnIndex: Int, array: List<Float>) {
        rawValues.setArray(columnIndex, Convert.Float32.fromArray(array))
    }

    fun setFloat32Nullable(columnIndex: Int, value: Float?) {
        rawValues.setScalar(columnIndex, Convert.Float32.fromNullableValue(value))
    }

    fun setFloat32NullableArray(columnIndex: Int, array: List<Float?>) {
        rawValues.setArray(columnIndex, Convert.Float32.fromNullableArray(array))
    }

    // ----------------- Float64 --------------------
    fun setFloat64(columnIndex: Int, value: Double) {
        rawValues.setScalar(columnIndex, Convert.Float64.fromValue(value))
    }

    fun setFloat64Array(columnIndex: Int, array: List<Double>) {
        rawValues.setArray(columnIndex, Convert.Float64.fromArray(array))
    }

    fun setFloat64Nullable(columnIndex: Int, value: Double?) {
        rawValues.setScalar(columnIndex, Convert.Float64.fromNullableValue(value))
    }

    fun setFloat64NullableArray(columnIndex: Int, array: List<Double?>) {
        rawValues.setArray(columnIndex, Convert.Float64.fromNullableArray(array))
    }

    // ----------------- Decimal --------------------
    fun setDecimal(columnIndex: Int, value: BigDecimal) {
        rawValues.setScalar(columnIndex, Convert.Decimal.fromValue(value))
    }

    fun setDecimalArray(columnIndex: Int, array: List<BigDecimal>) {
        rawValues.setArray(columnIndex, Convert.Decimal.fromArray(array))
    }

    fun setDecimalNullable(columnIndex: Int, value: BigDecimal?) {
        rawValues.setScalar(columnIndex, Convert.Decimal.fromNullableValue(value))
    }

    fun setDecimalNullableArray(columnIndex: Int, array: List<BigDecimal?>) {
        rawValues.setArray(columnIndex, Convert.Decimal.fromNullableArray(array))
    }

    // ----------------- String --------------------
    fun setString(columnIndex: Int, value: String) {
        rawValues.setScalar(columnIndex, "'$value'")
    }

    fun setStringArray(columnIndex: Int, array: List<String>) {
        // wrap every value to 'value'
        val wrappedArray = array.map { value ->
            "'$value'"
        }
        rawValues.setArray(columnIndex, wrappedArray)
    }

    fun setStringNullable(columnIndex: Int, value: String?) {
        if (value == null) {
            rawValues.setScalar(columnIndex, null)
        } else {
            rawValues.setScalar(columnIndex, "'$value'")
        }
    }

    fun setStringNullableArray(columnIndex: Int, array: List<String?>) {
        val wrappedArray = array.map { value ->
            if (value == null) {
                null
            } else {
                "'$value'"
            }
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    // ----------------- DateTime --------------------
    fun setDateTime(columnIndex: Int, value: Date, timeZone: TimeZone) {
        val scalar = Convert.DateTime.fromValue(value, timeZone)
        rawValues.setScalar(columnIndex, "'$scalar'")
    }

    fun setDateTimeArray(columnIndex: Int, array: List<Date>, timeZone: TimeZone) {
        val wrappedArray = Convert.DateTime.fromArray(array, timeZone).map { value ->
            "'$value'"
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    fun setDateTimeNullable(columnIndex: Int, value: Date?, timeZone: TimeZone) {
        val scalar = Convert.DateTime.fromNullableValue(value, timeZone)

        if (scalar == null) {
            rawValues.setScalar(columnIndex, null)
        } else {
            rawValues.setScalar(columnIndex, "'$scalar'")
        }
    }

    fun setDateTimeNullableArray(columnIndex: Int, array: List<Date?>, timeZone: TimeZone) {
        val wrappedArray = Convert.DateTime.fromNullableArray(array, timeZone).map { value ->
            if (value == null) {
                null
            } else {
                "'$value'"
            }
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    // ----------------- Date --------------------
    fun setDate(columnIndex: Int, value: Date, timeZone: TimeZone) {
        rawValues.setScalar(columnIndex, "'${Convert.Date.fromValue(value, timeZone)}'")
    }

    fun setDateArray(columnIndex: Int, array: List<Date>, timeZone: TimeZone) {
        val wrappedArray = Convert.Date.fromArray(array, timeZone).map { value ->
            "'$value'"
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    fun setDateNullable(columnIndex: Int, value: Date?, timeZone: TimeZone) {
        val scalar = Convert.Date.fromNullableValue(value, timeZone)

        if (scalar == null) {
            rawValues.setScalar(columnIndex, null)
        } else {
            rawValues.setScalar(columnIndex, "'$scalar'")
        }
    }

    fun setDateNullableArray(columnIndex: Int, array: List<Date?>, timeZone: TimeZone) {
        val wrappedArray = Convert.Date.fromNullableArray(array, timeZone).map { value ->
            if (value == null) {
                null
            } else {
                "'$value'"
            }
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    // ----------------- Enum --------------------
    fun <T : Enum<T>> setEnum(columnIndex: Int, value: T) {
        rawValues.setScalar(columnIndex, "'${Convert.Enum.fromValue(value)}'")
    }

    fun <T : Enum<T>> setEnumArray(columnIndex: Int, array: List<T>) {
        val wrappedArray = Convert.Enum.fromArray(array).map { value ->
            "'$value'"
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

    fun <T : Enum<T>> setEnumNullable(columnIndex: Int, value: T?) {
        val scalar = Convert.Enum.fromNullableValue(value)

        if (scalar == null) {
            rawValues.setScalar(columnIndex, null)
        } else {
            rawValues.setScalar(columnIndex, "'$scalar'")
        }
    }

    fun <T : Enum<T>> setEnumNullableArray(columnIndex: Int, array: List<T?>) {
        val wrappedArray = Convert.Enum.fromNullableArray(array).map { value ->
            if (value == null) {
                null
            } else {
                "'$value'"
            }
        }

        rawValues.setArray(columnIndex, wrappedArray)
    }

}
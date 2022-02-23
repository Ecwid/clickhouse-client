package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.convert.Convert
import com.ecwid.clickhouse.raw.RawValues
import java.math.BigDecimal
import java.util.*

class TypedValues {

	private val rawValues = RawValues()

	fun getRawValues() = rawValues

	// ----------------- INT_8 --------------------
	fun setInt8(columnName: String, value: Byte) {
		rawValues.addScalar(columnName, Convert.Int8.fromValue(value))
	}

	fun setInt8Array(columnName: String, array: List<Byte>) {
		rawValues.addArray(columnName, Convert.Int8.fromArray(array))
	}

	fun setInt8Nullable(columnName: String, value: Byte?) {
		rawValues.addScalar(columnName, Convert.Int8.fromNullableValue(value))
	}

	fun setInt8NullableArray(columnName: String, array: List<Byte?>) {
		rawValues.addArray(columnName, Convert.Int8.fromNullableArray(array))
	}

	// ----------------- INT_16 --------------------
	fun setInt16(columnName: String, value: Short) {
		rawValues.addScalar(columnName, Convert.Int16.fromValue(value))
	}

	fun setInt16Array(columnName: String, array: List<Short>) {
		rawValues.addArray(columnName, Convert.Int16.fromArray(array))
	}

	fun setInt16Nullable(columnName: String, value: Short?) {
		rawValues.addScalar(columnName, Convert.Int16.fromNullableValue(value))
	}

	fun setInt16NullableArray(columnName: String, array: List<Short?>) {
		rawValues.addArray(columnName, Convert.Int16.fromNullableArray(array))
	}

	// ----------------- INT_32 --------------------
	fun setInt32(columnName: String, value: Int) {
		rawValues.addScalar(columnName, Convert.Int32.fromValue(value))
	}

	fun setInt32Array(columnName: String, array: List<Int>) {
		rawValues.addArray(columnName, Convert.Int32.fromArray(array))
	}

	fun setInt32Nullable(columnName: String, value: Int?) {
		rawValues.addScalar(columnName, Convert.Int32.fromNullableValue(value))
	}

	fun setInt32NullableArray(columnName: String, array: List<Int?>) {
		rawValues.addArray(columnName, Convert.Int32.fromNullableArray(array))
	}

	// ----------------- INT_64 --------------------
	fun setInt64(columnName: String, value: Long) {
		rawValues.addScalar(columnName, Convert.Int64.fromValue(value))
	}

	fun setInt64Array(columnName: String, array: List<Long>) {
		rawValues.addArray(columnName, Convert.Int64.fromArray(array))
	}

	fun setInt64Nullable(columnName: String, value: Long?) {
		rawValues.addScalar(columnName, Convert.Int64.fromNullableValue(value))
	}

	fun setInt64NullableArray(columnName: String, array: List<Long?>) {
		rawValues.addArray(columnName, Convert.Int64.fromNullableArray(array))
	}

	// ----------------- UINT_8 --------------------
	fun setUInt8(columnName: String, value: Int) {
		rawValues.addScalar(columnName, Convert.Int32.fromValue(value))
	}

	fun setUInt8Array(columnName: String, array: List<Int>) {
		rawValues.addArray(columnName, Convert.Int32.fromArray(array))
	}

	fun setUInt8Nullable(columnName: String, value: Int?) {
		rawValues.addScalar(columnName, Convert.Int32.fromNullableValue(value))
	}

	fun setUInt8NullableArray(columnName: String, array: List<Int?>) {
		rawValues.addArray(columnName, Convert.Int32.fromNullableArray(array))
	}

	// ----------------- UINT_16 --------------------
	fun setUInt16(columnName: String, value: Int) {
		rawValues.addScalar(columnName, Convert.Int32.fromValue(value))
	}

	fun setUInt16Array(columnName: String, array: List<Int>) {
		rawValues.addArray(columnName, Convert.Int32.fromArray(array))
	}

	fun setUInt16Nullable(columnName: String, value: Int?) {
		rawValues.addScalar(columnName, Convert.Int32.fromNullableValue(value))
	}

	fun setUInt16NullableArray(columnName: String, array: List<Int?>) {
		rawValues.addArray(columnName, Convert.Int32.fromNullableArray(array))
	}

	// ----------------- UINT_32 --------------------
	fun setUInt32(columnName: String, value: Int) {
		rawValues.addScalar(columnName, Convert.UInt32.fromValue(value))
	}

	fun setUInt32Array(columnName: String, array: List<Int>) {
		rawValues.addArray(columnName, Convert.UInt32.fromArray(array))
	}

	fun setUInt32Nullable(columnName: String, value: Int?) {
		rawValues.addScalar(columnName, Convert.UInt32.fromNullableValue(value))
	}

	fun setUInt32NullableArray(columnName: String, array: List<Int?>) {
		rawValues.addArray(columnName, Convert.UInt32.fromNullableArray(array))
	}

	// ----------------- UINT_64 --------------------
	fun setUInt64(columnName: String, value: Long) {
		rawValues.addScalar(columnName, Convert.UInt64.fromValue(value))
	}

	fun setUInt64Array(columnName: String, array: List<Long>) {
		rawValues.addArray(columnName, Convert.UInt64.fromArray(array))
	}

	fun setUInt64Nullable(columnName: String, value: Long?) {
		rawValues.addScalar(columnName, Convert.UInt64.fromNullableValue(value))
	}

	fun setUInt64NullableArray(columnName: String, array: List<Long?>) {
		rawValues.addArray(columnName, Convert.UInt64.fromNullableArray(array))
	}

	// ----------------- Float32 --------------------
	fun setFloat32(columnName: String, value: Float) {
		rawValues.addScalar(columnName, Convert.Float32.fromValue(value))
	}

	fun setFloat32Array(columnName: String, array: List<Float>) {
		rawValues.addArray(columnName, Convert.Float32.fromArray(array))
	}

	fun setFloat32Nullable(columnName: String, value: Float?) {
		rawValues.addScalar(columnName, Convert.Float32.fromNullableValue(value))
	}

	fun setFloat32NullableArray(columnName: String, array: List<Float?>) {
		rawValues.addArray(columnName, Convert.Float32.fromNullableArray(array))
	}

	// ----------------- Float64 --------------------
	fun setFloat64(columnName: String, value: Double) {
		rawValues.addScalar(columnName, Convert.Float64.fromValue(value))
	}

	fun setFloat64Array(columnName: String, array: List<Double>) {
		rawValues.addArray(columnName, Convert.Float64.fromArray(array))
	}

	fun setFloat64Nullable(columnName: String, value: Double?) {
		rawValues.addScalar(columnName, Convert.Float64.fromNullableValue(value))
	}

	fun setFloat64NullableArray(columnName: String, array: List<Double?>) {
		rawValues.addArray(columnName, Convert.Float64.fromNullableArray(array))
	}

	// ----------------- Decimal --------------------
	fun setDecimal(columnName: String, value: BigDecimal) {
		rawValues.addScalar(columnName, Convert.Decimal.fromValue(value))
	}

	fun setDecimalArray(columnName: String, array: List<BigDecimal>) {
		rawValues.addArray(columnName, Convert.Decimal.fromArray(array))
	}

	fun setDecimalNullable(columnName: String, value: BigDecimal?) {
		rawValues.addScalar(columnName, Convert.Decimal.fromNullableValue(value))
	}

	fun setDecimalNullableArray(columnName: String, array: List<BigDecimal?>) {
		rawValues.addArray(columnName, Convert.Decimal.fromNullableArray(array))
	}

	// ----------------- String --------------------
	fun setString(columnName: String, value: String) {
		rawValues.addScalar(columnName, Convert.Str.fromValue(value))
	}

	fun setStringArray(columnName: String, array: List<String>) {
		rawValues.addArray(columnName, Convert.Str.fromArray(array))
	}

	fun setStringNullable(columnName: String, value: String?) {
		rawValues.addScalar(columnName, Convert.Str.fromNullableValue(value))
	}

	fun setStringNullableArray(columnName: String, array: List<String?>) {
		rawValues.addArray(columnName, Convert.Str.fromNullableArray(array))
	}

	// ----------------- DateTime --------------------
	fun setDateTime(columnName: String, value: Date, timeZone: TimeZone) {
		val scalar = Convert.DateTime.fromValue(value, timeZone)
		rawValues.addScalar(columnName, "'$scalar'")
	}

	fun setDateTimeArray(columnName: String, array: List<Date>, timeZone: TimeZone) {
		val wrappedArray = Convert.DateTime.fromArray(array, timeZone).map { value ->
			"'$value'"
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	fun setDateTimeNullable(columnName: String, value: Date?, timeZone: TimeZone) {
		val scalar = Convert.DateTime.fromNullableValue(value, timeZone)

		if (scalar == null) {
			rawValues.addScalar(columnName, null)
		} else {
			rawValues.addScalar(columnName, "'$scalar'")
		}
	}

	fun setDateTimeNullableArray(columnName: String, array: List<Date?>, timeZone: TimeZone) {
		val wrappedArray = Convert.DateTime.fromNullableArray(array, timeZone).map { value ->
			if (value == null) {
				null
			} else {
				"'$value'"
			}
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	// ----------------- Date --------------------
	fun setDate(columnName: String, value: Date, timeZone: TimeZone) {
		rawValues.addScalar(columnName, "'${Convert.Date.fromValue(value, timeZone)}'")
	}

	fun setDateArray(columnName: String, array: List<Date>, timeZone: TimeZone) {
		val wrappedArray = Convert.Date.fromArray(array, timeZone).map { value ->
			"'$value'"
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	fun setDateNullable(columnName: String, value: Date?, timeZone: TimeZone) {
		val scalar = Convert.Date.fromNullableValue(value, timeZone)

		if (scalar == null) {
			rawValues.addScalar(columnName, null)
		} else {
			rawValues.addScalar(columnName, "'$scalar'")
		}
	}

	fun setDateNullableArray(columnName: String, array: List<Date?>, timeZone: TimeZone) {
		val wrappedArray = Convert.Date.fromNullableArray(array, timeZone).map { value ->
			if (value == null) {
				null
			} else {
				"'$value'"
			}
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	// ----------------- Enum --------------------
	fun <T : Enum<T>> setEnum(columnName: String, value: T) {
		rawValues.addScalar(columnName, "'${Convert.Enum.fromValue(value)}'")
	}

	fun <T : Enum<T>> setEnumArray(columnName: String, array: List<T>) {
		val wrappedArray = Convert.Enum.fromArray(array).map { value ->
			"'$value'"
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	fun <T : Enum<T>> setEnumNullable(columnName: String, value: T?) {
		val scalar = Convert.Enum.fromNullableValue(value)

		if (scalar == null) {
			rawValues.addScalar(columnName, null)
		} else {
			rawValues.addScalar(columnName, "'$scalar'")
		}
	}

	fun <T : Enum<T>> setEnumNullableArray(columnName: String, array: List<T?>) {
		val wrappedArray = Convert.Enum.fromNullableArray(array).map { value ->
			if (value == null) {
				null
			} else {
				"'$value'"
			}
		}

		rawValues.addArray(columnName, wrappedArray)
	}

	// ----------------- Map --------------------
	fun setMapStringToString(columnName: String, value: Map<String, String>) {
		rawValues.addMap(columnName, Convert.Map.fromValue(value))
	}

	fun setMapStringToNullableString(columnName: String, value: Map<String, String?>) {
		rawValues.addMap(columnName, Convert.Map.fromValue(value))
	}
}

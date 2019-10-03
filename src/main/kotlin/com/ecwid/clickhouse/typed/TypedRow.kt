package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.convert.Convert
import com.ecwid.clickhouse.raw.RawRow
import java.math.BigDecimal
import java.util.*

data class TypedRow(
	private val defaultTimeZone: TimeZone,
	private val rawRow: RawRow
) {

	// ----------------- INT_8 --------------------
	fun getInt8(columnIndex: Int): Byte {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int8.toValue(scalar)
	}

	fun getInt8Array(columnIndex: Int): List<Byte> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int8.toArray(array)
	}

	fun getInt8Nullable(columnIndex: Int): Byte? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int8.toNullableValue(scalar)
	}

	fun getInt8NullableArray(columnIndex: Int): List<Byte?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int8.toNullableArray(array)
	}

	fun getInt8(columnName: String): Byte {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int8.toValue(scalar)
	}

	fun getInt8Array(columnName: String): List<Byte> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int8.toArray(array)
	}

	fun getInt8Nullable(columnName: String): Byte? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int8.toNullableValue(scalar)
	}

	fun getInt8NullableArray(columnName: String): List<Byte?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int8.toNullableArray(array)
	}

	// ----------------- INT_16 --------------------

	fun getInt16(columnIndex: Int): Short {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int16.toValue(scalar)
	}

	fun getInt16Array(columnIndex: Int): List<Short> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int16.toArray(array)
	}

	fun getInt16Nullable(columnIndex: Int): Short? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int16.toNullableValue(scalar)
	}

	fun getInt16NullableArray(columnIndex: Int): List<Short?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int16.toNullableArray(array)
	}

	fun getInt16(columnName: String): Short {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int16.toValue(scalar)
	}

	fun getInt16Array(columnName: String): List<Short> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int16.toArray(array)
	}

	fun getInt16Nullable(columnName: String): Short? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int16.toNullableValue(scalar)
	}

	fun getInt16NullableArray(columnName: String): List<Short?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int16.toNullableArray(array)
	}

	// ----------------- INT_32 --------------------

	fun getInt32(columnIndex: Int): Int {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toValue(scalar)
	}

	fun getInt32Array(columnIndex: Int): List<Int> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toArray(array)
	}

	fun getInt32Nullable(columnIndex: Int): Int? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getInt32NullableArray(columnIndex: Int): List<Int?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toNullableArray(array)
	}

	fun getInt32(columnName: String): Int {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toValue(scalar)
	}

	fun getInt32Array(columnName: String): List<Int> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toArray(array)
	}

	fun getInt32Nullable(columnName: String): Int? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getInt32NullableArray(columnName: String): List<Int?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toNullableArray(array)
	}

	// ----------------- INT_64 --------------------

	fun getInt64(columnIndex: Int): Long {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int64.toValue(scalar)
	}

	fun getInt64Array(columnIndex: Int): List<Long> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int64.toArray(array)
	}

	fun getInt64Nullable(columnIndex: Int): Long? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int64.toNullableValue(scalar)
	}

	fun getInt64NullableArray(columnIndex: Int): List<Long?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int64.toNullableArray(array)
	}

	fun getInt64(columnName: String): Long {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int64.toValue(scalar)
	}

	fun getInt64Array(columnName: String): List<Long> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int64.toArray(array)
	}

	fun getInt64Nullable(columnName: String): Long? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int64.toNullableValue(scalar)
	}

	fun getInt64NullableArray(columnName: String): List<Long?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int64.toNullableArray(array)
	}

	// ----------------- UINT_8 --------------------

	fun getUInt8(columnIndex: Int): Int {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toValue(scalar)
	}

	fun getUInt8Array(columnIndex: Int): List<Int> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toArray(array)
	}

	fun getUInt8Nullable(columnIndex: Int): Int? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getUInt8NullableArray(columnIndex: Int): List<Int?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toNullableArray(array)
	}

	fun getUInt8(columnName: String): Int {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toValue(scalar)
	}

	fun getUInt8Array(columnName: String): List<Int> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toArray(array)
	}

	fun getUInt8Nullable(columnName: String): Int? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getUInt8NullableArray(columnName: String): List<Int?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toNullableArray(array)
	}

	// ----------------- UINT_16 --------------------

	fun getUInt16(columnIndex: Int): Int {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toValue(scalar)
	}

	fun getUInt16Array(columnIndex: Int): List<Int> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toArray(array)
	}

	fun getUInt16Nullable(columnIndex: Int): Int? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getUInt16NullableArray(columnIndex: Int): List<Int?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Int32.toNullableArray(array)
	}

	fun getUInt16(columnName: String): Int {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toValue(scalar)
	}

	fun getUInt16Array(columnName: String): List<Int> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toArray(array)
	}

	fun getUInt16Nullable(columnName: String): Int? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Int32.toNullableValue(scalar)
	}

	fun getUInt16NullableArray(columnName: String): List<Int?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Int32.toNullableArray(array)
	}

	// ----------------- UINT_32 --------------------

	fun getUInt32(columnIndex: Int): Int {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.UInt32.toValue(scalar)
	}

	fun getUInt32Array(columnIndex: Int): List<Int> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.UInt32.toArray(array)
	}

	fun getUInt32Nullable(columnIndex: Int): Int? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.UInt32.toNullableValue(scalar)
	}

	fun getUInt32NullableArray(columnIndex: Int): List<Int?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.UInt32.toNullableArray(array)
	}

	fun getUInt32(columnName: String): Int {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.UInt32.toValue(scalar)
	}

	fun getUInt32Array(columnName: String): List<Int> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.UInt32.toArray(array)
	}

	fun getUInt32Nullable(columnName: String): Int? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.UInt32.toNullableValue(scalar)
	}

	fun getUInt32NullableArray(columnName: String): List<Int?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.UInt32.toNullableArray(array)
	}
	// ----------------- UINT_64 --------------------

	fun getUInt64(columnIndex: Int): Long {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.UInt64.toValue(scalar)
	}

	fun getUInt64Array(columnIndex: Int): List<Long> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.UInt64.toArray(array)
	}

	fun getUInt64Nullable(columnIndex: Int): Long? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.UInt64.toNullableValue(scalar)
	}

	fun getUInt64NullableArray(columnIndex: Int): List<Long?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.UInt64.toNullableArray(array)
	}

	fun getUInt64(columnName: String): Long {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.UInt64.toValue(scalar)
	}

	fun getUInt64Array(columnName: String): List<Long> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.UInt64.toArray(array)
	}

	fun getUInt64Nullable(columnName: String): Long? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.UInt64.toNullableValue(scalar)
	}

	fun getUInt64NullableArray(columnName: String): List<Long?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.UInt64.toNullableArray(array)
	}

	// ----------------- FLOAT_32 --------------------
	fun getFloat32(columnIndex: Int): Float {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Float32.toValue(scalar)
	}

	fun getFloat32Array(columnIndex: Int): List<Float> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Float32.toArray(array)
	}

	fun getFloat32Nullable(columnIndex: Int): Float? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Float32.toNullableValue(scalar)
	}

	fun getFloat32NullableArray(columnIndex: Int): List<Float?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Float32.toNullableArray(array)
	}

	fun getFloat32(columnName: String): Float {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Float32.toValue(scalar)
	}

	fun getFloat32Array(columnName: String): List<Float> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Float32.toArray(array)
	}

	fun getFloat32Nullable(columnName: String): Float? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Float32.toNullableValue(scalar)
	}

	fun getFloat32NullableArray(columnName: String): List<Float?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Float32.toNullableArray(array)
	}

	// ----------------- FLOAT_64 --------------------

	fun getFloat64(columnIndex: Int): Double {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Float64.toValue(scalar)
	}

	fun getFloat64Array(columnIndex: Int): List<Double> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Float64.toArray(array)
	}

	fun getFloat64Nullable(columnIndex: Int): Double? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Float64.toNullableValue(scalar)
	}

	fun getFloat64NullableArray(columnIndex: Int): List<Double?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Float64.toNullableArray(array)
	}

	fun getFloat64(columnName: String): Double {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Float64.toValue(scalar)
	}

	fun getFloat64Array(columnName: String): List<Double> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Float64.toArray(array)
	}

	fun getFloat64Nullable(columnName: String): Double? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Float64.toNullableValue(scalar)
	}

	fun getFloat64NullableArray(columnName: String): List<Double?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Float64.toNullableArray(array)
	}

	// ----------------- STRING --------------------
	fun getString(columnIndex: Int): String {
		val scalar = rawRow.getScalarValue(columnIndex)
		return requireNotNull(scalar)
	}

	fun getStringArray(columnIndex: Int): List<String> {
		return rawRow.getArrayValue(columnIndex).requireNoNulls()
	}

	fun getStringNullable(columnIndex: Int): String? {
		return rawRow.getScalarValue(columnIndex)
	}

	fun getStringNullableArray(columnIndex: Int): List<String?> {
		return rawRow.getArrayValue(columnIndex)
	}

	fun getString(columnName: String): String {
		val scalar = rawRow.getScalarValue(columnName)
		return requireNotNull(scalar)
	}

	fun getStringArray(columnName: String): List<String> {
		return rawRow.getArrayValue(columnName).requireNoNulls()
	}

	fun getStringNullable(columnName: String): String? {
		return rawRow.getScalarValue(columnName)
	}

	fun getStringNullableArray(columnName: String): List<String?> {
		return rawRow.getArrayValue(columnName)
	}

	// ----------------- DATETIME --------------------

	fun getDateTime(columnIndex: Int): Date {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.DateTime.toValue(scalar, defaultTimeZone)
	}

	fun getDateTime(columnIndex: Int, timeZone: TimeZone): Date {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.DateTime.toValue(scalar, timeZone)
	}

	fun getDateTimeArray(columnIndex: Int): List<Date> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.DateTime.toArray(array, defaultTimeZone)
	}

	fun getDateTimeArray(columnIndex: Int, timeZone: TimeZone): List<Date> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.DateTime.toArray(array, timeZone)
	}

	fun getDateTimeNullable(columnIndex: Int): Date? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.DateTime.toNullableValue(scalar, defaultTimeZone)
	}

	fun getDateTimeNullable(columnIndex: Int, timeZone: TimeZone): Date? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.DateTime.toNullableValue(scalar, timeZone)
	}

	fun getDateTimeNullableArray(columnIndex: Int): List<Date?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.DateTime.toNullableArray(array, defaultTimeZone)
	}

	fun getDateTimeNullableArray(columnIndex: Int, timeZone: TimeZone): List<Date?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.DateTime.toNullableArray(array, timeZone)
	}

	fun getDateTime(columnName: String): Date {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.DateTime.toValue(scalar, defaultTimeZone)
	}

	fun getDateTime(columnName: String, timeZone: TimeZone): Date {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.DateTime.toValue(scalar, timeZone)
	}

	fun getDateTimeArray(columnName: String): List<Date> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.DateTime.toArray(array, defaultTimeZone)
	}

	fun getDateTimeArray(columnName: String, timeZone: TimeZone): List<Date> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.DateTime.toArray(array, timeZone)
	}

	fun getDateTimeNullable(columnName: String): Date? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.DateTime.toNullableValue(scalar, defaultTimeZone)
	}

	fun getDateTimeNullable(columnName: String, timeZone: TimeZone): Date? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.DateTime.toNullableValue(scalar, timeZone)
	}

	fun getDateTimeNullableArray(columnName: String): List<Date?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.DateTime.toArray(array, defaultTimeZone)
	}

	fun getDateTimeNullableArray(columnName: String, timeZone: TimeZone): List<Date?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.DateTime.toArray(array, timeZone)
	}
	// ----------------- DATE --------------------

	fun getDate(columnIndex: Int): Date {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Date.toValue(scalar, defaultTimeZone)
	}

	fun getDate(columnIndex: Int, timeZone: TimeZone): Date {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Date.toValue(scalar, timeZone)
	}

	fun getDateArray(columnIndex: Int): List<Date> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Date.toArray(array, defaultTimeZone)
	}

	fun getDateArray(columnIndex: Int, timeZone: TimeZone): List<Date> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Date.toArray(array, timeZone)
	}

	fun getDateNullable(columnIndex: Int): Date? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Date.toNullableValue(scalar, defaultTimeZone)
	}

	fun getDateNullable(columnIndex: Int, timeZone: TimeZone): Date? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Date.toNullableValue(scalar, timeZone)
	}

	fun getDateNullableArray(columnIndex: Int): List<Date?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Date.toNullableArray(array, defaultTimeZone)
	}

	fun getDateNullableArray(columnIndex: Int, timeZone: TimeZone): List<Date?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Date.toNullableArray(array, timeZone)
	}

	fun getDate(columnName: String): Date {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Date.toValue(scalar, defaultTimeZone)
	}

	fun getDate(columnName: String, timeZone: TimeZone): Date {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Date.toValue(scalar, timeZone)
	}

	fun getDateArray(columnName: String): List<Date> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Date.toArray(array, defaultTimeZone)
	}

	fun getDateArray(columnName: String, timeZone: TimeZone): List<Date> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Date.toArray(array, timeZone)
	}

	fun getDateNullable(columnName: String): Date? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Date.toNullableValue(scalar, defaultTimeZone)
	}

	fun getDateNullable(columnName: String, timeZone: TimeZone): Date? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Date.toNullableValue(scalar, timeZone)
	}

	fun getDateNullableArray(columnName: String): List<Date?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Date.toNullableArray(array, defaultTimeZone)
	}

	fun getDateNullableArray(columnName: String, timeZone: TimeZone): List<Date?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Date.toNullableArray(array, timeZone)
	}

	// ----------------- DECIMAL --------------------

	fun getDecimal(columnIndex: Int): BigDecimal {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Decimal.toValue(scalar)
	}

	fun getDecimalArray(columnIndex: Int): List<BigDecimal> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Decimal.toArray(array)
	}

	fun getDecimalNullable(columnIndex: Int): BigDecimal? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Decimal.toNullableValue(scalar)
	}

	fun getDecimalNullableArray(columnIndex: Int): List<BigDecimal?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Decimal.toNullableArray(array)
	}

	fun getDecimal(columnName: String): BigDecimal {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Decimal.toValue(scalar)
	}

	fun getDecimalArray(columnName: String): List<BigDecimal> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Decimal.toArray(array)
	}

	fun getDecimalNullable(columnName: String): BigDecimal? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Decimal.toNullableValue(scalar)
	}

	fun getDecimalNullableArray(columnName: String): List<BigDecimal?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Decimal.toNullableArray(array)
	}

	// ----------------- ENUM --------------------
	fun <T : Enum<T>> getEnum(columnIndex: Int, clazz: Class<T>): T {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Enum.toValue(scalar, clazz)
	}

	fun <T : Enum<T>> getEnumArray(columnIndex: Int, clazz: Class<T>): List<T> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Enum.toArray(array, clazz)
	}

	fun <T : Enum<T>> getNullableEnum(columnIndex: Int, clazz: Class<T>): T? {
		val scalar = rawRow.getScalarValue(columnIndex)
		return Convert.Enum.toNullableValue(scalar, clazz)
	}

	fun <T : Enum<T>> getEnumNullableArray(columnIndex: Int, clazz: Class<T>): List<T?> {
		val array = rawRow.getArrayValue(columnIndex)
		return Convert.Enum.toNullableArray(array, clazz)
	}

	fun <T : Enum<T>> getEnum(columnName: String, clazz: Class<T>): T {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Enum.toValue(scalar, clazz)
	}

	fun <T : Enum<T>> getEnumArray(columnName: String, clazz: Class<T>): List<T> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Enum.toArray(array, clazz)
	}

	fun <T : Enum<T>> getNullableEnum(columnName: String, clazz: Class<T>): T? {
		val scalar = rawRow.getScalarValue(columnName)
		return Convert.Enum.toNullableValue(scalar, clazz)
	}

	fun <T : Enum<T>> getEnumNullableArray(columnName: String, clazz: Class<T>): List<T?> {
		val array = rawRow.getArrayValue(columnName)
		return Convert.Enum.toNullableArray(array, clazz)
	}

}

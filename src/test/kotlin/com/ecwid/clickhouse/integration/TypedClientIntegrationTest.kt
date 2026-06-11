package com.ecwid.clickhouse.integration

import com.ecwid.clickhouse.convert.Convert
import com.ecwid.clickhouse.typed.TypedValues
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.BigInteger
import java.util.TimeZone

internal enum class OrderStatus { CREATED, PAID, CANCELLED }

internal class TypedClientIntegrationTest : AbstractClickHouseTest() {

	override fun generateTestData(): List<String> {
		return listOf(
			"""
			create table $TABLE (
				id Int32,

				bool_v Bool,
				int8_v Int8,
				int16_v Int16,
				int32_v Int32,
				int64_v Int64,
				int128_v Int128,
				int256_v Int256,
				uint8_v UInt8,
				uint16_v UInt16,
				uint32_v UInt32,
				uint64_v UInt64,
				uint128_v UInt128,
				uint256_v UInt256,
				float32_v Float32,
				float64_v Float64,
				decimal_v Decimal(18, 4),
				string_v String,
				fixed_string_v FixedString(4),
				low_cardinality_v LowCardinality(String),
				date_v Date,
				datetime_v DateTime,
				enum8_v Enum8('CREATED' = 1, 'PAID' = 2, 'CANCELLED' = 3),
				enum16_v Enum16('CREATED' = 1000, 'PAID' = 2000, 'CANCELLED' = 3000),

				nullable_bool_v Nullable(Bool),
				nullable_int8_v Nullable(Int8),
				nullable_int16_v Nullable(Int16),
				nullable_int32_v Nullable(Int32),
				nullable_int64_v Nullable(Int64),
				nullable_int128_v Nullable(Int128),
				nullable_int256_v Nullable(Int256),
				nullable_uint8_v Nullable(UInt8),
				nullable_uint16_v Nullable(UInt16),
				nullable_uint32_v Nullable(UInt32),
				nullable_uint64_v Nullable(UInt64),
				nullable_uint128_v Nullable(UInt128),
				nullable_uint256_v Nullable(UInt256),
				nullable_float32_v Nullable(Float32),
				nullable_float64_v Nullable(Float64),
				nullable_decimal_v Nullable(Decimal(18, 4)),
				nullable_string_v Nullable(String),
				nullable_date_v Nullable(Date),
				nullable_datetime_v Nullable(DateTime),
				nullable_enum8_v Nullable(Enum8('CREATED' = 1, 'PAID' = 2, 'CANCELLED' = 3)),

				bool_array_v Array(Bool),
				int8_array_v Array(Int8),
				int16_array_v Array(Int16),
				int32_array_v Array(Int32),
				int64_array_v Array(Int64),
				int128_array_v Array(Int128),
				int256_array_v Array(Int256),
				uint8_array_v Array(UInt8),
				uint16_array_v Array(UInt16),
				uint32_array_v Array(UInt32),
				uint64_array_v Array(UInt64),
				uint128_array_v Array(UInt128),
				uint256_array_v Array(UInt256),
				float32_array_v Array(Float32),
				float64_array_v Array(Float64),
				decimal_array_v Array(Decimal(18, 4)),
				string_array_v Array(String),
				date_array_v Array(Date),
				datetime_array_v Array(DateTime),
				enum8_array_v Array(Enum8('CREATED' = 1, 'PAID' = 2, 'CANCELLED' = 3)),
				low_cardinality_array_v Array(LowCardinality(String)),

				bool_nullable_array_v Array(Nullable(Bool)),
				int8_nullable_array_v Array(Nullable(Int8)),
				int16_nullable_array_v Array(Nullable(Int16)),
				int32_nullable_array_v Array(Nullable(Int32)),
				int64_nullable_array_v Array(Nullable(Int64)),
				int128_nullable_array_v Array(Nullable(Int128)),
				int256_nullable_array_v Array(Nullable(Int256)),
				uint8_nullable_array_v Array(Nullable(UInt8)),
				uint16_nullable_array_v Array(Nullable(UInt16)),
				uint32_nullable_array_v Array(Nullable(UInt32)),
				uint64_nullable_array_v Array(Nullable(UInt64)),
				uint128_nullable_array_v Array(Nullable(UInt128)),
				uint256_nullable_array_v Array(Nullable(UInt256)),
				float32_nullable_array_v Array(Nullable(Float32)),
				float64_nullable_array_v Array(Nullable(Float64)),
				decimal_nullable_array_v Array(Nullable(Decimal(18, 4))),
				string_nullable_array_v Array(Nullable(String)),
				date_nullable_array_v Array(Nullable(Date)),
				datetime_nullable_array_v Array(Nullable(DateTime)),
				enum8_nullable_array_v Array(Nullable(Enum8('CREATED' = 1, 'PAID' = 2, 'CANCELLED' = 3))),

				map_string_string_v Map(String, String),
				map_string_nullable_v Map(String, Nullable(String))
			) engine = MergeTree order by id
			""".trimIndent()
		)
	}

	@Test
	fun testInsertAndSelect() {
		val row1 = TypedValues().apply {
			setInt32("id", 1)

			// scalars
			setBool("bool_v", true)
			setInt8("int8_v", Byte.MIN_VALUE)
			setInt16("int16_v", Short.MIN_VALUE)
			setInt32("int32_v", Int.MIN_VALUE)
			setInt64("int64_v", Long.MIN_VALUE)
			setInt128("int128_v", INT128_MIN)
			setInt256("int256_v", INT256_MIN)
			setUInt8("uint8_v", 0)
			setUInt16("uint16_v", 0)
			setUInt32("uint32_v", 0)
			setUInt64("uint64_v", 0L)
			setUInt128("uint128_v", BigInteger.ZERO)
			setUInt256("uint256_v", BigInteger.ZERO)
			setFloat32("float32_v", 2.5f)
			setFloat64("float64_v", 2.5)
			setDecimal("decimal_v", BigDecimal("12345.6789"))
			setString("string_v", STRING_WITH_ESCAPES)
			setString("fixed_string_v", "abcd")
			setString("low_cardinality_v", "lc_value")
			setDate("date_v", DATE_1, UTC)
			setDateTime("datetime_v", DATETIME_1, UTC)
			setEnum("enum8_v", OrderStatus.PAID)
			setEnum("enum16_v", OrderStatus.PAID)

			// nullable scalars — values in row 1, nulls in row 2
			setBoolNullable("nullable_bool_v", true)
			setInt8Nullable("nullable_int8_v", Byte.MAX_VALUE)
			setInt16Nullable("nullable_int16_v", Short.MAX_VALUE)
			setInt32Nullable("nullable_int32_v", Int.MAX_VALUE)
			setInt64Nullable("nullable_int64_v", Long.MAX_VALUE)
			setInt128Nullable("nullable_int128_v", INT128_MAX)
			setInt256Nullable("nullable_int256_v", INT256_MAX)
			setUInt8Nullable("nullable_uint8_v", 255)
			setUInt16Nullable("nullable_uint16_v", 65535)
			setUInt32Nullable("nullable_uint32_v", UINT32_MAX)
			setUInt64Nullable("nullable_uint64_v", UINT64_MAX)
			setUInt128Nullable("nullable_uint128_v", UINT128_MAX)
			setUInt256Nullable("nullable_uint256_v", UINT256_MAX)
			setFloat32Nullable("nullable_float32_v", -0.5f)
			setFloat64Nullable("nullable_float64_v", -0.25)
			setDecimalNullable("nullable_decimal_v", BigDecimal("-0.0001"))
			setStringNullable("nullable_string_v", "present")
			setDateNullable("nullable_date_v", DATE_2, UTC)
			setDateTimeNullable("nullable_datetime_v", DATETIME_2, UTC)
			setEnumNullable("nullable_enum8_v", OrderStatus.CANCELLED)

			// arrays
			setBoolArray("bool_array_v", listOf(true, false))
			setInt8Array("int8_array_v", listOf(Byte.MIN_VALUE, Byte.MAX_VALUE))
			setInt16Array("int16_array_v", listOf(Short.MIN_VALUE, Short.MAX_VALUE))
			setInt32Array("int32_array_v", listOf(Int.MIN_VALUE, Int.MAX_VALUE))
			setInt64Array("int64_array_v", listOf(Long.MIN_VALUE, Long.MAX_VALUE))
			setInt128Array("int128_array_v", listOf(INT128_MIN, INT128_MAX))
			setInt256Array("int256_array_v", listOf(INT256_MIN, INT256_MAX))
			setUInt8Array("uint8_array_v", listOf(0, 255))
			setUInt16Array("uint16_array_v", listOf(0, 65535))
			setUInt32Array("uint32_array_v", listOf(0, UINT32_MAX))
			setUInt64Array("uint64_array_v", listOf(0L, UINT64_MAX))
			setUInt128Array("uint128_array_v", listOf(BigInteger.ZERO, UINT128_MAX))
			setUInt256Array("uint256_array_v", listOf(BigInteger.ZERO, UINT256_MAX))
			setFloat32Array("float32_array_v", listOf(2.5f, -0.5f))
			setFloat64Array("float64_array_v", listOf(2.5, -0.25))
			setDecimalArray("decimal_array_v", listOf(BigDecimal("1.2345"), BigDecimal("-6.7891")))
			setStringArray("string_array_v", listOf("plain", "with 'quote'"))
			setDateArray("date_array_v", listOf(DATE_1, DATE_2), UTC)
			setDateTimeArray("datetime_array_v", listOf(DATETIME_1, DATETIME_2), UTC)
			setEnumArray("enum8_array_v", listOf(OrderStatus.CREATED, OrderStatus.PAID))
			setStringArray("low_cardinality_array_v", listOf("lc_a", "lc_b"))

			// arrays of nullable elements — [value, null] in row 1, [null] in row 2
			setBoolNullableArray("bool_nullable_array_v", listOf(true, null))
			setInt8NullableArray("int8_nullable_array_v", listOf(Byte.MAX_VALUE, null))
			setInt16NullableArray("int16_nullable_array_v", listOf(Short.MAX_VALUE, null))
			setInt32NullableArray("int32_nullable_array_v", listOf(Int.MAX_VALUE, null))
			setInt64NullableArray("int64_nullable_array_v", listOf(Long.MAX_VALUE, null))
			setInt128NullableArray("int128_nullable_array_v", listOf(INT128_MAX, null))
			setInt256NullableArray("int256_nullable_array_v", listOf(INT256_MAX, null))
			setUInt8NullableArray("uint8_nullable_array_v", listOf(255, null))
			setUInt16NullableArray("uint16_nullable_array_v", listOf(65535, null))
			setUInt32NullableArray("uint32_nullable_array_v", listOf(UINT32_MAX, null))
			setUInt64NullableArray("uint64_nullable_array_v", listOf(UINT64_MAX, null))
			setUInt128NullableArray("uint128_nullable_array_v", listOf(UINT128_MAX, null))
			setUInt256NullableArray("uint256_nullable_array_v", listOf(UINT256_MAX, null))
			setFloat32NullableArray("float32_nullable_array_v", listOf(2.5f, null))
			setFloat64NullableArray("float64_nullable_array_v", listOf(-0.25, null))
			setDecimalNullableArray("decimal_nullable_array_v", listOf(BigDecimal("1.2345"), null))
			setStringNullableArray("string_nullable_array_v", listOf("x", null))
			setDateNullableArray("date_nullable_array_v", listOf(DATE_1, null), UTC)
			setDateTimeNullableArray("datetime_nullable_array_v", listOf(DATETIME_1, null), UTC)
			setEnumNullableArray("enum8_nullable_array_v", listOf(OrderStatus.PAID, null))

			// maps
			setMapStringToString("map_string_string_v", mapOf("k1" to "v1", "k2" to "with 'quote'"))
			setMapStringToNullableString("map_string_nullable_v", mapOf("k" to "v", "n" to null))
		}

		val row2 = TypedValues().apply {
			setInt32("id", 2)

			// scalars — opposite values
			setBool("bool_v", false)
			setInt8("int8_v", Byte.MAX_VALUE)
			setInt16("int16_v", Short.MAX_VALUE)
			setInt32("int32_v", Int.MAX_VALUE)
			setInt64("int64_v", Long.MAX_VALUE)
			setInt128("int128_v", INT128_MAX)
			setInt256("int256_v", INT256_MAX)
			setUInt8("uint8_v", 255)
			setUInt16("uint16_v", 65535)
			setUInt32("uint32_v", UINT32_MAX)
			setUInt64("uint64_v", UINT64_MAX)
			setUInt128("uint128_v", UINT128_MAX)
			setUInt256("uint256_v", UINT256_MAX)
			setFloat32("float32_v", -0.5f)
			setFloat64("float64_v", -0.25)
			setDecimal("decimal_v", BigDecimal("-0.0001"))
			setString("string_v", "")
			setString("fixed_string_v", "wxyz")
			setString("low_cardinality_v", "lc_value")
			setDate("date_v", DATE_2, UTC)
			setDateTime("datetime_v", DATETIME_2, UTC)
			setEnum("enum8_v", OrderStatus.CREATED)
			setEnum("enum16_v", OrderStatus.CANCELLED)

			// nullable scalars — all null
			setBoolNullable("nullable_bool_v", null)
			setInt8Nullable("nullable_int8_v", null)
			setInt16Nullable("nullable_int16_v", null)
			setInt32Nullable("nullable_int32_v", null)
			setInt64Nullable("nullable_int64_v", null)
			setInt128Nullable("nullable_int128_v", null)
			setInt256Nullable("nullable_int256_v", null)
			setUInt8Nullable("nullable_uint8_v", null)
			setUInt16Nullable("nullable_uint16_v", null)
			setUInt32Nullable("nullable_uint32_v", null)
			setUInt64Nullable("nullable_uint64_v", null)
			setUInt128Nullable("nullable_uint128_v", null)
			setUInt256Nullable("nullable_uint256_v", null)
			setFloat32Nullable("nullable_float32_v", null)
			setFloat64Nullable("nullable_float64_v", null)
			setDecimalNullable("nullable_decimal_v", null)
			setStringNullable("nullable_string_v", null)
			setDateNullable("nullable_date_v", null, UTC)
			setDateTimeNullable("nullable_datetime_v", null, UTC)
			setEnumNullable("nullable_enum8_v", null)

			// arrays — all empty
			setBoolArray("bool_array_v", emptyList())
			setInt8Array("int8_array_v", emptyList())
			setInt16Array("int16_array_v", emptyList())
			setInt32Array("int32_array_v", emptyList())
			setInt64Array("int64_array_v", emptyList())
			setInt128Array("int128_array_v", emptyList())
			setInt256Array("int256_array_v", emptyList())
			setUInt8Array("uint8_array_v", emptyList())
			setUInt16Array("uint16_array_v", emptyList())
			setUInt32Array("uint32_array_v", emptyList())
			setUInt64Array("uint64_array_v", emptyList())
			setUInt128Array("uint128_array_v", emptyList())
			setUInt256Array("uint256_array_v", emptyList())
			setFloat32Array("float32_array_v", emptyList())
			setFloat64Array("float64_array_v", emptyList())
			setDecimalArray("decimal_array_v", emptyList())
			setStringArray("string_array_v", emptyList())
			setDateArray("date_array_v", emptyList(), UTC)
			setDateTimeArray("datetime_array_v", emptyList(), UTC)
			setEnumArray("enum8_array_v", emptyList<OrderStatus>())
			setStringArray("low_cardinality_array_v", emptyList())

			// arrays of nullable elements — single null element
			setBoolNullableArray("bool_nullable_array_v", listOf(null))
			setInt8NullableArray("int8_nullable_array_v", listOf(null))
			setInt16NullableArray("int16_nullable_array_v", listOf(null))
			setInt32NullableArray("int32_nullable_array_v", listOf(null))
			setInt64NullableArray("int64_nullable_array_v", listOf(null))
			setInt128NullableArray("int128_nullable_array_v", listOf(null))
			setInt256NullableArray("int256_nullable_array_v", listOf(null))
			setUInt8NullableArray("uint8_nullable_array_v", listOf(null))
			setUInt16NullableArray("uint16_nullable_array_v", listOf(null))
			setUInt32NullableArray("uint32_nullable_array_v", listOf(null))
			setUInt64NullableArray("uint64_nullable_array_v", listOf(null))
			setUInt128NullableArray("uint128_nullable_array_v", listOf(null))
			setUInt256NullableArray("uint256_nullable_array_v", listOf(null))
			setFloat32NullableArray("float32_nullable_array_v", listOf(null))
			setFloat64NullableArray("float64_nullable_array_v", listOf(null))
			setDecimalNullableArray("decimal_nullable_array_v", listOf(null))
			setStringNullableArray("string_nullable_array_v", listOf(null))
			setDateNullableArray("date_nullable_array_v", listOf(null), UTC)
			setDateTimeNullableArray("datetime_nullable_array_v", listOf(null), UTC)
			setEnumNullableArray("enum8_nullable_array_v", listOf<OrderStatus?>(null))

			// maps
			setMapStringToString("map_string_string_v", emptyMap())
			setMapStringToNullableString("map_string_nullable_v", emptyMap())
		}

		typedClient.insert(host, TABLE, listOf(row1, row2))

		typedClient.select(host, "select * from $TABLE order by id").use { response ->
			val rows = response.toList()
			assertEquals(2, rows.size)

			val r1 = rows[0]
			assertEquals(1, r1.getInt32("id"))

			// scalars
			assertEquals(true, r1.getBool("bool_v"))
			assertEquals(Byte.MIN_VALUE, r1.getInt8("int8_v"))
			assertEquals(Short.MIN_VALUE, r1.getInt16("int16_v"))
			assertEquals(Int.MIN_VALUE, r1.getInt32("int32_v"))
			assertEquals(Long.MIN_VALUE, r1.getInt64("int64_v"))
			assertEquals(INT128_MIN, r1.getInt128("int128_v"))
			assertEquals(INT256_MIN, r1.getInt256("int256_v"))
			assertEquals(0, r1.getUInt8("uint8_v"))
			assertEquals(0, r1.getUInt16("uint16_v"))
			assertEquals(0, r1.getUInt32("uint32_v"))
			assertEquals(0L, r1.getUInt64("uint64_v"))
			assertEquals(BigInteger.ZERO, r1.getUInt128("uint128_v"))
			assertEquals(BigInteger.ZERO, r1.getUInt256("uint256_v"))
			assertEquals(2.5f, r1.getFloat32("float32_v"))
			assertEquals(2.5, r1.getFloat64("float64_v"))
			assertEquals(BigDecimal("12345.6789"), r1.getDecimal("decimal_v"))
			assertEquals(STRING_WITH_ESCAPES, r1.getString("string_v"))
			assertEquals("abcd", r1.getString("fixed_string_v"))
			assertEquals("lc_value", r1.getString("low_cardinality_v"))
			assertEquals(DATE_1, r1.getDate("date_v"))
			assertEquals(DATETIME_1, r1.getDateTime("datetime_v"))
			assertEquals(OrderStatus.PAID, r1.getEnum("enum8_v", OrderStatus::class.java))
			assertEquals(OrderStatus.PAID, r1.getEnum("enum16_v", OrderStatus::class.java))

			// nullable scalars
			assertEquals(true, r1.getBoolNullable("nullable_bool_v"))
			assertEquals(Byte.MAX_VALUE, r1.getInt8Nullable("nullable_int8_v"))
			assertEquals(Short.MAX_VALUE, r1.getInt16Nullable("nullable_int16_v"))
			assertEquals(Int.MAX_VALUE, r1.getInt32Nullable("nullable_int32_v"))
			assertEquals(Long.MAX_VALUE, r1.getInt64Nullable("nullable_int64_v"))
			assertEquals(INT128_MAX, r1.getInt128Nullable("nullable_int128_v"))
			assertEquals(INT256_MAX, r1.getInt256Nullable("nullable_int256_v"))
			assertEquals(255, r1.getUInt8Nullable("nullable_uint8_v"))
			assertEquals(65535, r1.getUInt16Nullable("nullable_uint16_v"))
			assertEquals(UINT32_MAX, r1.getUInt32Nullable("nullable_uint32_v"))
			assertEquals(UINT64_MAX, r1.getUInt64Nullable("nullable_uint64_v"))
			assertEquals(UINT128_MAX, r1.getUInt128Nullable("nullable_uint128_v"))
			assertEquals(UINT256_MAX, r1.getUInt256Nullable("nullable_uint256_v"))
			assertEquals(-0.5f, r1.getFloat32Nullable("nullable_float32_v"))
			assertEquals(-0.25, r1.getFloat64Nullable("nullable_float64_v"))
			assertEquals(BigDecimal("-0.0001"), r1.getDecimalNullable("nullable_decimal_v"))
			assertEquals("present", r1.getStringNullable("nullable_string_v"))
			assertEquals(DATE_2, r1.getDateNullable("nullable_date_v"))
			assertEquals(DATETIME_2, r1.getDateTimeNullable("nullable_datetime_v"))
			assertEquals(OrderStatus.CANCELLED, r1.getNullableEnum("nullable_enum8_v", OrderStatus::class.java))

			// arrays
			assertEquals(listOf(true, false), r1.getBoolArray("bool_array_v"))
			assertEquals(listOf(Byte.MIN_VALUE, Byte.MAX_VALUE), r1.getInt8Array("int8_array_v"))
			assertEquals(listOf(Short.MIN_VALUE, Short.MAX_VALUE), r1.getInt16Array("int16_array_v"))
			assertEquals(listOf(Int.MIN_VALUE, Int.MAX_VALUE), r1.getInt32Array("int32_array_v"))
			assertEquals(listOf(Long.MIN_VALUE, Long.MAX_VALUE), r1.getInt64Array("int64_array_v"))
			assertEquals(listOf(INT128_MIN, INT128_MAX), r1.getInt128Array("int128_array_v"))
			assertEquals(listOf(INT256_MIN, INT256_MAX), r1.getInt256Array("int256_array_v"))
			assertEquals(listOf(0, 255), r1.getUInt8Array("uint8_array_v"))
			assertEquals(listOf(0, 65535), r1.getUInt16Array("uint16_array_v"))
			assertEquals(listOf(0, UINT32_MAX), r1.getUInt32Array("uint32_array_v"))
			assertEquals(listOf(0L, UINT64_MAX), r1.getUInt64Array("uint64_array_v"))
			assertEquals(listOf(BigInteger.ZERO, UINT128_MAX), r1.getUInt128Array("uint128_array_v"))
			assertEquals(listOf(BigInteger.ZERO, UINT256_MAX), r1.getUInt256Array("uint256_array_v"))
			assertEquals(listOf(2.5f, -0.5f), r1.getFloat32Array("float32_array_v"))
			assertEquals(listOf(2.5, -0.25), r1.getFloat64Array("float64_array_v"))
			assertEquals(listOf(BigDecimal("1.2345"), BigDecimal("-6.7891")), r1.getDecimalArray("decimal_array_v"))
			assertEquals(listOf("plain", "with 'quote'"), r1.getStringArray("string_array_v"))
			assertEquals(listOf(DATE_1, DATE_2), r1.getDateArray("date_array_v"))
			assertEquals(listOf(DATETIME_1, DATETIME_2), r1.getDateTimeArray("datetime_array_v"))
			assertEquals(listOf(OrderStatus.CREATED, OrderStatus.PAID), r1.getEnumArray("enum8_array_v", OrderStatus::class.java))
			assertEquals(listOf("lc_a", "lc_b"), r1.getStringArray("low_cardinality_array_v"))

			// arrays of nullable elements
			assertEquals(listOf(true, null), r1.getBoolNullableArray("bool_nullable_array_v"))
			assertEquals(listOf(Byte.MAX_VALUE, null), r1.getInt8NullableArray("int8_nullable_array_v"))
			assertEquals(listOf(Short.MAX_VALUE, null), r1.getInt16NullableArray("int16_nullable_array_v"))
			assertEquals(listOf(Int.MAX_VALUE, null), r1.getInt32NullableArray("int32_nullable_array_v"))
			assertEquals(listOf(Long.MAX_VALUE, null), r1.getInt64NullableArray("int64_nullable_array_v"))
			assertEquals(listOf(INT128_MAX, null), r1.getInt128NullableArray("int128_nullable_array_v"))
			assertEquals(listOf(INT256_MAX, null), r1.getInt256NullableArray("int256_nullable_array_v"))
			assertEquals(listOf(255, null), r1.getUInt8NullableArray("uint8_nullable_array_v"))
			assertEquals(listOf(65535, null), r1.getUInt16NullableArray("uint16_nullable_array_v"))
			assertEquals(listOf(UINT32_MAX, null), r1.getUInt32NullableArray("uint32_nullable_array_v"))
			assertEquals(listOf(UINT64_MAX, null), r1.getUInt64NullableArray("uint64_nullable_array_v"))
			assertEquals(listOf(UINT128_MAX, null), r1.getUInt128NullableArray("uint128_nullable_array_v"))
			assertEquals(listOf(UINT256_MAX, null), r1.getUInt256NullableArray("uint256_nullable_array_v"))
			assertEquals(listOf(2.5f, null), r1.getFloat32NullableArray("float32_nullable_array_v"))
			assertEquals(listOf(-0.25, null), r1.getFloat64NullableArray("float64_nullable_array_v"))
			assertEquals(listOf(BigDecimal("1.2345"), null), r1.getDecimalNullableArray("decimal_nullable_array_v"))
			assertEquals(listOf("x", null), r1.getStringNullableArray("string_nullable_array_v"))
			assertEquals(listOf(DATE_1, null), r1.getDateNullableArray("date_nullable_array_v"))
			assertEquals(listOf(DATETIME_1, null), r1.getDateTimeNullableArray("datetime_nullable_array_v"))
			assertEquals(listOf(OrderStatus.PAID, null), r1.getEnumNullableArray("enum8_nullable_array_v", OrderStatus::class.java))

			// maps
			assertEquals(mapOf("k1" to "v1", "k2" to "with 'quote'"), r1.getMapStringString("map_string_string_v"))
			assertEquals(mapOf("k" to "v", "n" to null), r1.getMapStringNullableString("map_string_nullable_v"))

			val r2 = rows[1]
			assertEquals(2, r2.getInt32("id"))

			// scalars — opposite values
			assertEquals(false, r2.getBool("bool_v"))
			assertEquals(Byte.MAX_VALUE, r2.getInt8("int8_v"))
			assertEquals(Short.MAX_VALUE, r2.getInt16("int16_v"))
			assertEquals(Int.MAX_VALUE, r2.getInt32("int32_v"))
			assertEquals(Long.MAX_VALUE, r2.getInt64("int64_v"))
			assertEquals(INT128_MAX, r2.getInt128("int128_v"))
			assertEquals(INT256_MAX, r2.getInt256("int256_v"))
			assertEquals(255, r2.getUInt8("uint8_v"))
			assertEquals(65535, r2.getUInt16("uint16_v"))
			assertEquals(UINT32_MAX, r2.getUInt32("uint32_v"))
			assertEquals(UINT64_MAX, r2.getUInt64("uint64_v"))
			assertEquals(UINT128_MAX, r2.getUInt128("uint128_v"))
			assertEquals(UINT256_MAX, r2.getUInt256("uint256_v"))
			assertEquals(-0.5f, r2.getFloat32("float32_v"))
			assertEquals(-0.25, r2.getFloat64("float64_v"))
			assertEquals(BigDecimal("-0.0001"), r2.getDecimal("decimal_v"))
			assertEquals("", r2.getString("string_v"))
			assertEquals("wxyz", r2.getString("fixed_string_v"))
			assertEquals("lc_value", r2.getString("low_cardinality_v"))
			assertEquals(DATE_2, r2.getDate("date_v"))
			assertEquals(DATETIME_2, r2.getDateTime("datetime_v"))
			assertEquals(OrderStatus.CREATED, r2.getEnum("enum8_v", OrderStatus::class.java))
			assertEquals(OrderStatus.CANCELLED, r2.getEnum("enum16_v", OrderStatus::class.java))

			// nullable scalars — all null
			assertNull(r2.getBoolNullable("nullable_bool_v"))
			assertNull(r2.getInt8Nullable("nullable_int8_v"))
			assertNull(r2.getInt16Nullable("nullable_int16_v"))
			assertNull(r2.getInt32Nullable("nullable_int32_v"))
			assertNull(r2.getInt64Nullable("nullable_int64_v"))
			assertNull(r2.getInt128Nullable("nullable_int128_v"))
			assertNull(r2.getInt256Nullable("nullable_int256_v"))
			assertNull(r2.getUInt8Nullable("nullable_uint8_v"))
			assertNull(r2.getUInt16Nullable("nullable_uint16_v"))
			assertNull(r2.getUInt32Nullable("nullable_uint32_v"))
			assertNull(r2.getUInt64Nullable("nullable_uint64_v"))
			assertNull(r2.getUInt128Nullable("nullable_uint128_v"))
			assertNull(r2.getUInt256Nullable("nullable_uint256_v"))
			assertNull(r2.getFloat32Nullable("nullable_float32_v"))
			assertNull(r2.getFloat64Nullable("nullable_float64_v"))
			assertNull(r2.getDecimalNullable("nullable_decimal_v"))
			assertNull(r2.getStringNullable("nullable_string_v"))
			assertNull(r2.getDateNullable("nullable_date_v"))
			assertNull(r2.getDateTimeNullable("nullable_datetime_v"))
			assertNull(r2.getNullableEnum("nullable_enum8_v", OrderStatus::class.java))

			// arrays — all empty
			assertEquals(emptyList<Boolean>(), r2.getBoolArray("bool_array_v"))
			assertEquals(emptyList<Byte>(), r2.getInt8Array("int8_array_v"))
			assertEquals(emptyList<Short>(), r2.getInt16Array("int16_array_v"))
			assertEquals(emptyList<Int>(), r2.getInt32Array("int32_array_v"))
			assertEquals(emptyList<Long>(), r2.getInt64Array("int64_array_v"))
			assertEquals(emptyList<BigInteger>(), r2.getInt128Array("int128_array_v"))
			assertEquals(emptyList<BigInteger>(), r2.getInt256Array("int256_array_v"))
			assertEquals(emptyList<Int>(), r2.getUInt8Array("uint8_array_v"))
			assertEquals(emptyList<Int>(), r2.getUInt16Array("uint16_array_v"))
			assertEquals(emptyList<Int>(), r2.getUInt32Array("uint32_array_v"))
			assertEquals(emptyList<Long>(), r2.getUInt64Array("uint64_array_v"))
			assertEquals(emptyList<BigInteger>(), r2.getUInt128Array("uint128_array_v"))
			assertEquals(emptyList<BigInteger>(), r2.getUInt256Array("uint256_array_v"))
			assertEquals(emptyList<Float>(), r2.getFloat32Array("float32_array_v"))
			assertEquals(emptyList<Double>(), r2.getFloat64Array("float64_array_v"))
			assertEquals(emptyList<BigDecimal>(), r2.getDecimalArray("decimal_array_v"))
			assertEquals(emptyList<String>(), r2.getStringArray("string_array_v"))
			assertEquals(emptyList<java.util.Date>(), r2.getDateArray("date_array_v"))
			assertEquals(emptyList<java.util.Date>(), r2.getDateTimeArray("datetime_array_v"))
			assertEquals(emptyList<OrderStatus>(), r2.getEnumArray("enum8_array_v", OrderStatus::class.java))
			assertEquals(emptyList<String>(), r2.getStringArray("low_cardinality_array_v"))

			// arrays of nullable elements — single null element
			assertEquals(listOf(null), r2.getBoolNullableArray("bool_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt8NullableArray("int8_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt16NullableArray("int16_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt32NullableArray("int32_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt64NullableArray("int64_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt128NullableArray("int128_nullable_array_v"))
			assertEquals(listOf(null), r2.getInt256NullableArray("int256_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt8NullableArray("uint8_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt16NullableArray("uint16_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt32NullableArray("uint32_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt64NullableArray("uint64_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt128NullableArray("uint128_nullable_array_v"))
			assertEquals(listOf(null), r2.getUInt256NullableArray("uint256_nullable_array_v"))
			assertEquals(listOf(null), r2.getFloat32NullableArray("float32_nullable_array_v"))
			assertEquals(listOf(null), r2.getFloat64NullableArray("float64_nullable_array_v"))
			assertEquals(listOf(null), r2.getDecimalNullableArray("decimal_nullable_array_v"))
			assertEquals(listOf(null), r2.getStringNullableArray("string_nullable_array_v"))
			assertEquals(listOf(null), r2.getDateNullableArray("date_nullable_array_v"))
			assertEquals(listOf(null), r2.getDateTimeNullableArray("datetime_nullable_array_v"))
			assertEquals(listOf(null), r2.getEnumNullableArray("enum8_nullable_array_v", OrderStatus::class.java))

			// maps
			assertEquals(emptyMap<String, String>(), r2.getMapStringString("map_string_string_v"))
			assertEquals(emptyMap<String, String?>(), r2.getMapStringNullableString("map_string_nullable_v"))
		}
	}

	companion object {
		private const val TABLE = "typed_client_test"

		private val UTC = TimeZone.getTimeZone("UTC")

		private val DATE_1 = Convert.Date.toValue("2024-03-15", UTC)
		private val DATE_2 = Convert.Date.toValue("1999-12-31", UTC)
		private val DATETIME_1 = Convert.DateTime.toValue("2024-03-15 10:20:30", UTC)
		private val DATETIME_2 = Convert.DateTime.toValue("1999-12-31 23:59:59", UTC)

		private val UINT32_MAX = Integer.parseUnsignedInt("4294967295")
		private val UINT64_MAX = java.lang.Long.parseUnsignedLong("18446744073709551615")
		private val UINT128_MAX = BigInteger("340282366920938463463374607431768211455")
		private val UINT256_MAX = BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935")
		private val INT128_MAX = BigInteger("170141183460469231731687303715884105727")
		private val INT128_MIN = BigInteger("-170141183460469231731687303715884105728")
		private val INT256_MAX = BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819967")
		private val INT256_MIN = BigInteger("-57896044618658097711785492504343953926634992332820282019728792003956564819968")

		private const val STRING_WITH_ESCAPES = "it's a 'quoted' \\ backslash, ünïcödé 🚀"
	}
}

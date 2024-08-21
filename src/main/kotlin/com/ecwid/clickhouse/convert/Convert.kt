package com.ecwid.clickhouse.convert

import java.math.BigDecimal
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.*

object Convert {

	object Bool {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBoolean()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBoolean()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Boolean) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Boolean?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Boolean>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Boolean?>) = array.map(::fromNullableValue)
	}

	object Int8 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toByte()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toByte()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Byte) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Byte?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Byte>) = array.map(::fromValue)

		@JvmStatic
		fun fromArray(array: ByteArray) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Byte?>) = array.map(::fromNullableValue)
	}

	object Int16 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toShort()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toShort()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Short) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Short?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Short>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Short?>) = array.map(::fromNullableValue)
	}

	object Int32 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toInt()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toInt()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Int) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Int?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Int>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Int?>) = array.map(::fromNullableValue)
	}

	object Int64 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toLong()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toLong()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Long) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Long?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Long>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Long?>) = array.map(::fromNullableValue)
	}

	object Int128 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBigInteger()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBigInteger()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: BigInteger) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: BigInteger?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<BigInteger>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<BigInteger?>) = array.map(::fromNullableValue)
	}

	object Int256 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBigInteger()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBigInteger()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: BigInteger) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: BigInteger?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<BigInteger>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<BigInteger?>) = array.map(::fromNullableValue)
	}

	object UInt32 {
		@JvmStatic
		fun toValue(str: String?) = Integer.parseUnsignedInt(requireNotNull(str))

		@JvmStatic
		fun toNullableValue(str: String?) = str?.let { Integer.parseUnsignedInt(str) }

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Int) = Integer.toUnsignedString(value)

		@JvmStatic
		fun fromNullableValue(value: Int?) = value?.let { Integer.toUnsignedString(value) }

		@JvmStatic
		fun fromArray(array: List<Int>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Int?>) = array.map(::fromNullableValue)
	}

	object UInt64 {
		@JvmStatic
		fun toValue(str: String?) = java.lang.Long.parseUnsignedLong(requireNotNull(str))

		@JvmStatic
		fun toNullableValue(str: String?) = str?.let { java.lang.Long.parseUnsignedLong(str) }

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Long) = java.lang.Long.toUnsignedString(value)

		@JvmStatic
		fun fromNullableValue(value: Long?) = value?.let { java.lang.Long.toUnsignedString(value) }

		@JvmStatic
		fun fromArray(array: List<Long>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Long?>) = array.map(::fromNullableValue)
	}

	object UInt128 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBigInteger()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBigInteger()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: BigInteger) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: BigInteger?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<BigInteger>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<BigInteger?>) = array.map(::fromNullableValue)
	}

	object UInt256 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBigInteger()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBigInteger()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: BigInteger) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: BigInteger?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<BigInteger>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<BigInteger?>) = array.map(::fromNullableValue)
	}

	object Float32 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toFloat()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toFloat()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Float) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Float?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Float>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Float?>) = array.map(::fromNullableValue)
	}

	object Float64 {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toDouble()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toDouble()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: Double) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: Double?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<Double>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<Double?>) = array.map(::fromNullableValue)
	}

	object Decimal {
		@JvmStatic
		fun toValue(str: String?) = requireNotNull(str).toBigDecimal()

		@JvmStatic
		fun toNullableValue(str: String?) = str?.toBigDecimal()

		@JvmStatic
		fun toArray(array: List<String?>) = array.map(::toValue)

		@JvmStatic
		fun toNullableArray(array: List<String?>) = array.map(::toNullableValue)

		@JvmStatic
		fun fromValue(value: BigDecimal) = value.toString()

		@JvmStatic
		fun fromNullableValue(value: BigDecimal?) = value?.toString()

		@JvmStatic
		fun fromArray(array: List<BigDecimal>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<BigDecimal?>) = array.map(::fromNullableValue)
	}

	object Str {
		@JvmStatic
		fun fromValue(str: String) = escapeAndQuoteString(str)

		@JvmStatic
		fun fromNullableValue(str: String?) = str?.let { escapeAndQuoteString(it) }

		@JvmStatic
		fun fromArray(array: List<String>) = array.map(::fromValue)

		@JvmStatic
		fun fromNullableArray(array: List<String?>) = array.map(::fromNullableValue)
	}

	object DateTime {
		@JvmStatic
		fun toValue(str: String?, timeZone: TimeZone) = convertFromString(requireNotNull(str), timeZone)

		@JvmStatic
		fun toNullableValue(str: String?, timeZone: TimeZone) = str?.let { convertFromString(str, timeZone) }

		@JvmStatic
		fun toArray(array: List<String?>, timeZone: TimeZone) = array.map { toValue(it, timeZone) }

		@JvmStatic
		fun toNullableArray(array: List<String?>, timeZone: TimeZone) = array.map { toNullableValue(it, timeZone) }

		@JvmStatic
		fun fromValue(value: java.util.Date, timeZone: TimeZone) = convertToString(value, timeZone)

		@JvmStatic
		fun fromNullableValue(value: java.util.Date?, timeZone: TimeZone) =
			value?.let { convertToString(value, timeZone) }

		@JvmStatic
		fun fromArray(array: List<java.util.Date>, timeZone: TimeZone) = array.map { fromValue(it, timeZone) }

		@JvmStatic
		fun fromNullableArray(array: List<java.util.Date?>, timeZone: TimeZone) =
			array.map { fromNullableValue(it, timeZone) }

		private val DATETIME_FORMAT = ThreadLocal.withInitial {
			SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		}

		private fun convertFromString(string: String, timeZone: TimeZone): java.util.Date {
			val format = DATETIME_FORMAT.get()
			format.timeZone = timeZone
			return format.parse(string)
		}

		private fun convertToString(value: java.util.Date, timeZone: TimeZone): String {
			val format = DATETIME_FORMAT.get()
			format.timeZone = timeZone
			return format.format(value)
		}
	}

	object Date {
		@JvmStatic
		fun toValue(str: String?, timeZone: TimeZone) = convertFromString(requireNotNull(str), timeZone)

		@JvmStatic
		fun toNullableValue(str: String?, timeZone: TimeZone) = str?.let { convertFromString(str, timeZone) }

		@JvmStatic
		fun toArray(array: List<String?>, timeZone: TimeZone) = array.map { toValue(it, timeZone) }

		@JvmStatic
		fun toNullableArray(array: List<String?>, timeZone: TimeZone) = array.map { toNullableValue(it, timeZone) }

		@JvmStatic
		fun fromValue(value: java.util.Date, timeZone: TimeZone) = convertToString(value, timeZone)

		@JvmStatic
		fun fromNullableValue(value: java.util.Date?, timeZone: TimeZone) =
			value?.let { convertToString(value, timeZone) }

		@JvmStatic
		fun fromArray(array: List<java.util.Date>, timeZone: TimeZone) = array.map { fromValue(it, timeZone) }

		@JvmStatic
		fun fromNullableArray(array: List<java.util.Date?>, timeZone: TimeZone) =
			array.map { fromNullableValue(it, timeZone) }

		private val DATE_FORMAT = ThreadLocal.withInitial {
			SimpleDateFormat("yyyy-MM-dd")
		}

		private fun convertFromString(string: String, timeZone: TimeZone): java.util.Date {
			val format = DATE_FORMAT.get()
			format.timeZone = timeZone
			return format.parse(string)
		}

		private fun convertToString(value: java.util.Date, timeZone: TimeZone): String {
			val format = DATE_FORMAT.get()
			format.timeZone = timeZone
			return format.format(value)
		}
	}

	object Enum {
		@JvmStatic
		fun <T : kotlin.Enum<T>> toValue(str: String?, clazz: Class<T>) =
			java.lang.Enum.valueOf(clazz, requireNotNull(str))

		@JvmStatic
		fun <T : kotlin.Enum<T>> toNullableValue(str: String?, clazz: Class<T>) =
			str?.let { java.lang.Enum.valueOf(clazz, str) }

		@JvmStatic
		fun <T : kotlin.Enum<T>> toArray(array: List<String?>, clazz: Class<T>) = array.map { toValue(it, clazz) }

		@JvmStatic
		fun <T : kotlin.Enum<T>> toNullableArray(array: List<String?>, clazz: Class<T>) =
			array.map { toNullableValue(it, clazz) }

		@JvmStatic
		fun <T : kotlin.Enum<T>> fromValue(value: T?) = requireNotNull(value).name

		@JvmStatic
		fun <T : kotlin.Enum<T>> fromNullableValue(value: T?) = value?.name

		@JvmStatic
		fun <T : kotlin.Enum<T>> fromArray(array: List<T>) = array.map { fromValue(it) }

		@JvmStatic
		fun <T : kotlin.Enum<T>> fromNullableArray(array: List<T?>) = array.map { fromNullableValue(it) }

	}

	object Map {
		@JvmStatic
		fun fromValue(map: kotlin.collections.Map<String, String?>) =
			map.map { kv ->
				val value = kv.value?.let { escapeAndQuoteString(it) } ?: "NULL"
				escapeAndQuoteString(kv.key) to value
			}.toMap()

		@JvmStatic
		fun toMapValue(map: kotlin.collections.Map<String, String?>) = fromValue(map)
	}

	private const val QUOTE: Char = '\''
	private const val BACKSLASH = '\\'

	private fun escapeAndQuoteString(str: String): String {
		// 2 symbols for quotes and 8 for possible escaping
		// it's just heuristics, no serious science behind :)
		val capacity = str.length + 10

		return buildString(capacity) {
			append(QUOTE)

			for (char in str) {
				when (char) {
					QUOTE -> {
						append(BACKSLASH)
						append(QUOTE)
					}

					BACKSLASH -> {
						append(BACKSLASH)
						append(BACKSLASH)
					}

					else -> append(char)
				}
			}
			append(QUOTE)
		}
	}
}

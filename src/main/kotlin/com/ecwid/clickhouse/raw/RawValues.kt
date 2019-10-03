package com.ecwid.clickhouse.raw

import java.util.*

class RawValues {

	private val values = TreeMap<String, String>()

	fun addScalar(field: String, value: String?) {
		values[field] = transform(value)
	}

	fun addArray(field: String, array: List<String?>) {
		values[field] = transform(array)
	}

	fun getFieldsSql(): String {
		return values.keys.joinToString(
			separator = ",",
			prefix = "(",
			postfix = ")"
		)
	}

	fun getValues(): String {
		return values.values.joinToString(
			separator = ",",
			prefix = "(",
			postfix = ")"
		)
	}

	// if String -> same string
	// if List -> ClickHouse array representation [A, B, C....]
	private fun transform(value: Any?): String {
		return when (value) {
			is String? -> value.toString()
			is List<*> -> value.joinToString(
				separator = ",",
				prefix = "[",
				postfix = "]"
			)
			else -> throw IllegalArgumentException("Can't convert unknown type into String: $value")
		}
	}

}

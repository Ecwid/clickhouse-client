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

	fun addMap(field: String, map: Map<String, String?>) {
		values[field] = transform(map)
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
	// if Map -> ClickHouse map representation {'key1':1,'key2':2....}
	private fun transform(value: Any?): String {
		return when (value) {
			is String? -> value.toString()
			is List<*> -> value.joinToString(
				separator = ",",
				prefix = "[",
				postfix = "]"
			)
			is Map<*, *> -> value.map { kv ->
				"${kv.key}:${kv.value}"
			}.joinToString(
				separator = ",",
				prefix = "{",
				postfix = "}"
			)
			else -> throw IllegalArgumentException("Can't convert unknown type into String: $value")
		}
	}

}

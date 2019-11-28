package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.Meta

data class RawRow(
	private val meta: Meta,
	private val values: List<Any?>
) {

	fun getMeta() = meta

	fun getScalarValue(columnIndex: Int): String? {
		val value = values[columnIndex]
		require(value is String?) {
			"Can't convert array $value to scalar value"
		}
		return value
	}

	fun getScalarValue(columnName: String): String? {
		val columnIndex = meta.getColumnIndex(columnName)
		return getScalarValue(columnIndex)
	}

	fun getArrayValue(columnIndex: Int): List<String?> {
		val value = values[columnIndex]

		require(value is List<*>) {
			"Can't convert scalar $value to array"
		}

		@Suppress("UNCHECKED_CAST")
		return value as List<String?>
	}

	fun getArrayValue(columnName: String): List<String?> {
		val columnIndex = meta.getColumnIndex(columnName)
		return getArrayValue(columnIndex)
	}

}

package com.ecwid.clickhouse.integration

import com.ecwid.clickhouse.raw.RawValues
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class RawClientIntegrationTest : AbstractClickHouseTest() {

	override fun generateTestData(): List<String> {
		return listOf(
			"""
			create table $TABLE (
				$COLUMN_ID Int32,
				$COLUMN_NAME Nullable(String),
				$COLUMN_BOOL Bool,
				$COLUMN_INT64 Int64,
				$COLUMN_FLOAT64 Float64,
				$COLUMN_DATE Date,
				$COLUMN_DATETIME DateTime,
				$COLUMN_TAGS Array(Nullable(String)),
				$COLUMN_ATTRS Map(String, String)
			) engine = MergeTree order by $COLUMN_ID
			""".trimIndent()
		)
	}

	@Test
	fun testInsertAndSelect() {
		// the raw layer expects values already escaped/quoted by the caller
		val row1 = RawValues().apply {
			addScalar(COLUMN_ID, "1")
			addScalar(COLUMN_NAME, "'hello'")
			addScalar(COLUMN_BOOL, "true")
			addScalar(COLUMN_INT64, "9223372036854775807")
			addScalar(COLUMN_FLOAT64, "2.5")
			addScalar(COLUMN_DATE, "'2024-03-15'")
			addScalar(COLUMN_DATETIME, "'2024-03-15 10:20:30'")
			addArray(COLUMN_TAGS, listOf("'a'", null, "'b'"))
			addMap(COLUMN_ATTRS, mapOf("'k1'" to "'v1'", "'k2'" to "'v2'"))
		}

		val row2 = RawValues().apply {
			addScalar(COLUMN_ID, "2")
			addScalar(COLUMN_NAME, null)
			addScalar(COLUMN_BOOL, "false")
			addScalar(COLUMN_INT64, "-9223372036854775808")
			addScalar(COLUMN_FLOAT64, "-0.25")
			addScalar(COLUMN_DATE, "'1999-12-31'")
			addScalar(COLUMN_DATETIME, "'1999-12-31 23:59:59'")
			addArray(COLUMN_TAGS, emptyList())
			addMap(COLUMN_ATTRS, emptyMap())
		}

		rawClient.insert(host, TABLE, listOf(row1, row2))

		rawClient.select(host, "select * from $TABLE order by $COLUMN_ID").use { response ->
			val rows = response.toList()
			assertEquals(2, rows.size)

			assertEquals("1", rows[0].getScalarValue(COLUMN_ID))
			assertEquals("hello", rows[0].getScalarValue(COLUMN_NAME))
			assertEquals("true", rows[0].getScalarValue(COLUMN_BOOL))
			assertEquals("9223372036854775807", rows[0].getScalarValue(COLUMN_INT64))
			assertEquals("2.5", rows[0].getScalarValue(COLUMN_FLOAT64))
			assertEquals("2024-03-15", rows[0].getScalarValue(COLUMN_DATE))
			assertEquals("2024-03-15 10:20:30", rows[0].getScalarValue(COLUMN_DATETIME))
			assertEquals(listOf("a", null, "b"), rows[0].getArrayValue(COLUMN_TAGS))
			assertEquals(mapOf("k1" to "v1", "k2" to "v2"), rows[0].getMapValue(COLUMN_ATTRS))

			assertEquals("2", rows[1].getScalarValue(COLUMN_ID))
			assertNull(rows[1].getScalarValue(COLUMN_NAME))
			assertEquals("false", rows[1].getScalarValue(COLUMN_BOOL))
			assertEquals("-9223372036854775808", rows[1].getScalarValue(COLUMN_INT64))
			assertEquals("-0.25", rows[1].getScalarValue(COLUMN_FLOAT64))
			assertEquals("1999-12-31", rows[1].getScalarValue(COLUMN_DATE))
			assertEquals("1999-12-31 23:59:59", rows[1].getScalarValue(COLUMN_DATETIME))
			assertEquals(emptyList<String?>(), rows[1].getArrayValue(COLUMN_TAGS))
			assertEquals(emptyMap<String, String?>(), rows[1].getMapValue(COLUMN_ATTRS))
		}
	}

	companion object {
		private const val TABLE = "raw_client_test"

		private const val COLUMN_ID = "id"
		private const val COLUMN_NAME = "name"
		private const val COLUMN_BOOL = "bool_v"
		private const val COLUMN_INT64 = "int64_v"
		private const val COLUMN_FLOAT64 = "float64_v"
		private const val COLUMN_DATE = "date_v"
		private const val COLUMN_DATETIME = "datetime_v"
		private const val COLUMN_TAGS = "tags"
		private const val COLUMN_ATTRS = "attrs"
	}
}

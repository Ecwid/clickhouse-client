package com.ecwid.clickhouse.raw

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RawValuesTest {

	@Test
	fun testAddAndSetMixedValues() {
		val rawValues = RawValues()

		rawValues.addScalar("first", "a")
		rawValues.addScalar("second", "b")
		rawValues.addScalar("third", null)
		rawValues.addScalar("second", "b")
		rawValues.addArray("", listOf("1", "2", null, "3"))
		rawValues.addMap("map", mapOf("k1" to "v1"))

		val sqlValues = rawValues.getValues()
		val expected = "([1,2,null,3],a,{k1:v1},b,null)"

		assertEquals(expected, sqlValues)
	}

}

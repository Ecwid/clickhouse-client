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

		val sqlValues = rawValues.getValues()
		val expected = "([1,2,null,3],a,b,null)"

		assertEquals(expected, sqlValues)
	}

}

package com.ecwid.clickhouse.raw

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RawValuesTest {

    @Test
    fun testAddAndSetMixedValues() {
        val rawValues = RawValues()

        rawValues.addScalar("a")
        rawValues.addScalar("b")
        rawValues.addScalar(null)
        rawValues.setScalar(1, "bb")
        rawValues.addArray(listOf("1", "2", null, "3"))

        val sqlValues = rawValues.joinRawValuesToSqlValues()
        val expected = "(a,bb,null,[1,2,null,3])"

        assertEquals(expected, sqlValues)
    }

}
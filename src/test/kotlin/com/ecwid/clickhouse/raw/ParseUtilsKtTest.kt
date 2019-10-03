package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.PlatformType
import com.ecwid.clickhouse.Type
import com.google.gson.stream.JsonReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ParseUtilsKtTest {

	@Test
	fun testReadMeta() {
		val json = """
        [
            {
                "name": "date",
                "type": "DateTime"
            },
            {
                "name": "field1",
                "type": "Int32"
            },
            {
                "name": "field2",
                "type": "Nullable(Int32)"
            },
            {
                "name": "field3",
                "type": "Nullable(String)"
            },
            {
                "name": "field4",
                "type": "Array(String)"
            }
        ]
        """.trimIndent()

		val meta = readMetaObject(JsonReader(json.reader()))

		Assertions.assertEquals(5, meta.columns.size)
		Assertions.assertEquals(Type.Platform(PlatformType.DATETIME, "DateTime"), meta.columns[0])
		Assertions.assertEquals(Type.Platform(PlatformType.INT_32, "Int32"), meta.columns[1])
		Assertions.assertEquals(Type.Platform(PlatformType.INT_32_NULLABLE, "Nullable(Int32)"), meta.columns[2])
		Assertions.assertEquals(Type.Platform(PlatformType.STRING_NULLABLE, "Nullable(String)"), meta.columns[3])
		Assertions.assertEquals(Type.Array(PlatformType.STRING, "Array(String)"), meta.columns[4])

		Assertions.assertEquals(5, meta.names.size)
		Assertions.assertEquals(0, meta.names["date"])
		Assertions.assertEquals(1, meta.names["field1"])
		Assertions.assertEquals(2, meta.names["field2"])
		Assertions.assertEquals(3, meta.names["field3"])
		Assertions.assertEquals(4, meta.names["field4"])
	}

	@Test
	fun testReadStatistics() {
		val json = """
        {
		    "elapsed": 0.0094376,
		    "rows_read": 2,
		    "bytes_read": 52
	    }
        """.trimIndent()

		val statistic = readStatistics(JsonReader(json.reader()))
		Assertions.assertEquals(statistic.elapsed, 0.0094376, 0.0001)
		Assertions.assertEquals(statistic.rowsRead, 2)
		Assertions.assertEquals(statistic.bytesRead, 52)
	}

}

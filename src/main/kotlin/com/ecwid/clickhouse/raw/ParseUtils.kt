package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.Meta
import com.ecwid.clickhouse.Statistics
import com.ecwid.clickhouse.Type
import com.ecwid.clickhouse.parseType
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken

internal fun readMetaObject(reader: JsonReader): Meta {
    // column types
    val indexes = mutableListOf<Type>()
    // column names to indexes map
    val names = mutableMapOf<String, Int>()

    reader.beginArray()
    while (reader.hasNext()) {
        reader.beginObject()
        reader.nextName() // read 'name' key
        val columnName = reader.nextString() // read 'name' value
        reader.nextName() // read 'type' key
        val columnType = parseType(reader.nextString()) // read 'type' value
        reader.endObject()

        indexes += columnType
        names[columnName] = indexes.size - 1
    }
    reader.endArray()

    return Meta(indexes, names)
}

internal fun readStatistics(reader: JsonReader): Statistics {
    var elapsed: Double? = null
    var rowsRead: Long? = null
    var bytesRead: Long? = null

    reader.beginObject()
    while (reader.hasNext()) {
        val keyName = reader.nextName()
        when (keyName) {
            "elapsed" -> elapsed = reader.nextDouble()
            "rows_read" -> rowsRead = reader.nextLong()
            "bytes_read" -> bytesRead = reader.nextLong()
        }
    }
    reader.endObject()

    requireNotNull(elapsed) { "Wrong 'statisticsBlock' section in SELECT response - no 'elapsed' key found" }
    requireNotNull(rowsRead) { "Wrong 'statisticsBlock' section in SELECT response - no 'rows_read' key found" }
    requireNotNull(bytesRead) { "Wrong 'statisticsBlock' section in SELECT response - no 'bytes_read' key found" }

    return Statistics(elapsed, rowsRead, bytesRead)
}

internal fun readRawRow(reader: JsonReader, meta: Meta): RawRow {
    val values = ArrayList<Any?>(meta.columns.size)

    reader.beginArray()
    while (reader.hasNext()) {
        val nextToken = reader.peek()

        when (nextToken) {
            JsonToken.NULL -> {
                reader.nextNull()
                values.add(null)
            }

            JsonToken.BEGIN_ARRAY -> {
                val array = arrayListOf<String?>()

                reader.beginArray()
                while (reader.hasNext()) {
                    val next = reader.peek()
                    if (next == JsonToken.NULL) {
                        reader.nextNull()
                        array.add(null)
                    } else {
                        val value = reader.nextString()
                        array.add(value)
                    }
                }
                reader.endArray()

                values.add(array)
            }

            else -> {
                val value = reader.nextString()
                values.add(value)
            }
        }
    }
    reader.endArray()

    return RawRow(meta, values)
}
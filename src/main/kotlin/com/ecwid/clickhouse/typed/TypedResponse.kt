package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.raw.RawResponse
import java.util.*

class TypedResponse(
    private val rawResponse: RawResponse,
    private val defaultTimeZone: TimeZone
) : Iterable<TypedRow>, AutoCloseable {

    override fun iterator(): Iterator<TypedRow> {
        return TypedIterator(rawResponse.getMeta(), rawResponse.iterator(), defaultTimeZone)
    }

    override fun close() {
        rawResponse.close()
    }

    fun getMeta() = rawResponse.getMeta()

    fun getStatistic() = rawResponse.getStatistic()

    fun getRows(): Long = rawResponse.getRows()

}
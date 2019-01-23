package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.typed.TypedResponse
import com.ecwid.clickhouse.typed.TypedRow

class MappedResponse<T>(
    private val typedResponse: TypedResponse,
    private val mapper: (TypedRow) -> T
) : Iterable<T>, AutoCloseable {

    override fun iterator(): Iterator<T> {
        return MappedIterator(typedResponse.iterator(), mapper)
    }

    override fun close() {
        typedResponse.close()
    }

    fun getMeta() = typedResponse.getMeta()

    fun getStatistic() = typedResponse.getStatistic()

    fun getRows(): Long = typedResponse.getRows()

}
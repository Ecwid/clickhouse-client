package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.typed.TypedRow

internal class MappedResponse<T>(
	private val typedResponse: ClickHouseResponse<TypedRow>,
	private val mapper: (TypedRow) -> T
) : ClickHouseResponse<T> {

	override fun iterator(): Iterator<T> {
		return MappedIterator(typedResponse.iterator(), mapper)
	}

	override fun close() {
		typedResponse.close()
	}

	override fun getMeta() = typedResponse.getMeta()

	override fun getStatistic() = typedResponse.getStatistic()

	override fun getRows(): Long = typedResponse.getRows()

	override fun getRowsBeforeLimitAtLeast(): Long? = typedResponse.getRowsBeforeLimitAtLeast()

}

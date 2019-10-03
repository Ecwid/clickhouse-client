package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.raw.RawRow
import java.util.*

internal class TypedResponse(
	private val rawResponse: ClickHouseResponse<RawRow>,
	private val defaultTimeZone: TimeZone
) : ClickHouseResponse<TypedRow> {

	override fun iterator(): Iterator<TypedRow> {
		return TypedIterator(rawResponse.iterator(), defaultTimeZone)
	}

	override fun close() {
		rawResponse.close()
	}

	override fun getMeta() = rawResponse.getMeta()

	override fun getStatistic() = rawResponse.getStatistic()

	override fun getRows(): Long = rawResponse.getRows()

	override fun getRowsBeforeLimitAtLeast(): Long? = rawResponse.getRowsBeforeLimitAtLeast()

}

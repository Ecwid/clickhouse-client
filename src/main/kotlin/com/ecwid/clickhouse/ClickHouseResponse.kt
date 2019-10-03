package com.ecwid.clickhouse

interface ClickHouseResponse<T> : Iterable<T>, AutoCloseable {

	fun getMeta(): Meta

	fun getStatistic(): Statistics

	fun getRows(): Long

	fun getRowsBeforeLimitAtLeast(): Long?

}

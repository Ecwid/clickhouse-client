package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.raw.ClickHouseRawClient
import com.ecwid.clickhouse.transport.HttpTransport
import com.ecwid.clickhouse.typed.ClickHouseTypedClient
import com.ecwid.clickhouse.typed.TypedRow
import com.ecwid.clickhouse.typed.TypedValues

class ClickHouseMappedClient(httpTransport: HttpTransport) {

	private val typedClient = ClickHouseTypedClient(httpTransport)

	fun <T> select(host: String, sqlQuery: String, mapper: (TypedRow) -> T): ClickHouseResponse<T> {
		val typedResponse = typedClient.select(host, sqlQuery)
		return MappedResponse(typedResponse, mapper)
	}

	fun <T> insert(host: String, table: String, values: List<T>, mapper: (T) -> TypedValues) {
		typedClient.insert(host, table, values.map(mapper))
	}

	fun getRawClient(): ClickHouseRawClient = typedClient.getRawClient()

	fun getTypedClient(): ClickHouseTypedClient = typedClient
}

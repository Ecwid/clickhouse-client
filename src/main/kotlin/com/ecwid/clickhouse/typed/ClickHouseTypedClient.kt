package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.raw.ClickHouseRawClient
import com.ecwid.clickhouse.raw.RawValues
import com.ecwid.clickhouse.transport.HttpTransport
import java.util.*

class ClickHouseTypedClient(httpTransport: HttpTransport) {

    private val defaultTimeZone = TimeZone.getTimeZone("UTC")
    private val rawClient = ClickHouseRawClient(httpTransport)

    fun select(host: String, sqlQuery: String, timeZone: TimeZone): ClickHouseResponse<TypedRow> {
        val rawResponse = rawClient.select(host, sqlQuery)
        return TypedResponse(rawResponse, timeZone)
    }

    fun select(host: String, sqlQuery: String): ClickHouseResponse<TypedRow> {
        return select(host, sqlQuery, defaultTimeZone)
    }

    fun insert(host: String, table: String, values: List<TypedValues>) {
        rawClient.insert(host, table, values.map(TypedValues::getRawValues))
    }

    fun getRawClient(): ClickHouseRawClient = rawClient

}
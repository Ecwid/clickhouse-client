package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.raw.ClickHouseRawClient
import com.ecwid.clickhouse.transport.HttpTransport
import java.util.*

class ClickHouseTypedClient(httpTransport: HttpTransport) {

    private val defaultTimeZone = TimeZone.getTimeZone("UTC")
    private val rawClient = ClickHouseRawClient(httpTransport)

    fun select(host: String, sqlQuery: String, timeZone: TimeZone): TypedResponse {
        val rawResponse = rawClient.select(host, sqlQuery)
        return TypedResponse(rawResponse, timeZone)
    }

    fun select(host: String, sqlQuery: String): TypedResponse {
        return select(host, sqlQuery, defaultTimeZone)
    }

    fun getRawClient(): ClickHouseRawClient = rawClient

}
package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.ClickHouseHttpException
import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.transport.HttpResponse
import com.ecwid.clickhouse.transport.HttpTransport
import java.net.URLEncoder

class ClickHouseRawClient(private val httpTransport: HttpTransport) {

    fun select(host: String, sqlQuery: String): ClickHouseResponse<RawRow> {
        val formattedQuery = "$sqlQuery format JSONCompact"
        val encodedQuery = URLEncoder.encode(formattedQuery, "UTF-8")
        val path = "query=$encodedQuery"
        val uri = "$host/?$path"

        val httpResponse = httpTransport.makeGetRequest(uri)
        checkResponse(httpResponse)

        return RawResponse(httpResponse)
    }

    fun executeQuery(host: String, sqlQuery: String) {
        val response = httpTransport.makePostRequest(host, sqlQuery)
        checkResponse(response)
    }

    private fun checkResponse(httpResponse: HttpResponse) {
        if (httpResponse.statusCode == 200) {
            return
        }

        // error response
        // throw exception and close response
        httpResponse.use { _ ->
            val code = httpResponse.statusCode
            val msg = httpResponse.statusLine
            val content = httpResponse.content.asString()

            throw ClickHouseHttpException(code, msg, content)
        }
    }

}
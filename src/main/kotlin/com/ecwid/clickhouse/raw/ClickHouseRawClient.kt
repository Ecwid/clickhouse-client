package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.ClickHouseHttpException
import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.transport.HttpResponse
import com.ecwid.clickhouse.transport.HttpTransport

class ClickHouseRawClient(private val httpTransport: HttpTransport) {

	fun select(host: String, sqlQuery: String): ClickHouseResponse<RawRow> {
		val queryWithFormatter = "$sqlQuery format JSONCompact"

		val httpResponse = httpTransport.makePostRequest(host, queryWithFormatter)
		checkResponse(httpResponse, sqlQuery)

		return RawResponse(httpResponse)
	}

	fun insert(host: String, table: String, values: List<RawValues>) {
		if (values.isEmpty()) {
			// no rows to insert
			return
		}

		val groupedValues = values.groupBy(RawValues::getFieldsSql, RawValues::getValues)
		groupedValues.forEach { (fieldsClause, sqlValues) ->
			val sql = sqlValues.joinToString(
				separator = ",",
				prefix = "insert into $table $fieldsClause values "
			)

			executeQuery(host, sql)
		}
	}

	fun executeQuery(host: String, sqlQuery: String) {
		val response = httpTransport.makePostRequest(host, sqlQuery)
		checkResponse(response, sqlQuery)
	}

	private fun checkResponse(httpResponse: HttpResponse, sqlQuery: String) {
		if (httpResponse.statusCode == 200) {
			return
		}

		// error response
		// throw exception and close response
		httpResponse.use { _ ->
			val code = httpResponse.statusCode
			val msg = httpResponse.statusLine
			val content = httpResponse.content.asString()

			throw ClickHouseHttpException(code, msg, content, sqlQuery)
		}
	}

}

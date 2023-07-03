package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.ClickHouseHttpException
import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.metrics.Metrics
import com.ecwid.clickhouse.transport.HttpResponse
import com.ecwid.clickhouse.transport.HttpTransport

class ClickHouseRawClient(
	private val httpTransport: HttpTransport,
	private val metrics: Metrics
) {

	fun select(host: String, sqlQuery: String): ClickHouseResponse<RawRow> {
		val queryWithFormatter = "$sqlQuery format JSONCompact"

		val requestTimer = metrics.startRequestTimer(host)
		val httpResponse = httpTransport.makePostRequest(host, queryWithFormatter)
		checkResponse(httpResponse, host, sqlQuery)

		return RawResponse(httpResponse, requestTimer)
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
		val requestTimer = metrics.startRequestTimer(host)
		val response = httpTransport.makePostRequest(host, sqlQuery)
		checkResponseAndClose(response, host, sqlQuery, requestTimer)
	}

	private fun checkResponse(httpResponse: HttpResponse, host: String, sqlQuery: String) {
		metrics.measureRequest(host, httpResponse.statusCode)

		if (httpResponse.statusCode == 200) {
			return
		}

		// error response
		// throw exception and close response
		httpResponse.use { _ ->
			val exception = convertResponseToException(httpResponse, sqlQuery)
			throw exception
		}
	}

	private fun checkResponseAndClose(httpResponse: HttpResponse, host: String, sqlQuery: String, requestTimer: AutoCloseable) {
		httpResponse.use { _ ->
			requestTimer.close()
			metrics.measureRequest(host, httpResponse.statusCode)

			if (httpResponse.statusCode == 200) {
				return
			}

			val exception = convertResponseToException(httpResponse, sqlQuery)
			throw exception
		}
	}

	private fun convertResponseToException(httpResponse: HttpResponse, sqlQuery: String): ClickHouseHttpException {
		require(httpResponse.statusCode != 200) {
			"Can't convert successful response to exception"
		}

		val code = httpResponse.statusCode
		val msg = httpResponse.statusLine
		val content = httpResponse.content.asString()

		return ClickHouseHttpException(code, msg, content, sqlQuery)
	}

}

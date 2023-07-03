package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.ClickHouseResponse
import com.ecwid.clickhouse.Meta
import com.ecwid.clickhouse.Statistics
import com.ecwid.clickhouse.transport.HttpResponse
import com.google.gson.stream.JsonReader

internal class RawResponse(
	private val httpResponse: HttpResponse,
	private val callTimer: AutoCloseable
) : ClickHouseResponse<RawRow> {

	private val jsonReader = JsonReader(httpResponse.content.asReader())
	private var iterationState = IterationState.BEFORE_ITERATION

	private val meta: Meta
	private var statistic: Statistics? = null
	private var rows: Long? = null
	private var rowsBeforeLimitAtLeast: Long? = null

	init {
		// start first iteration
		jsonReader.beginObject()

		// meta
		val metaKey = jsonReader.nextName()
		check(metaKey == "meta") {
			"'meta' object not found"
		}
		this.meta = readMetaObject(jsonReader)

		// open 'data' section in json
		val dataKey = jsonReader.nextName()
		check(dataKey == "data") {
			"'data' object not found"
		}
		jsonReader.beginArray()
		iterationState = IterationState.READY_TO_ITERATION
	}

	override fun iterator(): Iterator<RawRow> {
		check(iterationState == IterationState.READY_TO_ITERATION) {
			"You can iterate over ClickHouse response only once."
		}

		return RawIterator(jsonReader, this)
	}

	override fun close() {
		httpResponse.close()
		callTimer.close()
	}

	internal fun completeIteration() {
		// close 'data' section in json
		iterationState = IterationState.AFTER_ITERATION
		jsonReader.endArray()

		// read other sections
		while (jsonReader.hasNext()) {
			val keyName = jsonReader.nextName()
			when (keyName) {
				"rows" -> {
					this.rows = jsonReader.nextLong()
				}

				"statistics" -> {
					this.statistic = readStatistics(jsonReader)
				}

				"rows_before_limit_at_least" -> {
					this.rowsBeforeLimitAtLeast = jsonReader.nextLong()
				}
			}
		}

		// complete response reading
		jsonReader.endObject()
	}


	// setters/getters
	override fun getMeta(): Meta {
		// Meta is always available, we can read it before response iteration
		// So no checks are needed
		return meta
	}

	override fun getStatistic(): Statistics {
		check(iterationState == IterationState.AFTER_ITERATION) {
			"Statistic isn't available yet, please read all data rows first"
		}

		return requireNotNull(statistic)
	}

	override fun getRows(): Long {
		check(iterationState == IterationState.AFTER_ITERATION) {
			"Rows isn't available yet, please read all data rows first"
		}

		return requireNotNull(rows)
	}

	override fun getRowsBeforeLimitAtLeast(): Long? {
		check(iterationState == IterationState.AFTER_ITERATION) {
			"RowsBeforeLimitAtLeast isn't available yet, please read all data rows first"
		}

		return rowsBeforeLimitAtLeast
	}

}

private enum class IterationState {
	BEFORE_ITERATION,
	READY_TO_ITERATION,
	AFTER_ITERATION
}

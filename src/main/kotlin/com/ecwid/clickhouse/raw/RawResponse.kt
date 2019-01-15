package com.ecwid.clickhouse.raw

import com.ecwid.clickhouse.Meta
import com.ecwid.clickhouse.Statistics
import com.ecwid.clickhouse.transport.HttpResponse
import com.google.gson.stream.JsonReader

class RawResponse(private val httpResponse: HttpResponse) : AutoCloseable, Iterable<RawRow> {

    private val jsonReader = JsonReader(httpResponse.content.asReader())
    private var iterationAllowed = false

    private val meta: Meta
    private var statistic: Statistics? = null
    private var rows: Long? = null

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
        iterationAllowed = true
    }

    override fun iterator(): Iterator<RawRow> {
        check(iterationAllowed) {
            "You can iterate over ClickHouse response only once."
        }

        return RawIterator(jsonReader, this)
    }

    override fun close() {
        httpResponse.close()
    }

    internal fun completeIteration() {
        // close 'data' section in json
        iterationAllowed = false
        jsonReader.endArray()

        // rows section
        val rowsKey = jsonReader.nextName()
        check(rowsKey == "rows") {
            "'rows' object not found"
        }
        this.rows = jsonReader.nextLong()

        // stats
        val statsName = jsonReader.nextName()
        check(statsName == "statistics") {
            "'statistics' object not found"
        }
        this.statistic = readStatistics(jsonReader)

        jsonReader.endObject()
    }


    // setters/getters

    fun getMeta() = meta

    fun getStatistic(): Statistics {
        val stats = statistic

        checkNotNull(stats) {
            "Statistic isn't available yet, please read all data rows first"
        }

        return stats
    }

    fun getRows(): Long {
        val r = this.rows
        checkNotNull(r) {
            "Rows isn't available yet, please read all data rows first"
        }

        return r
    }
}
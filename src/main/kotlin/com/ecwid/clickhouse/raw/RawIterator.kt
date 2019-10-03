package com.ecwid.clickhouse.raw

import com.google.gson.stream.JsonReader

internal class RawIterator(
	private val jsonReader: JsonReader,
	private val rawResponse: RawResponse
) : AbstractIterator<RawRow>() {

	override fun computeNext() {
		if (jsonReader.hasNext()) {
			val next = readRawRow(jsonReader, rawResponse.getMeta())
			setNext(next)
		} else {
			done()
			rawResponse.completeIteration()
		}
	}
}

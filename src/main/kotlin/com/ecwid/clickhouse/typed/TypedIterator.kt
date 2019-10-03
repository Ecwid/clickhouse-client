package com.ecwid.clickhouse.typed

import com.ecwid.clickhouse.raw.RawRow
import java.util.*

internal class TypedIterator(
	private val rawIterator: Iterator<RawRow>,
	private val defaultTimeZone: TimeZone
) : AbstractIterator<TypedRow>() {

	override fun computeNext() {
		if (rawIterator.hasNext()) {
			val nextValue = rawIterator.next()
			val typedRow = TypedRow(defaultTimeZone, nextValue)

			setNext(typedRow)
		} else {
			done()
		}
	}

}

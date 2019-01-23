package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.typed.TypedRow

internal class MappedIterator<T>(
    private val typedIterator: Iterator<TypedRow>,
    private val mapper: (TypedRow) -> T
) : AbstractIterator<T>() {

    override fun computeNext() {
        if (typedIterator.hasNext()) {
            val typedRow = typedIterator.next()
            val mappedValue = mapper(typedRow)

            setNext(mappedValue)
        } else {
            done()
        }
    }
}
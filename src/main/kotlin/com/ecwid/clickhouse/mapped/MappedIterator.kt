package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.typed.TypedRow

internal class MappedIterator<T>(
    private val typedIterator: Iterator<TypedRow>,
    private val mapper: SelectMapper<T>
) : AbstractIterator<T>() {

    override fun computeNext() {
        if (typedIterator.hasNext()) {
            val typedRow = typedIterator.next()
            val mappedValue = mapper.map(typedRow)

            setNext(mappedValue)
        } else {
            done()
        }
    }
}
package com.ecwid.clickhouse.raw

class RawValues {

    private val values = arrayListOf<String?>()

    fun add(value: String?) {
        values += value
    }

    fun set(index: Int, value: String?) {
        values[index] = value
    }

    internal fun joinRawValuesToSqlValues(): String {
        return values.joinToString(
            prefix = "(",
            postfix = ")"
        )
    }

}
package com.ecwid.clickhouse.raw

class RawValues {

    private val values = arrayListOf<Any?>()

    fun addScalar(value: String?) {
        values.add(value)
    }

    fun addScalars(vararg values: String?) {
        values.forEach(::addScalar)
    }

    fun addArray(array: List<String?>) {
        values.add(array)
    }

    fun setScalar(index: Int, value: String?) {
        expandList(index)
        values.set(index, value)
    }

    fun setArray(index: Int, array: List<String?>) {
        expandList(index)
        values.set(index, array)
    }

    // one SQL values block in INSERT clause (A, B, C...)
    internal fun joinRawValuesToSqlValues(): String {
        return values.joinToString(
            separator = ",",
            prefix = "(",
            postfix = ")",
            transform = ::transform
        )
    }

    // if String -> same string
    // if List -> ClickHouse array representation [A, B, C....]
    private fun transform(value: Any?): String {
        return when (value) {
            is String? -> value.toString()
            is List<*> -> value.joinToString(
                separator = ",",
                prefix = "[",
                postfix = "]"
            )
            else -> throw IllegalArgumentException("Can't convert unknown type into String: $value")
        }
    }

    private fun expandList(index: Int) {
        val neededCapacity = index + 1

        values.ensureCapacity(neededCapacity)
        while (values.size < neededCapacity) {
            values.add(null)
        }
    }

}
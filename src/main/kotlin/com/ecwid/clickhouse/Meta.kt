package com.ecwid.clickhouse

data class Meta(
    val columns: List<Type>,
    val names: Map<String, Int>
) {

    fun getColumnIndex(columnName: String): Int {
        val columnIndex = names[columnName]

        return requireNotNull(columnIndex) {
            "Unknown column name '$columnName'. Known columns are ${names.keys}"
        }
    }

}


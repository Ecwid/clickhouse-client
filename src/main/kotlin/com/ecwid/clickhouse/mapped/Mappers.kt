package com.ecwid.clickhouse.mapped

import com.ecwid.clickhouse.typed.TypedRow

/**
 * Converts TypedRow to your POJO
 */
interface SelectMapper<T> {

    fun map(typedRow: TypedRow): T

}
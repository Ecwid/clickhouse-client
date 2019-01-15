package com.ecwid.clickhouse

data class Statistics(
    val elapsed: Double,
    val rowsRead: Long,
    val bytesRead: Long
)
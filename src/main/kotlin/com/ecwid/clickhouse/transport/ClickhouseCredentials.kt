package com.ecwid.clickhouse.transport

data class ClickhouseCredentials @JvmOverloads constructor(
		val username: String,
		val password: String = ""
)
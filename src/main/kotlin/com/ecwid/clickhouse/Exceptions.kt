package com.ecwid.clickhouse

open class ClickHouseException : RuntimeException {

	constructor() : super()

	constructor(message: String) : super(message)

	constructor(message: String, cause: Throwable) : super(message, cause)
}

class ClickHouseHttpException(
	val statusCode: Int,
	val statusMessage: String,
	val responseContent: String
) : ClickHouseException() {

	override val message = "HTTP $statusCode ($statusMessage): $responseContent"
}

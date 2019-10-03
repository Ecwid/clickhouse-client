package com.ecwid.clickhouse.transport

import java.io.Closeable
import java.io.InputStream
import java.io.Reader

data class HttpResponse(
	val statusCode: Int,
	val statusLine: String,
	val content: Content
) : Closeable {

	override fun close() {
		content.close()
	}
}

interface Content : Closeable {

	fun asString(): String

	fun asReader(): Reader

	fun asStream(): InputStream

}

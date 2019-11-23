package com.ecwid.clickhouse.transport

interface HttpTransport {

	fun makePostRequest(uri: String, content: String): HttpResponse

}

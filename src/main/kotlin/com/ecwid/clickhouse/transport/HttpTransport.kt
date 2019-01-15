package com.ecwid.clickhouse.transport

interface HttpTransport {

    fun makeGetRequest(uri: String): HttpResponse

    fun makePostRequest(uri: String, content: String): HttpResponse

}
package com.ecwid.clickhouse.transport.httpclient

import com.ecwid.clickhouse.transport.Content
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils
import java.io.InputStream

internal data class StringContent(val string: String) : Content {

    override fun asString() = string

    override fun asReader() = string.reader()

    override fun asStream() = string.byteInputStream()

    override fun close() {
        // nothing to close for string content
    }
}

internal data class StreamContent(val stream: InputStream, private val entity: HttpEntity) : Content {

    override fun asString() = asReader().readText()

    override fun asReader() = stream.bufferedReader(Charsets.UTF_8)

    override fun asStream() = stream

    override fun close() {
        EntityUtils.consumeQuietly(entity)
    }
}

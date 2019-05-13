package com.ecwid.clickhouse.transport.httpclient

import com.ecwid.clickhouse.ClickHouseException
import com.ecwid.clickhouse.transport.ClickhouseCredentials
import com.ecwid.clickhouse.transport.HttpResponse
import com.ecwid.clickhouse.transport.HttpTransport
import org.apache.http.HttpHeaders
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.util.EntityUtils
import org.apache.http.message.BasicHeader
import java.nio.charset.StandardCharsets
import java.util.*

private const val DEFAULT_MAX_CONNECTIONS = 100
private const val DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 50

private const val DEFAULT_CONNECTION_TIMEOUT = 5000
private const val DEFAULT_READ_TIMEOUT = 10000

class ApacheHttpClientTransport @JvmOverloads constructor(
    connectTimeoutMs: Int = DEFAULT_CONNECTION_TIMEOUT,
    readTimeoutMs: Int = DEFAULT_READ_TIMEOUT,
    credentials: ClickhouseCredentials? = null
) : HttpTransport {

    private val httpClient: HttpClient

    init {
        val connectionManager = PoolingHttpClientConnectionManager()
        connectionManager.maxTotal = DEFAULT_MAX_CONNECTIONS
        connectionManager.defaultMaxPerRoute =
            DEFAULT_MAX_PER_ROUTE_CONNECTIONS

        val requestConfig = RequestConfig.custom()
            .setConnectTimeout(connectTimeoutMs)
            .setConnectionRequestTimeout(connectTimeoutMs)
            .setSocketTimeout(readTimeoutMs)
            .build()

        val httpClientBuilder = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .setDefaultRequestConfig(requestConfig)

        if (credentials != null) {
            val encodedCredentials = Base64.getEncoder().encodeToString(
                    "${credentials.username}:${credentials.password}".toByteArray(StandardCharsets.UTF_8)
            )
            httpClientBuilder.setDefaultHeaders(setOf(
                    BasicHeader(HttpHeaders.AUTHORIZATION, "Basic $encodedCredentials" )
            ))
        }
        this.httpClient = httpClientBuilder.build()
    }

    override fun makeGetRequest(uri: String): com.ecwid.clickhouse.transport.HttpResponse {
        val request = HttpGet(uri)

        val response = try {
            httpClient.execute(request)
        } catch (e: Exception) {
            throw ClickHouseException("Can't execute ClickHouse request", e)
        }

        val statusCode = response.statusLine.statusCode
        val statusMsg = response.statusLine.reasonPhrase
        val responseContent = response.entity.content

        return HttpResponse(statusCode, statusMsg, StreamContent(responseContent, response.entity))
    }

    override fun makePostRequest(uri: String, content: String): com.ecwid.clickhouse.transport.HttpResponse {
        val request = HttpPost(uri)
        request.entity = StringEntity(content, Charsets.UTF_8)

        val response = try {
            httpClient.execute(request)
        } catch (e: Exception) {
            throw ClickHouseException("Can't execute ClickHouse request", e)
        }

        return try {
            val statusCode = response.statusLine.statusCode
            val statusMsg = response.statusLine.reasonPhrase
            val responseContent = EntityUtils.toString(response.entity, Charsets.UTF_8)

            HttpResponse(statusCode, statusMsg, StringContent(responseContent))
        } finally {
            EntityUtils.consumeQuietly(response.entity)
        }
    }
}
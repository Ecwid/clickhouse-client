package com.ecwid.clickhouse.integration

import com.ecwid.clickhouse.mapped.ClickHouseMappedClient
import com.ecwid.clickhouse.metrics.DefaultMetrics
import com.ecwid.clickhouse.raw.ClickHouseRawClient
import com.ecwid.clickhouse.transport.ClickhouseCredentials
import com.ecwid.clickhouse.transport.httpclient.ApacheHttpClientTransport
import com.ecwid.clickhouse.typed.ClickHouseTypedClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.testcontainers.clickhouse.ClickHouseContainer

/**
 * Base class for all ClickHouse integration tests.
 *
 * 1) Every test method gets its own, totally fresh ClickHouse container (JUnit creates
 *    a new test class instance per test method, and the container is an instance field).
 * 2) The container is fully managed by this class — subclasses never touch it and only
 *    use the ready-made clients (rawClient/typedClient/mappedClient) and host.
 * 3) This class handles ClickHouse start/stop.
 */
@Tag("clickhouse")
abstract class AbstractClickHouseTest {

	// ClickHouseContainer exposes port 8123, waits for readiness over HTTP and creates
	// the test/test user on its own; TZ is pinned only because the Date/DateTime test
	// fixtures assume the server renders timestamps in UTC
	private val container = ClickHouseContainer(RANDOM_CLICKHOUSE_IMAGE)
		.withEnv("TZ", "UTC")

	protected lateinit var host: String
	protected lateinit var rawClient: ClickHouseRawClient
	protected lateinit var typedClient: ClickHouseTypedClient
	protected lateinit var mappedClient: ClickHouseMappedClient

	@BeforeEach
	fun init() {
		// Start ClickHouse container
		// (no Docker => testcontainers fails here with "Could not find a valid Docker environment",
		// which is intentional: a skipped suite would silently green-light an auto-release)
		container.start()
		host = container.httpUrl

		// Init clients
		val transport = ApacheHttpClientTransport(
			credentials = ClickhouseCredentials(container.username, container.password)
		)
		rawClient = ClickHouseRawClient(transport, DefaultMetrics.NONE)
		typedClient = ClickHouseTypedClient(transport, DefaultMetrics.NONE)
		mappedClient = ClickHouseMappedClient(transport)

		// Create tables and insert test data, if any
		generateTestData().forEach { statement ->
			rawClient.executeQuery(host, statement)
		}
	}

	@AfterEach
	fun shutdown() {
		container.stop()
	}

	/**
	 * SQL statements (DDL/DML) to execute against the fresh container before each test.
	 */
	protected open fun generateTestData(): List<String> {
		return emptyList()
	}

	companion object {
		// All ClickHouse images to run tests
		// Choose random image at every run
		private val CLICKHOUSE_IMAGES = setOf(
			"clickhouse/clickhouse-server:24.3.18-alpine",
			"clickhouse/clickhouse-server:24.8.14-alpine",
			"clickhouse/clickhouse-server:25.3.14-alpine",
			"clickhouse/clickhouse-server:25.8.24-alpine",
			"clickhouse/clickhouse-server:26.3.12-alpine",
			"clickhouse/clickhouse-server:26.5.1-alpine"
		)

		val RANDOM_CLICKHOUSE_IMAGE = CLICKHOUSE_IMAGES.random()
		val NEWEST_CLICKHOUSE_IMAGE = CLICKHOUSE_IMAGES.last()

		init {
			println("ClickHouse docker image: $RANDOM_CLICKHOUSE_IMAGE")
		}
	}
}

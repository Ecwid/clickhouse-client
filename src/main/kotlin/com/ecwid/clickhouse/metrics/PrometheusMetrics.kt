package com.ecwid.clickhouse.metrics

import io.prometheus.metrics.core.metrics.Counter
import io.prometheus.metrics.core.metrics.Summary


/**
 * Metrics will be collected to prometheus lib
 */
class PrometheusMetrics : Metrics {
	override fun startRequestTimer(host: String): AutoCloseable {
		return requestsLatencySummary.labelValues(host).startTimer()
	}

	override fun measureRequest(host: String, statusCode: Int) {
		requestsCounter.labelValues(host, statusCode.toString()).inc()
	}

	companion object {
		private val requestsLatencySummary = Summary.builder()
			.name("clickhouse_client_requests_latency_seconds")
			.help("Latency of requests in seconds")
			.labelNames("host")
			.quantile(0.5, 0.01)
			.quantile(0.95, 0.01)
			.quantile(0.99, 0.01)
			.register()

		private val requestsCounter = Counter.builder()
			.name("clickhouse_client_requests_total")
			.help("Total number of requests")
			.labelNames("host", "http_code")
			.register()
	}
}

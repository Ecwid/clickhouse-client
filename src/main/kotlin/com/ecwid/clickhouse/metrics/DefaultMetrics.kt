package com.ecwid.clickhouse.metrics

object DefaultMetrics {
	/**
	 * No metrics will be collected
	 */
	val NONE = object : Metrics {
		private val emptyTimer = AutoCloseable {
			// noting to do
		}

		override fun startRequestTimer(host: String): AutoCloseable {
			return emptyTimer
		}

		override fun measureRequest(host: String, statusCode: Int) {
			// nothing to do
		}
	}

	/**
	 * Use prometheus metrics lib
	 */
	val PROMETHEUS: Metrics by lazy(::PrometheusMetrics)
}

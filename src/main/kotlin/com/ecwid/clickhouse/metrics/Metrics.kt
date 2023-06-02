package com.ecwid.clickhouse.metrics

interface Metrics {
	/**
	 * Returns autocloseable instance, which must do measure on close invoke
	 */
	fun startRequestTimer(host: String): AutoCloseable

	/**
	 * Measure one request
	 */
	fun measureRequest(host: String, statusCode: Int)
}

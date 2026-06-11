package com.ecwid.clickhouse.integration

import com.ecwid.clickhouse.typed.TypedValues
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal data class Player(
	val id: Int,
	val name: String,
	val score: Int?
)

internal class MappedClientIntegrationTest : AbstractClickHouseTest() {

	override fun generateTestData(): List<String> {
		return listOf(
			"create table $TABLE ($COLUMN_ID Int32, $COLUMN_NAME String, $COLUMN_SCORE Nullable(Int32)) engine = MergeTree order by $COLUMN_ID"
		)
	}

	@Test
	fun testInsertAndSelect() {
		val players = listOf(
			Player(1, "alice", 100),
			Player(2, "bob", null)
		)

		mappedClient.insert(host, TABLE, players) { player ->
			TypedValues().apply {
				setInt32(COLUMN_ID, player.id)
				setString(COLUMN_NAME, player.name)
				setInt32Nullable(COLUMN_SCORE, player.score)
			}
		}

		mappedClient.select(host, "select * from $TABLE order by $COLUMN_ID") { row ->
			Player(
				id = row.getInt32(COLUMN_ID),
				name = row.getString(COLUMN_NAME),
				score = row.getInt32Nullable(COLUMN_SCORE)
			)
		}.use { response ->
			assertEquals(players, response.toList())
		}
	}

	companion object {
		private const val TABLE = "mapped_client_test"

		private const val COLUMN_ID = "id"
		private const val COLUMN_NAME = "name"
		private const val COLUMN_SCORE = "score"
	}
}

package com.ecwid.clickhouse

enum class PlatformType(val platformName: String) {

	BOOL("Bool"),

	INT_8("Int8"),
	INT_8_NULLABLE("Nullable(Int8)"),

	INT_16("Int16"),
	INT_16_NULLABLE("Nullable(Int16)"),

	INT_32("Int32"),
	INT_32_NULLABLE("Nullable(Int32)"),

	INT_64("Int64"),
	INT_64_NULLABLE("Nullable(Int64)"),

	UINT_8("UInt8"),
	UINT_8_NULLABLE("Nullable(UInt8)"),

	UINT_16("UInt16"),
	UINT_16_NULLABLE("Nullable(UInt16)"),

	UINT_32("UInt32"),
	UINT_32_NULLABLE("Nullable(UInt32)"),

	UINT_64("UInt64"),
	UINT_64_NULLABLE("Nullable(UInt64)"),

	// --------------------------------------------------------------
	FLOAT_32("Float32"),
	FLOAT_32_NULLABLE("Nullable(Float32)"),

	FLOAT_64("Float64"),
	FLOAT_64_NULLABLE("Nullable(Float64)"),

	// --------------------------------------------------------------
	STRING("String"),
	STRING_NULLABLE("Nullable(String)"),

	// --------------------------------------------------------------
	DATETIME("DateTime"),
	DATETIME_NULLABLE("Nullable(DateTime)"),

	DATE("Date"),
	DATE_NULLABLE("Nullable(Date)"),

	// --------------------------------------------------------------
	DECIMAL("Decimal(P,S)"),
	DECIMAL_NULLABLE("Nullable(Decimal(P,S))"),

	// --------------------------------------------------------------
	ENUM("Enum"),
	ENUM_NULLABLE("Nullable(Enum)")

}

sealed class Type {
	data class Platform(val type: PlatformType, val rawType: String) : Type()
	data class Array(val type: PlatformType, val rawType: String) : Type()
	data class Map(val keyType: PlatformType, val valueType: PlatformType, val rawType: String) : Type()
}

internal fun parseType(type: String): Type {
	return if (type.startsWith("Array(")) {
		parseArrayType(type)
	} else if (type.startsWith("Map(")) {
		parseMapType(type)
	} else if (type.startsWith("LowCardinality(")) {
		parseLowCardinalityType(type)
	} else {
		parsePlatformType(type)
	}
}

private fun findPlatformType(typeName: String): PlatformType {
	val platformType = when {
		typeName.matches("Enum[0-9]+\\(.*\\)".toRegex()) -> {
			PlatformType.ENUM
		}

		typeName.matches("Nullable\\(Enum[0-9]+\\(.*\\)\\)".toRegex()) -> {
			PlatformType.ENUM_NULLABLE
		}

		typeName.matches("FixedString\\([0-9]+\\)".toRegex()) -> {
			PlatformType.STRING
		}

		typeName.matches("Nullable\\(FixedString\\([0-9]+\\)\\)".toRegex()) -> {
			PlatformType.STRING_NULLABLE
		}

		typeName.matches("Decimal\\([0-9]+, [0-9]+\\)".toRegex()) -> {
			PlatformType.DECIMAL
		}

		typeName.matches("Nullable\\(Decimal\\([0-9]+, [0-9]+\\)\\)".toRegex()) -> {
			PlatformType.DECIMAL_NULLABLE
		}

		else -> {
			PlatformType.values().find { it.platformName == typeName }
		}
	}

	requireNotNull(platformType) { "Unknown ClickHouse type '$typeName'" }

	return platformType
}

private fun parsePlatformType(type: String): Type {
	val platformType = findPlatformType(type)
	return Type.Platform(platformType, type)
}

private fun parseLowCardinalityType(type: String): Type {
	val platformTypeName = type.removeSurrounding(prefix = "LowCardinality(", suffix = ")")
	val platformType = findPlatformType(platformTypeName)
	return Type.Platform(platformType, type)
}

private fun parseArrayType(type: String): Type {
	val platformTypeName = type.removeSurrounding("Array(", ")")
	val platformType = findPlatformType(platformTypeName)
	return Type.Array(platformType, type)
}

private fun parseMapType(type: String): Type {
	val platformTypeName = type.removeSurrounding("Map(", ")")
	val keyValueTypes = platformTypeName.split(",").map(String::trim)
	require(keyValueTypes.size == 2) {
		"Can't parse Map type metadata $type"
	}
	val keyPlatformType = findPlatformType(keyValueTypes.first())
	val valuePlatformType = findPlatformType(keyValueTypes.last())
	return Type.Map(keyPlatformType, valuePlatformType, type)
}

package com.ecwid.clickhouse

enum class PlatformType(val platformName: String) {

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

}

sealed class Type {
    data class Platform(val platformType: PlatformType) : Type()
    data class Array(val platformType: PlatformType) : Type()
}

internal fun parseType(type: String): Type {
    return if (type.startsWith("Array(")) {
        parseArrayType(type)
    } else {
        parsePlatformType(type)
    }
}

private fun findPlatformType(typeName: String): PlatformType {
    val platformType = when {
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
            PlatformType.DECIMAL
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
    return Type.Platform(platformType)
}

private fun parseArrayType(type: String): Type {
    val platformTypeName = type.removeSurrounding("Array(", ")")
    val platformType = findPlatformType(platformTypeName)
    return Type.Array(platformType)
}

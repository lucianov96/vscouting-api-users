package com.vscouting.api.users.domain.enum

enum class CountryCode(val dialCode: String) {
    ARGENTINA("+54"),
    URUGUAY("+598"),
    USA("+1"),
    BRAZIL("+55");

    companion object {
        fun fromDialCode(code: String): CountryCode? =
            values().firstOrNull { it.dialCode == code }
    }
}
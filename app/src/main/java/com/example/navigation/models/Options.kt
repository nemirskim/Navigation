package com.example.navigation.models

data class Options(
    val fistCount: Int
) {
    companion object {
        val DEFAULT = Options(fistCount = 3)
    }
}

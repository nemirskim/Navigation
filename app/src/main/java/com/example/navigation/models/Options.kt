package com.example.navigation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Options(
    val fistCount: Int
) : Parcelable {

    companion object {
        val DEFAULT = Options(fistCount = 3)
    }
}

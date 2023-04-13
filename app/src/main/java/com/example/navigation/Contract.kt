package com.example.navigation

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.navigation.models.Options

class Contract(
    private val activity: Class<*>,
    private val inputKey: String,
    private val outputKey: String
) : ActivityResultContract<Options, Options>() {

    override fun createIntent(context: Context, input: Options) =
        Intent(context, activity).apply {
            putExtra(inputKey, input)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): Options {
        val settings = intent?.getIntExtra(outputKey, Options.DEFAULT.fistCount)
            ?: Options.DEFAULT.fistCount
        return Options(settings)
    }
}
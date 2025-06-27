package com.washingtondcsquad.tudee.domain.entity

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.washingtondcsquad.tudee.R
import kotlin.String

enum class Priority{
    LOW,
    MEDIUM,
    HIGH;

    //TODO replace it by mapping from domain to presentation
    companion object {
        fun fromString(priority: String): Priority {
            return when (priority.lowercase()) {
                "low" -> LOW
                "medium" -> MEDIUM
                "high" -> HIGH
                else -> LOW
            }
        }
    }
    @Composable
    fun getTitle(): String{
        return when(this){
            LOW -> stringResource(R.string.low)
            MEDIUM -> stringResource(R.string.medium)
            HIGH -> stringResource(R.string.high)
        }
    }
}
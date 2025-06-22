package com.washingtondcsquad.tudee.presentation.provider

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int): String
}
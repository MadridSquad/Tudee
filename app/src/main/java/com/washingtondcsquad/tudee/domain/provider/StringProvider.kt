package com.washingtondcsquad.tudee.domain.provider

import androidx.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes resId: Int): String
}
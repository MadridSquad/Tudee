package com.washingtondcsquad.tudee.presentation.provider

import android.content.Context
import com.washingtondcsquad.tudee.domain.provider.StringProvider

class StringProviderImp(
    private val context: Context
) : StringProvider{
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}
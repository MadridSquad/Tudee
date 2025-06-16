package com.washingtondcsquad.tudee.presentation.utils.modifierExensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

@Stable
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    val interactionSource = MutableInteractionSource()
    return this.clickable(
        indication = null,
        interactionSource = interactionSource,
        onClick = onClick
    )

}


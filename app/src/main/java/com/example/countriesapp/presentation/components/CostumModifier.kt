package com.example.countriesapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.foundation.interaction.MutableInteractionSource

fun Modifier.noRippleClickable(isEnabled: Boolean = true, onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        enabled = isEnabled,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}
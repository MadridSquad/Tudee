package com.washingtondcsquad.tudee.presentation.design

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class TudeeColors(
    val primary: Color,
    val secondary: Color,
    val primaryVarient: Color,
    val primaryGradient: List<Color>,
    val title: Color,
    val body: Color,
    val hint: Color,
    val stroke: Color,
    val surfaceLow: Color,
    val surface: Color,
    val surfaceHigh: Color,
    val onPrimary: Color,
    val onPrimaryCaption: Color,
    val onPrimaryCard: Color,
    val onPrimaryStroke: Color,
    val disable: Color,
    val pinkAccent: Color,
    val yellowAccent: Color,
    val greenAccent: Color,
    val purpleAccent: Color,
    val error: Color,
    val overlay: Color,
    val emojiTint: Color,
    val yellowVariant: Color,
    val greenVariant: Color,
    val purpleVariant: Color,
    val errorVariant: Color,
    val transparentColor : Color,
) {

    companion object {
        val light = TudeeColors(
            primary = Color(0xFF49BAF2),
            secondary = Color(0xFFF49061),
            primaryVarient = Color(0xFFEFF9FE),
            primaryGradient = listOf(Color(0xFF49BAF2), Color(0xFF3A9CCD)),
            title = Color(0xDE1F1F1F),
            body = Color(0x991F1F1F),
            hint = Color(0x611F1F1F),
            stroke = Color(0x1F1F1F1F),
            surfaceLow = Color(0xFFF0F0F0),
            surface = Color(0xFFF9F9F9),
            surfaceHigh = Color(0xFFFFFFFF),
            onPrimary = Color(0xDEFFFFFF),
            onPrimaryCaption = Color(0xB3FFFFFF),
            onPrimaryCard = Color(0x29FFFFFF),
            onPrimaryStroke = Color(0x99FFFFFF),
            disable = Color(0xFFE8EBED),
            pinkAccent = Color(0xFFF4869A),
            yellowAccent = Color(0xFFF2C849),
            greenAccent = Color(0xFF76C499),
            purpleAccent = Color(0xFF9887F5),
            error = Color(0xFFE55C5C),
            overlay = Color(0x5249BAF2),
            emojiTint = Color(0xDE1F1F1F),
            yellowVariant = Color(0xFFF7F2E4),
            greenVariant = Color(0xFFE4F2EA),
            purpleVariant = Color(0xFFEEEDF7),
            errorVariant = Color(0xFFFCE8E8),
            transparentColor = Color(0x00000000)
        )

        val dark = TudeeColors(
            primary = Color(0xFF3090BF),
            secondary = Color(0xFFF49061),
            primaryVarient = Color(0xFF05202E),
            primaryGradient = listOf(Color(0xFF3090BF), Color(0xFF3A9CCD)),
            title = Color(0xDEFFFFFF),
            body = Color(0x99FFFFFF),
            hint = Color(0x61FFFFFF),
            stroke = Color(0x1FFFFFFF),
            surfaceLow = Color(0xFF020108),
            surface = Color(0xFF0D0C14),
            surfaceHigh = Color(0xFF0F0E19),
            onPrimary = Color(0xDEFFFFFF),
            onPrimaryCaption = Color(0xB3FFFFFF),
            onPrimaryCard = Color(0x29060414),
            onPrimaryStroke = Color(0x99242424),
            disable = Color(0xFF1D1E1F),
            pinkAccent = Color(0xFFCC5268),
            yellowAccent = Color(0xFFB28F25),
            greenAccent = Color(0xFF4D8064),
            purpleAccent = Color(0xFF6F63B2),
            error = Color(0xFFF95555),
            overlay = Color(0x5202151E),
            emojiTint = Color(0xDE1F1F1F),
            yellowVariant = Color(0xFF1F1E1C),
            greenVariant = Color(0xFF1C1F1D),
            purpleVariant = Color(0xFF1C1A33),
            errorVariant = Color(0xFF1F1111),
            transparentColor = Color(0x00000000)
        )
    }
}


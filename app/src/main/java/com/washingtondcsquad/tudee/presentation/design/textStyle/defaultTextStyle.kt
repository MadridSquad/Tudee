package com.washingtondcsquad.tudee.presentation.design.textStyle

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.washingtondcsquad.tudee.R


val nunito = FontFamily(
    Font(R.font.nunito, weight = FontWeight.Normal),
    Font(R.font.nunito_medium, weight = FontWeight.Medium),
    Font(R.font.nunito_semibold, weight = FontWeight.SemiBold),
)

val defaultTextStyle = TudeeTextStyle(

    headLine = SizedTextStyle(
        small = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),

        medium = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 28.sp
        ),

        large = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 30.sp
        ),
    ),

    title = SizedTextStyle(
        small = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ),
        medium =  TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 22.sp
        ),
        large =  TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 24.sp
        ),
    ),


    body = SizedTextStyle(
        small = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 17.sp
        ),
        medium = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 20.sp
        ),
        large = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 22.sp
        )
    ),


    label =  SizedTextStyle(
        small =TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        ),
        medium = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 17.sp
        ),
        large = TextStyle(
            fontFamily = nunito,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 19.sp
        ),
    ),
)

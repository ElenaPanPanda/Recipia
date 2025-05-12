package com.example.recipia.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.recipia.core.ui.R

val playDisplay = FontFamily(
    Font(R.font.playfair_display_bold, FontWeight.Bold), // 700
    Font(R.font.playfair_display_extrabold, FontWeight.ExtraBold), // 800
)

val poppins = FontFamily(
    Font(R.font.poppins, FontWeight.Normal), // 400
    Font(R.font.poppins_medium, FontWeight.Medium), // 500
    Font(R.font.poppins_semibold, FontWeight.SemiBold), // 600
)

@Immutable
data class AppTypography(
    val playDisplayBold: TextStyle = TextStyle(
        fontFamily = playDisplay,
        fontWeight = FontWeight.Bold,
    ),

    val playDisplayExtraBold: TextStyle = TextStyle(
        fontFamily = playDisplay,
        fontWeight = FontWeight.ExtraBold,
    ),

    val poppinsNormal: TextStyle = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Normal,
    ),

    val poppinsMedium: TextStyle = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
    ),

    val poppinsSemiBold: TextStyle = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
    ),
)
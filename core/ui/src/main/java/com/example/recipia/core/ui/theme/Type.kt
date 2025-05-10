package com.example.recipia.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipia.core.ui.R

// TODO: remove with fonts files
val roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal),
)

val playDisplay = FontFamily(
    Font(R.font.playfair_display_bold, FontWeight.Bold), // 700
    Font(R.font.playfair_display_extrabold, FontWeight.Bold), // 800
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

    val poppinsSemiBold: TextStyle = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
    ),

    val poppinsMedium: TextStyle = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Medium,
    ),


    // TODO: remove
    /** Title3 Regular 1 */
    val title3Regular: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),

    /** Body Regular 2 */
    val bodyRegular: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.2.sp,
    ),

    /** Label 3 */
    val label: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp,
    ),

    /** Headline 4 */
    val headline: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    ),

    /** Title3 Medium 5 */
    val title3Medium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),

    /** Body Medium 6 */
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.2.sp,
    ),

    /** Footnote Medium 7 */
    val footnoteMedium: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),

    /** Footnote Regular 8 */
    val footnoteRegular: TextStyle = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
)
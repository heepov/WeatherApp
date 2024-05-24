package com.practicum.weatherapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.practicum.weatherapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_grotesk_variable_font_wght)),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_grotesk_variable_font_wght)),
        fontWeight = FontWeight.Medium,
        fontSize = 48.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_grotesk_variable_font_wght)),
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_mono_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_mono_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_grotesk_variable_font_wght)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.space_grotesk_variable_font_wght)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

)

//val displayLarge: TextStyle = TypographyTokens.DisplayLarge,
//val displayMedium: TextStyle = TypographyTokens.DisplayMedium,
//val displaySmall: TextStyle = TypographyTokens.DisplaySmall,
//val headlineLarge: TextStyle = TypographyTokens.HeadlineLarge,
//val headlineMedium: TextStyle = TypographyTokens.HeadlineMedium,
//val headlineSmall: TextStyle = TypographyTokens.HeadlineSmall,
//val titleLarge: TextStyle = TypographyTokens.TitleLarge,
//val titleMedium: TextStyle = TypographyTokens.TitleMedium,
//val titleSmall: TextStyle = TypographyTokens.TitleSmall,
//val bodyLarge: TextStyle = TypographyTokens.BodyLarge,
//val bodyMedium: TextStyle = TypographyTokens.BodyMedium,
//val bodySmall: TextStyle = TypographyTokens.BodySmall,
//val labelLarge: TextStyle = TypographyTokens.LabelLarge,
//val labelMedium: TextStyle = TypographyTokens.LabelMedium,
//val labelSmall: TextStyle = TypographyTokens.LabelSmall,
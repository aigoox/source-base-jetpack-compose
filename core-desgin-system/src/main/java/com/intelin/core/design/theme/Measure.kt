package com.intelin.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class Measure (
    val paddingHorizontalBody: Dp = 20.dp,
    val paddingBody: Dp = 20.dp,
    val marginVerticalShort: Dp = 10.dp,
    val marginVerticalLowShort: Dp = 5.dp,
    val marginVerticalStandard: Dp = 20.dp,
    val marginShort: Dp = 10.dp,
    val marginStandard: Dp = 20.dp,
    val marginLong: Dp = 50.dp,
    val marginVerticalLong: Dp = 50.dp,
    val marginHorizontalShort: Dp = 10.dp,
    val marginHorizontalLowShort: Dp = 5.dp,
    val marginHorizontalStandard: Dp = 20.dp,
    val marginHorizontalLong: Dp = 50.dp,
    val sizeIconFieldRatio: Float = 0.6f,
    val sizeIconStandard: Dp = 50.dp,
    val sizeIconSmall: Dp = 30.dp,
    val sizeIconLarge: Dp = 100.dp,
    val sizeIconRatioStandard: Float = 1f,
    val sizeIconRatioWithScreenSmall: Float = 10f,
    val sizeIconRatioWithScreenStandard: Float = 30f,
    val sizeIconRatioWithScreenLarge: Float = 50f,
    val sizeIconField: Dp = 20.dp,
    val sizeTextSmall: TextUnit = 15.sp,
    val sizeTextStandard: TextUnit = 20.sp,
    val sizeTextLarge: TextUnit = 30.sp,
    val radiusButton: Dp = 10.dp,
    val radiusViewGroup: Dp = 10.dp,
    val alphaBackgroundPopup: Float = 0.65f,
    val maxWidthPopup: Float = 0.9f,
    val maxHeightPopupConstant: Float = 0.25f,
    val maxHeightPopup: Float = 0.7f,
    val sizeSquareStandard: Dp = 50.dp,
    val paddingFieldStandard: Dp = 20.dp,
    val paddingFieldLowShort: Dp = 5.dp,
    val paddingFieldShort: Dp = 10.dp,
    val heightPopupConstant: Dp = 100.dp
)
val LocalMeasureTheme = staticCompositionLocalOf { Measure() }
package com.intelin.core.design.component.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import com.intelin.core.design.theme.LocalMeasureTheme


@Composable
fun CTIconField(icon: ImageVector ,modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    CTIcon(
        modifier = Modifier
            .fillMaxHeight(LocalMeasureTheme.current.sizeIconFieldRatio)
            .then(modifier),
        tint = tint,
        icon = icon)
}

@Composable
fun CTIconField(icon: ImageBitmap ,modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    CTIcon(
        modifier = Modifier
            .fillMaxHeight(LocalMeasureTheme.current.sizeIconFieldRatio)
            .then(modifier),
        tint = tint,
        icon = icon)
}

@Composable
fun CTIconField(@DrawableRes icon: Int, modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    CTIcon(
        modifier = Modifier
            .fillMaxHeight(LocalMeasureTheme.current.sizeIconFieldRatio)
            .then(modifier),
        tint = tint,
        icon = icon
    )
}

package com.intelin.core.design.component.icon

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.intelin.core.design.R
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.useWhen

@SuppressLint("ModifierParameter")
@Composable
fun CTIcon(icon: ImageVector, modifier: Modifier? = null, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(LocalMeasureTheme.current.sizeIconField)
            .useWhen(modifier.isNotNull(), modifier),
        tint = tint,
        imageVector = icon,
        contentDescription = stringResource(R.string.des_icon_custom)
    )
}
@Composable
fun CTIcon(icon: ImageVector, size: Dp, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(size)
            .width(size),
        tint = tint,
        imageVector = icon,
        contentDescription = stringResource(R.string.des_icon_custom)
    )
}
@Composable
fun CTIcon(icon: ImageBitmap, size: Dp, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(size)
            .width(size),
        bitmap = icon,
        tint = tint,
        contentDescription = stringResource(R.string.des_icon_custom)
    )
}
@Composable
fun CTIcon(@DrawableRes icon: Int, size: Dp, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(size)
            .width(size),
        tint = tint,
        painter = painterResource(id = icon),
        contentDescription = stringResource(R.string.des_icon_custom)
    )
}

@Composable
fun CTIcon(icon: ImageBitmap, modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(LocalMeasureTheme.current.sizeIconField)
            .then(modifier),
        bitmap = icon,
        tint = tint,
        contentDescription = stringResource(R.string.des_icon_custom)
    )
}

@Composable
fun CTIcon(@DrawableRes icon: Int, modifier: Modifier = Modifier, tint: Color = LocalContentColor.current) {
    Icon(
        modifier = Modifier
            .height(LocalMeasureTheme.current.sizeIconField)
            .then(modifier),
        tint = tint,
        painter = painterResource(id = icon),
        contentDescription = "Icon Custom")
}
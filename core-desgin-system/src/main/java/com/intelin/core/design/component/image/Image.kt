package com.intelin.core.design.component.image

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R

@SuppressLint("ModifierParameter")
@Composable
fun CTImage(@DrawableRes icon: Int = R.drawable.ic_logo, modifier: Modifier = Modifier) {
    Image(painter = painterResource(id = icon),
        contentDescription = "img drawable ",
        modifier = modifier)
}

@SuppressLint("ModifierParameter")
@Composable
fun CTImage(icon: ImageBitmap, modifier: Modifier = Modifier) {
    Image(bitmap = icon,
        contentDescription = "img bitmap",
        modifier = modifier)
}

@SuppressLint("ModifierParameter")
@Composable
fun CTImage(icon: ImageVector, modifier: Modifier = Modifier) {
    Image(imageVector = icon,
        contentDescription = "img vector",
        modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun CTImagePreview() {
    CTImage()
}
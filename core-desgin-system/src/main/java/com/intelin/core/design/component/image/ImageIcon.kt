package com.intelin.core.design.component.image

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.intelin.core.design.R
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.useWhen

@SuppressLint("ModifierParameter")
@Composable
fun CTImageIcon(@DrawableRes id: Int = R.drawable.ic_logo, modifier: Modifier? = null) {
    Image(painter = painterResource(id = id),
        contentDescription = "button img component",
        modifier = Modifier
            .fillMaxWidth(LocalMeasureTheme.current.sizeIconRatioStandard)
            .useWhen(modifier.isNotNull(),modifier)
    )
}

@Composable
fun CTImageIcon(@DrawableRes id: Int = R.drawable.ic_logo, size: Dp) {
    Image(painter = painterResource(id = id),
        contentDescription = "button img component",
        modifier = Modifier.width(size).height(size)
    )
}

@Preview(showBackground = true)
@Composable
private fun CTImageIconPreview() {
    CTImageIcon()
}
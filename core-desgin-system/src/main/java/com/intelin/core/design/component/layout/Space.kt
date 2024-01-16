package com.intelin.core.design.component.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.useWhen
import com.intelin.core.library.extension.width

@Composable
fun CTAddSpace(value: Dp = LocalMeasureTheme.current.marginStandard, mode: ModeAlignmentDirection = ModeAlignmentDirection.VERTICAL) {
    if (mode == ModeAlignmentDirection.VERTICAL) {
        Spacer(Modifier.height(value))
    }else {
        Spacer(Modifier.width(value))
    }
}

@Composable
fun CTAddSpaceSmall(value: Dp = LocalMeasureTheme.current.marginShort, mode: ModeAlignmentDirection = ModeAlignmentDirection.VERTICAL) {
    if (mode == ModeAlignmentDirection.VERTICAL) {
        Spacer(Modifier.height(value))
    }else {
        Spacer(Modifier.width(value))
    }
}

@Composable
fun CTAddSpaceLarge(value: Dp = LocalMeasureTheme.current.marginLong, mode: ModeAlignmentDirection = ModeAlignmentDirection.VERTICAL) {
    if (mode == ModeAlignmentDirection.VERTICAL) {
        Spacer(Modifier.height(value))
    }else {
        Spacer(Modifier.width(value))
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun CTAddDriver(color: Color = Color.Gray, size: Dp = 1.dp,width: Dp? = null,modifier: Modifier? = null) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .useWhen(modifier.isNotNull(), modifier)
            .useWhen(width.isNotNull(), Modifier.width(width))
        ,
        color = color,
        thickness = size
    )
}
@SuppressLint("ModifierParameter")
@Composable
fun CTAddDriver(width: Dp, height: Dp = 1.dp, modifier: Modifier? = null) {
    Divider(
        modifier = Modifier
            .width(width)
            .useWhen(modifier.isNotNull(), modifier),
        color = Color.Gray,
        thickness = height
    )
}

@Composable
fun CTAddDriver(ratio: Float) {
    Divider(
        modifier = Modifier.fillMaxWidth(ratio),
        color = Color.Gray,
        thickness = 1.dp
    )
}


@Preview(showBackground = true)
@Composable
private fun CTAddDriverSpacePreview() {
    Column(modifier = Modifier.padding(5.dp)) {
        CTAddDriver()

        CTAddSpace()

        CTAddDriver(200.dp, 20.dp)
    }
}
package com.intelin.core.design.component.text

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.intelin.core.design.component.layout.CTAddDriver
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.useWhen
import com.intelin.core.library.ultils.AppUtils

@SuppressLint("ModifierParameter")
@Composable
fun CTText(
    modifier: Modifier? = null,
    style: TextStyle? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Center,
    onClick: (() -> Unit?)? = null
) {

    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        style = style ?:  TextStyle.Default,
        fontSize = fontSize,
        modifier = Modifier
            .useWhen(onClick.isNotNull(), Modifier.clickable {
                onClick?.invoke()
            })
            .useWhen(modifier))
}

@Composable
fun CTLineText(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        var viewWidth by remember {
            mutableStateOf(0)
        }

        val widthDriver = (AppUtils.widthScreen()
                - (LocalMeasureTheme.current.paddingBody * 2)
                - LocalMeasureTheme.current.marginHorizontalLong
                - (viewWidth.dp / AppUtils.densityScreen())) / 2

        CTAddDriver(widthDriver)

        CTAddSpace(mode = ModeAlignmentDirection.HORIZONTAL, value = LocalMeasureTheme.current.marginHorizontalShort)

        CTText(text = text,
            style = TextStyle(
                fontSize = LocalMeasureTheme.current.sizeTextStandard,
                color = Color.Gray,
                fontFamily = FontFamily.Serif
            ),
            modifier = Modifier
            .onGloballyPositioned { coordinates ->
                viewWidth = coordinates.size.width
            })

        CTAddSpace(mode = ModeAlignmentDirection.HORIZONTAL, value = LocalMeasureTheme.current.marginHorizontalShort)

        CTAddDriver(widthDriver)
    }
}
@SuppressLint("ModifierParameter")
@Composable
fun CTTextSmall(
    modifier: Modifier? = null,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    onClick: (() -> Unit?)? = null) {
    CTText(
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = LocalMeasureTheme.current.sizeTextSmall,
        onClick = onClick,
        modifier = Modifier.useWhen(modifier.isNotNull(), modifier))
}

@Composable
fun CTTextStandard(modifier: Modifier = Modifier,
                   text: String,
                   textAlign: TextAlign = TextAlign.Start,
                   color: Color = Color.Unspecified,
                   onClick: (() -> Unit?)? = null) {
    CTText(text = text, textAlign = textAlign, color = color ,fontSize = LocalMeasureTheme.current.sizeTextStandard, onClick = onClick, modifier = modifier)
}

@Composable
fun CTTextLarge(text: String,
                modifier: Modifier = Modifier,
                color: Color = Color.Unspecified,
                textAlign: TextAlign = TextAlign.Start,
                onClick: (() -> Unit?)? = null) {
    CTText(text = text, color = color,textAlign = textAlign, fontSize = LocalMeasureTheme.current.sizeTextLarge, onClick = onClick, modifier = modifier)
}

@Composable
fun CTTextStyleSmall(modifier: Modifier = Modifier,
                     text: String,
                     textAlign: TextAlign = TextAlign.Start,
                     color: Color = Color.Unspecified,
                     onClick: (() -> Unit?)? = null) {
    CTText(text = text, color = color, textAlign = textAlign, style = MaterialTheme.typography.titleSmall, onClick = onClick, modifier = modifier)
}

@Composable
fun CTTextStyleStandard(modifier: Modifier = Modifier,
                        text: String,
                        textAlign: TextAlign = TextAlign.Start,
                        color: Color = Color.Unspecified,
                        onClick: (() -> Unit?)? = null) {
    CTText(text = text, color = color, textAlign = textAlign, style = MaterialTheme.typography.titleMedium, onClick = onClick, modifier = modifier)
}

@Composable
fun CTTextStyleLarge(modifier: Modifier = Modifier,
                     text: String,
                     textAlign: TextAlign = TextAlign.Start,
                     color: Color = Color.Unspecified,
                     onClick: (() -> Unit?)? = null) {
    CTText(text = text, color = color, textAlign = textAlign, style = MaterialTheme.typography.titleLarge, onClick = onClick, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
private fun CTLineTextPreview() {
    CTLineText("Login")
}
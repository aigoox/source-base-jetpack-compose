package com.intelin.core.design.component.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intelin.core.design.R
import com.intelin.core.design.component.button.CTButton
import com.intelin.core.design.component.layout.CTAddDriver
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.repository.pattern.ITextPopup
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.compareTrue
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.switch

@Composable
fun CTPopup(
    isShow: Boolean? = null,
    text: ITextPopup,
    onHeader: @Composable (() -> Unit)? = null,
    onFooter: @Composable (() -> Unit)? = null,
    onOK: () -> Unit = {},
    onCancel: (() -> Unit)? = null,
    onBody: @Composable (() -> Unit)? = null
) {
    if (isShow.switch(text.getMessage().isNotEmpty().compareTrue())) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(LocalMeasureTheme.current.maxWidthPopup)
                    .fillMaxHeight(LocalMeasureTheme.current.maxHeightPopup)
                    .wrapContentHeight()
                    .background(
                        color = Color.Transparent
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(LocalMeasureTheme.current.radiusViewGroup)
                        )
                ) {

                    Header(onHeader, text.getTitle())

                    CTAddDriver()

                    CTAddSpace()

                    Body(onBody, text.getMessage())

                    CTAddSpace()

                    Footer(onFooter, onOK, onCancel)

                }
            }
        }
    }
}

@Composable
private fun Body(onBody: @Composable (() -> Unit)?, message: String) {
    if (onBody != null) {
        onBody.invoke()
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalMeasureTheme.current.marginHorizontalStandard, 0.dp)
        ) {
            CTText(text = message, fontSize = LocalMeasureTheme.current.sizeTextStandard)
        }
    }
}

@Composable
private fun Header(onHeader: @Composable (() -> Unit)? = null, text: String?) {
    if (onHeader != null) {
        onHeader.invoke()
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(15.dp, 5.dp)
        ) {
            Text(
                text = text.switch(stringResource(id = R.string.notification)),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun Footer(
    onFooter: @Composable (() -> Unit)? = null,
    onOK: () -> Unit = {},
    onCancel: (() -> Unit)? = null
) {
    if (onFooter.isNotNull()) {
        onFooter?.invoke()
    } else {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp, 1.dp)
                .fillMaxWidth()
        ) {
            if (onCancel != null) {
                CTButton(
                    label = LocalContext.current.getString(R.string.cancel),
                    onClick = onOK,
                    modifier = Modifier.padding(
                        LocalMeasureTheme.current.marginHorizontalStandard,
                        LocalMeasureTheme.current.marginVerticalShort)
                        .weight(1f)
                )
            }

            CTButton(
                label = LocalContext.current.getString(R.string.ok),
                onClick = onOK,
                modifier = Modifier.padding(
                    LocalMeasureTheme.current.marginHorizontalStandard,
                    LocalMeasureTheme.current.marginVerticalShort)
                    .weight(1f)
            )
        }
    }
}

@Composable
fun CTPopup(
    text: ITextPopup,
    onHeader: @Composable (() -> Unit)? = null,
    onFooter: @Composable (() -> Unit)? = null,
    onOK: () -> Unit = {},
    onCancel: (() -> Unit)? = null,
    onBody: @Composable (() -> Unit)? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(LocalMeasureTheme.current.maxWidthPopup)
                .fillMaxHeight(LocalMeasureTheme.current.maxHeightPopup)
                .wrapContentHeight()
                .background(
                    color = Color.Transparent
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(LocalMeasureTheme.current.radiusViewGroup)
                    )
            ) {

                Header(onHeader, text.getTitle())

                CTAddDriver()

                CTAddSpace()

                Body(onBody, text.getMessage())

                CTAddSpace()

                Footer(onFooter, onOK, onCancel)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CTPopupPreview() {
    CTPopup(isShow = true, text = ITextPopup.message("Test Popup"), onOK = {}, onCancel = {})
}
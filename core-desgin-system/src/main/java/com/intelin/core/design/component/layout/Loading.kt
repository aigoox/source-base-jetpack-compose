package com.intelin.core.design.component.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.getValue
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CTLoading(
    loading: MutableStateFlow<Boolean>,
    content: @Composable (() -> Unit?)? = null
) {

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        content?.invoke()
        if (loading.getValue()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = LocalMeasureTheme.current.alphaBackgroundPopup))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight(LocalMeasureTheme.current.maxHeightPopupConstant)
                        .fillMaxWidth(LocalMeasureTheme.current.maxWidthPopup)
                        .background(
                            color = MaterialTheme.colorScheme.onTertiary,
                            shape = RoundedCornerShape(LocalMeasureTheme.current.radiusViewGroup)
                        )
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        CTAddSpace()
                        CTText(text = stringResource(R.string.loading))
                    }
                }
            }
        }
    }
}

@Composable
fun CTLoading() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = LocalMeasureTheme.current.alphaBackgroundPopup))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxHeight(LocalMeasureTheme.current.maxHeightPopupConstant)
                    .fillMaxWidth(LocalMeasureTheme.current.maxWidthPopup)
                    .background(
                        color = MaterialTheme.colorScheme.onTertiary,
                        shape = RoundedCornerShape(LocalMeasureTheme.current.radiusViewGroup)
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    CTAddSpace()
                    CTText(text = stringResource(R.string.loading))
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CTLoadingPreview() {
    CTLoading(MutableStateFlow(true)) {}
}
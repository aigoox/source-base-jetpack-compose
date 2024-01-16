package com.intelin.core.design.component.popup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.repository.pattern.ITextPopup
import com.intelin.core.library.extension.getValue
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun CTPopupAlert(
    message: MutableStateFlow<String>,
    onOK: () -> Unit = {},
    content: @Composable (() -> Unit?)? = null
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content?.invoke()
        CTPopup(
            isShow = message.getValue().isNotEmpty() ,
            text = ITextPopup.message(message.getValue()),
            onOK = onOK
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CTPopupAlertPreview() {
    CTPopupAlert(message = MutableStateFlow("Test Popup")) {}
}
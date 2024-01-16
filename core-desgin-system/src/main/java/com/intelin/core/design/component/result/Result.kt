package com.intelin.core.design.component.result

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.component.text.CTTextLarge
import com.intelin.core.design.component.button.CTButton
import com.intelin.core.design.component.image.CTImage
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.repository.data.StatusResult
import com.intelin.core.design.theme.LocalMeasureTheme

@Composable
fun CTResult(icon: @Composable () -> Unit, message: String, textButton: String? = null, onClick: () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon.invoke()
        
        CTAddSpace()
        
        CTText(text = message)
        
        CTAddSpace()
        
        textButton?.let { 
            CTButton(label = textButton) { onClick.invoke() }
        }
    }
}

@Composable
fun CTResult(@DrawableRes icon: Int, message: String, textButton: String? = null, onClick: () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CTImage(icon, Modifier.fillMaxWidth(LocalMeasureTheme.current.sizeIconRatioWithScreenStandard))

        CTAddSpace()

        CTTextLarge(text = message, textAlign = TextAlign.Center)

        CTAddSpace()

        textButton?.let {
            CTButton(label = textButton) { onClick.invoke() }
        }
    }
}

@Composable
fun CTResult(message: String, textButton: String? = null, mode: StatusResult = StatusResult.INFO, onClick: () -> Unit = {}) {
    when(mode) {
        StatusResult.SUCCESS -> {
            CTResult(R.mipmap.ic_success_result, message, textButton, onClick)
        }
        StatusResult.FAILED -> {
            CTResult(R.mipmap.ic_failed_result, message, textButton, onClick)
        }
        else -> {
            CTResult(R.mipmap.ic_info_result, message, textButton, onClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CTResultPreview() {
    Column{
        CTResult(mode = StatusResult.SUCCESS, message = "Data test 1", textButton = "Login")
        CTResult(mode = StatusResult.FAILED, message = "Data test 2", textButton = "Login")
        CTResult(message = "Data test", textButton = "Login")
    }
}
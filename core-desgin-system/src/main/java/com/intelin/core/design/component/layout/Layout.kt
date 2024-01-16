package com.intelin.core.design.component.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.intelin.core.design.R
import com.intelin.core.design.component.icon.CTIcon
import com.intelin.core.design.component.popup.CTPopupAlert
import com.intelin.core.design.component.text.CTText
import com.intelin.core.design.component.text.CTTextStandard
import com.intelin.core.design.repository.data.ModeAlignmentDirection
import com.intelin.core.design.theme.LocalMeasureTheme
import com.intelin.core.library.extension.clickable
import com.intelin.core.library.extension.clickableWithoutRipple
import com.intelin.core.library.extension.isNotNull
import com.intelin.core.library.extension.useWhen
import com.intelin.core.library.extension.useWhenElse
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("ModifierParameter")
@Composable
fun CTColumnLayout(alignmentHorizontal:  Alignment.Horizontal = Alignment.CenterHorizontally,
                   arrangementVertical:   Arrangement.Vertical = Arrangement.Top,
                   isFullPage: Boolean = false,
                   isPadding: Boolean = false,
                   isScrollView: Boolean = false,
                   modifier: Modifier? = null,
                   content: @Composable () -> Unit) {
    Column(modifier = Modifier
        .useWhen(modifier.isNotNull(), modifier)
        .clickable()
        .useWhenElse(isFullPage, Modifier.fillMaxSize(), Modifier.fillMaxWidth())
        .useWhen(isScrollView, Modifier.verticalScroll(rememberScrollState()))
        .useWhen(isPadding, Modifier.padding(LocalMeasureTheme.current.paddingBody)),
        verticalArrangement = arrangementVertical,
        horizontalAlignment = alignmentHorizontal
    ) {
        content.invoke()
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun CTRowLayout(arrangementHorizontal:  Arrangement.Horizontal = Arrangement.Start,
                alignmentVertical: Alignment.Vertical = Alignment.CenterVertically,
                isFullPage: Boolean = false,
                isPadding: Boolean = true,
                isScrollView: Boolean = false,
                modifier: Modifier? = null,
                content: @Composable () -> Unit) {
    Row(modifier = Modifier
        .clickable()
        .useWhenElse(isFullPage, Modifier.fillMaxSize(), Modifier.fillMaxWidth())
        .useWhen(isPadding, Modifier.padding(LocalMeasureTheme.current.paddingBody))
        .useWhen(isScrollView, Modifier.horizontalScroll(rememberScrollState()))
        .useWhen(modifier.isNotNull(), modifier),
        verticalAlignment = alignmentVertical,
        horizontalArrangement = arrangementHorizontal
    ) {
        content.invoke()
    }
}
@Composable
fun CTBackLayout(
    isScrollView: Boolean = true,
    isPadding: Boolean = true,
    onBack: (() -> Unit?)? = null,
    arrangementVertical: Arrangement.Vertical = Arrangement.Top,
    alignmentHorizontal: Alignment.Horizontal = Alignment.Start,
    footer: @Composable (() -> Unit?)? = null,
    content: @Composable () -> Unit) {

    CTColumnLayout(

        isFullPage = true,
        isScrollView = isScrollView,
        isPadding = isPadding,
        arrangementVertical = Arrangement.SpaceBetween) {

        Column(modifier = Modifier.fillMaxWidth()) {

            onBack?.let {

                Box(contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .clickableWithoutRipple { it.invoke() }
                ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        CTIcon(icon = Icons.Default.ArrowBack, tint = MaterialTheme.colorScheme.onTertiaryContainer)

                        CTAddSpace(LocalMeasureTheme.current.marginHorizontalLowShort, ModeAlignmentDirection.HORIZONTAL)

                        CTTextStandard(text = stringResource(id = R.string.label_back_to), color = MaterialTheme.colorScheme.onTertiaryContainer)
                    }
                }
            }

            CTAddSpace()

            if(arrangementVertical != Arrangement.Center) {

                BodyBackLayout(arrangementVertical, alignmentHorizontal, content)
            }
        }

        if(arrangementVertical == Arrangement.Center) {

            BodyBackLayout(arrangementVertical, alignmentHorizontal, content)
        }

        footer?.invoke()
    }
}

@Composable
fun BodyBackLayout(
    arrangementVertical: Arrangement.Vertical = Arrangement.Top,
    alignmentHorizontal: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = arrangementVertical,
        horizontalAlignment = alignmentHorizontal
    ) {
        content.invoke()
    }

    CTAddSpace()
}
@Composable
fun CTContentLayoutLoadingAlert(
    messageAlert: MutableStateFlow<String>,
    loading: MutableStateFlow<Boolean>,
    onCloseAlert: (() -> Unit?)? = null,
    content: @Composable () -> Unit) {
    CTPopupAlert(
        message = messageAlert,
        onOK = {
            if (onCloseAlert.isNotNull()) {
                onCloseAlert?.invoke()
            } else {
                messageAlert.value = ""
            }},
    ) {
        CTLoading(loading) {

            Column(modifier = Modifier.fillMaxSize()) {
                content.invoke()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CTBackLayoutPreview() {
    CTBackLayout(footer = {
        CTText(text = "Footer")
    }, onBack = {}){}
}
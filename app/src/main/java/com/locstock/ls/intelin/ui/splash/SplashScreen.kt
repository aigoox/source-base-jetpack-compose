package com.locstock.ls.intelin.ui.splash

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.intelin.core.design.component.button.CTButton
import com.intelin.core.design.component.layout.CTAddSpace
import com.intelin.core.design.component.layout.CTColumnLayout
import com.intelin.core.design.component.popup.CTPopupAlert
import com.intelin.core.library.extension.getValueCompose
import com.intelin.core.library.extension.resetValue
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SplashScreen() {
    val viewModel: SplashViewModel = hiltViewModel()
    val context = LocalContext.current
    BackHandler {
        return@BackHandler
    }
    val mPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
            prepare()
        }
    }


    val playerView = remember {
        PlayerView(context).apply {
            useController = false
        }
    }
    RenderSplashScreen(
        messageError = viewModel.messageNotify.getInstance,
        onOKPopup = { viewModel.messageNotify.resetValue() },
        urlClip = viewModel.urlClip.getValueCompose(),
        mPlayer = mPlayer,
        playerView = playerView,
        onChangeUrl = { viewModel.changeUrl() }
    )
}

@Composable
fun RenderSplashScreen(
    messageError: MutableStateFlow<String>,
    onOKPopup: () -> Unit,
    urlClip: String,
    onChangeUrl: () -> Unit,
    mPlayer: ExoPlayer,
    playerView: PlayerView
) {
    val context = LocalContext.current
    Toast.makeText(context, urlClip, Toast.LENGTH_SHORT).show()
    LaunchedEffect(urlClip) {
        mPlayer.apply {
            clearMediaItems()
            seekTo(0)
            addMediaItem(0, MediaItem.fromUri(urlClip))
        }
        playerView.player = mPlayer
    }

    CTPopupAlert(
        message = messageError,
        onOK = onOKPopup
    ) {
        CTColumnLayout {
            AndroidView(
                factory = {
                    playerView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
            )

            CTAddSpace()

            CTButton(label = "Change Clip", onClick = {
                onChangeUrl.invoke()
            })
        }
    }
}
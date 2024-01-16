package com.locstock.ls.intelin.ui.splash

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import com.intelin.core.library.base.BaseViewModel
import com.intelin.core.library.extension.createStateFlow
import com.intelin.core.library.extension.createStateFlowInstance
import com.intelin.core.library.extension.getValue
import com.intelin.core.library.repository.interfaces.ISessionManager
import com.intelin.core.network.remote.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): BaseViewModel() {

    private val _urlClip =  MutableStateFlow("https://live-par-2-cdn-alt.livepush.io/live/bigbuckbunnyclip/index.m3u8")
    private val _urlChange = MutableStateFlow("https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8")
    val urlClip = _urlClip.createStateFlow()

    fun changeUrl() {
        _urlClip.value = _urlChange.value
    }
}
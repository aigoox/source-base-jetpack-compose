package com.intelin.core.design.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class SnackBarState {
    DEFAULT,
    ERROR
}

class SnackBarDelegate(
    var snackBarHostState: SnackbarHostState? = null,
    var coroutineScope: CoroutineScope? = null
) {

    private var snackBarState: SnackBarState = SnackBarState.DEFAULT

    val snackBarBackgroundColor: androidx.compose.ui.graphics.Color
        @Composable
        get() = when (snackBarState) {
            SnackBarState.DEFAULT -> MaterialTheme.colorScheme.onBackground
            SnackBarState.ERROR -> MaterialTheme.colorScheme.error
        }

    fun showSnackBar(
        state: SnackBarState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        this.snackBarState = state
        coroutineScope?.launch {
            snackBarHostState?.showSnackbar(message, actionLabel, duration = duration)
        }
    }
}
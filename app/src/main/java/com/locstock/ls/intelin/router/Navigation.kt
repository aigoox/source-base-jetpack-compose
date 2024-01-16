package com.locstock.ls.intelin.router

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.intelin.core.design.utils.SnackBarDelegate
import com.intelin.core.design.utils.SnackBarState
import com.intelin.core.network.utils.extension.navigationPopUpTo
import com.locstock.ls.intelin.ui.splash.SplashScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val snackBarDelegate by remember {
        mutableStateOf(SnackBarDelegate())
    }

    val snackBarHostState = remember {
        SnackbarHostState()
    }
    snackBarDelegate.apply {
        this.snackBarHostState = snackBarHostState
        coroutineScope = rememberCoroutineScope()
    }
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {
                val backgroundColor = snackBarDelegate.snackBarBackgroundColor
                Snackbar(
                    snackbarData = it,
                    containerColor = backgroundColor,
                )
            }
        },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                MainNavigation(snackBarDelegate, navController = navController) {
                    selectedItem = it
                }
            }
        },
    )
}

@Suppress("UNUSED_VARIABLE")
@Composable
fun MainNavigation(
    snackBarDelegate: SnackBarDelegate,
    navController: NavHostController,
    onSelectItemChange: (Int) -> Unit
) {
    val navToHome: (Boolean) -> Unit = { clearBackStack ->
        onSelectItemChange.invoke(0)
        navController.navigate(Screen.Home.route) {
            if (clearBackStack) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        }
    }
    val showSnackBar: (
        state: SnackBarState,
        message: String,
        actionLabel: String?,
        duration: SnackbarDuration
    ) -> Unit = { state, message, actionLabel, duration ->
        snackBarDelegate.showSnackBar(state, message, actionLabel, duration)
    }
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen()
        }

    }
}
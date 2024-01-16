package com.intelin.core.network.utils.extension

import androidx.navigation.NavHostController

/**
 * isInclusive: Is there screen release?
 * - true: Freeing the screens in the stack
 * - false: Free the screens but ignore the current screen
 */
fun NavHostController.navigationPopUpTo(route: String, routeLiberated: String, isInclusive: Boolean = true) {
    this.navigate(route) {
        popUpTo(routeLiberated) {
            inclusive = isInclusive
        }
    }
}
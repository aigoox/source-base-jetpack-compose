package com.intelin.core.design.component.button

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.intelin.core.design.theme.AppTheme

@Composable
fun BottomNavigationBar(
    navController: NavController,
    routeMap: Map<String, String>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    AppTheme() {
        val items = routeMap.values
        val routes = routeMap.keys.toList()

        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { GetIconByBottomIndex(index) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = {
                        onItemSelected.invoke(index);
                        navController.navigate(routes[index]) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun GetIconByBottomIndex(index: Int) {
    when (index) {
        1 -> {
            Icon(Icons.Filled.Menu, contentDescription = "Home")
        }

        else -> {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }
    }
}

@Composable
fun getDefaultSelected(navController: NavController, routeMap: Map<String, String>): Int {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("currentRout", "currentRout e$currentRoute")
    val tmpIndex = routeMap.keys.indexOfFirst { it == currentRoute }
    return if (tmpIndex < 0) {
        0
    } else {
        tmpIndex
    }
}
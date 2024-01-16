package com.locstock.ls.intelin.router


sealed class Screen(val route: String, var title: String) {
    object Home : Screen("home", "Home")
    object Profile : Screen("profile", "Profile")
    object Register : Screen("register", "Register")
    object Splash : Screen("splash", "Splash")
    object Login : Screen("login", "Login")
    object ForgotPassword : Screen("forgot_password", "Forgot Password")
}
package com.andy.fakestore.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
    object ProductDetails : Screen("product_details_screen/{productId}") {
        fun createRoute(productId: Int) = "product_details_screen/$productId"
    }
    object Cart : Screen("cart_screen")
    object Profile : Screen("profile_screen")
}

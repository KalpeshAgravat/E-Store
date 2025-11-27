package com.andy.fakestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andy.fakestore.feature_auth.presentation.LoginScreen
import com.andy.fakestore.feature_auth.presentation.RegisterScreen
import com.andy.fakestore.feature_cart.presentation.CartScreen
import com.andy.fakestore.feature_home.presentation.HomeScreen
import com.andy.fakestore.feature_home.presentation.ProductDetailsScreen
import com.andy.fakestore.feature_profile.presentation.ProfileScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.ProductDetails.route,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) {
            ProductDetailsScreen()
        }
        composable(Screen.Cart.route) {
            CartScreen()
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}

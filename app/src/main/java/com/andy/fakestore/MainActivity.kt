package com.andy.fakestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andy.fakestore.ui.components.BottomNavigationBar
import com.andy.fakestore.ui.navigation.NavGraph
import com.andy.fakestore.ui.navigation.Screen
import com.andy.fakestore.ui.theme.FakeStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf(
                    Screen.Home.route,
                    Screen.Cart.route,
                    Screen.Profile.route
                )

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { padding ->
                    // Apply padding only if bottom bar is shown, or handle inside screens
                    // Actually, NavGraph should handle padding or we pass it down
                    // But standard way is to apply padding to NavHost
                    androidx.compose.foundation.layout.Box(modifier = Modifier.padding(padding)) {
                        NavGraph(navController = navController)
                    }
                }
            }
        }
    }
}
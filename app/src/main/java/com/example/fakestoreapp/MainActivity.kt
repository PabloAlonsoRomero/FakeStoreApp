package com.example.fakestoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fakestoreapp.screens.HomeScreen
import com.example.fakestoreapp.screens.ProductDetailScreen
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = "home"){
                        composable(route = "home") {
                            HomeScreen(
                                innerPadding = innerPadding, navController = navController
                            )
                        }
                        composable(
                            route = "product-detail/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getInt("id") ?: 0
                            ProductDetailScreen(
                                innerPadding = innerPadding,
                                productId = id
                            )
                        }
                    }

                }
            }
        }
    }
}
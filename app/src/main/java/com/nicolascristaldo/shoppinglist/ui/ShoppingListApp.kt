package com.nicolascristaldo.shoppinglist.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicolascristaldo.shoppinglist.ui.navigation.AppNavHost

@Composable
fun ShoppingListApp(navController: NavHostController = rememberNavController()) {
    AppNavHost(navController = navController)
}
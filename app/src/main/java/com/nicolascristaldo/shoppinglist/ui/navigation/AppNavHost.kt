package com.nicolascristaldo.shoppinglist.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascristaldo.shoppinglist.ui.screens.home.HomeDestination
import com.nicolascristaldo.shoppinglist.ui.screens.home.HomeScreen
import com.nicolascristaldo.shoppinglist.ui.screens.list.ListDestination
import com.nicolascristaldo.shoppinglist.ui.screens.list.ListScreen
import com.nicolascristaldo.shoppinglist.ui.screens.product.add.AddProductDestination
import com.nicolascristaldo.shoppinglist.ui.screens.product.add.AddProductScreen
import com.nicolascristaldo.shoppinglist.ui.screens.product.details.ProductDetailsDestination
import com.nicolascristaldo.shoppinglist.ui.screens.product.details.ProductDetailsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddProduct = { navController.navigate(AddProductDestination.route) },
                navigateToList = { navController.navigate(ListDestination.route) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = AddProductDestination.route) {
            AddProductScreen(navController = navController)
        }

        composable(route = ListDestination.route) {
            ListScreen(
                navController = navController,
                navigateToEdit = {
                    navController.navigate("${ProductDetailsDestination.route}/${it}")
                }
            )
        }

        composable(
            route = ProductDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductDetailsDestination.productId) {
                type = NavType.IntType
            })
        ) {
            ProductDetailsScreen(navController = navController)
        }
    }
}
package com.nicolascristaldo.shoppinglist.ui.screens.product.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.ui.AppViewModelProvider
import com.nicolascristaldo.shoppinglist.ui.components.ShoppingListTopAppBar
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination
import kotlinx.coroutines.launch

object ProductDetailsDestination: NavDestination {
    override val route: String = "product_details"
    override val titleRes: Int = R.string.product_details
    const val productId = "productId"
    val routeWithArgs = "$route/{$productId}"
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(
    navController: NavHostController,
    viewModel: ProductDetailsViewModel = viewModel(factory = AppViewModelProvider.factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ShoppingListTopAppBar(
                title = stringResource(id = R.string.product_details),
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = viewModel.productUiState.productDetails.name,
                onValueChange = {
                    viewModel.updateUiState(
                        viewModel.productUiState.productDetails.copy(name = it)
                    )
                }
            )
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                value = viewModel.productUiState.productDetails.price,
                onValueChange = {
                    viewModel.updateUiState(
                        viewModel.productUiState.productDetails.copy(price = it)
                    )
                }
            )
            OutlinedTextField(
                value = viewModel.productUiState.productDetails.category,
                onValueChange = {
                    viewModel.updateUiState(
                        viewModel.productUiState.productDetails.copy(category = it)
                    )
                }
            )
            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.updateProduct()
                        navController.navigateUp()
                    }
                },
                enabled = viewModel.productUiState.isEntryValid
            ) {
                Text(text = "save")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.deleteProduct()
                        navController.navigateUp()
                    }
                },
                enabled = viewModel.productUiState.isEntryValid
            ) {
                Text(text = "delete")
            }
        }
    }
}
package com.nicolascristaldo.shoppinglist.ui.screens.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.ui.AppViewModelProvider
import com.nicolascristaldo.shoppinglist.ui.components.ShoppingListTopAppBar
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination

object ListDestination: NavDestination {
    override val route: String = "list"
    override val titleRes: Int = R.string.products_list
}

@Composable
fun ListScreen(
    navController: NavHostController,
    viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.factory),
    modifier: Modifier = Modifier
) {
    val listUiState by viewModel.listUiState.collectAsState()

    Scaffold(
        topBar = { 
            ShoppingListTopAppBar(
                title = stringResource(id = R.string.products_list),
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(items = listUiState.productList, key = { it.id }) { product ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = product.name)
                    Text(text = product.category)
                }
            }
        }
    }
}
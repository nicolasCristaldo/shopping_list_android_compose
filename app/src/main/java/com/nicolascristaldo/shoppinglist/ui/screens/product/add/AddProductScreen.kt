package com.nicolascristaldo.shoppinglist.ui.screens.product.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.ui.AppViewModelProvider
import com.nicolascristaldo.shoppinglist.ui.components.AppButton
import com.nicolascristaldo.shoppinglist.ui.components.ShoppingListTopAppBar
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination
import com.nicolascristaldo.shoppinglist.ui.screens.product.components.DetailsInputForm
import kotlinx.coroutines.launch

object AddProductDestination : NavDestination {
    override val route: String = "add_product"
    override val titleRes: Int = R.string.add_product
}

@Composable
fun AddProductScreen(
    navController: NavHostController,
    viewModel: AddProductViewModel = viewModel(factory = AppViewModelProvider.factory),
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ShoppingListTopAppBar(
                title = stringResource(id = R.string.add_product),
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        AddProductBody(
            viewModel = viewModel,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveProduct()
                    navController.navigateUp()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddProductBody(
    viewModel: AddProductViewModel,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        DetailsInputForm(
            productDetails = viewModel.productUiState.productDetails,
            onValueChange = viewModel::updateUiState
        )
        AppButton(
            title = "save",
            enabled = viewModel.productUiState.isEntryValid,
            onClick = { onSaveClick() }
        )
    }
}
package com.nicolascristaldo.shoppinglist.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.data.providers.CategoriesProvider
import com.nicolascristaldo.shoppinglist.model.Product
import com.nicolascristaldo.shoppinglist.ui.AppViewModelProvider
import com.nicolascristaldo.shoppinglist.ui.components.ShoppingListTopAppBar
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination
import com.nicolascristaldo.shoppinglist.ui.screens.list.components.ProductCard

object ListDestination : NavDestination {
    override val route: String = "list"
    override val titleRes: Int = R.string.products_list
}

@Composable
fun ListScreen(
    navController: NavHostController,
    viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.factory),
    navigateToEdit: (Int) -> Unit,
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
        ListScreenBody(
            navigateToEdit = navigateToEdit,
            productList = listUiState.productList,
            contentPadding = innerPadding
        )
    }
}

@Composable
fun ListScreenBody(
    navigateToEdit: (Int) -> Unit,
    productList: List<Product>,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        items(items = productList, key = { it.id }) { product ->

            ProductCard(
                product = product,
                navigateToEdit = navigateToEdit
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenBodyPreview() {
    ListScreenBody(
        navigateToEdit = {},
        productList = listOf(
            Product(
                id = 1,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 2,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 3,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 4,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 5,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 6,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 7,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
            Product(
                id = 8,
                name = "BANANA",
                price = 2.43,
                category = CategoriesProvider().getCategory("Groceries").name
            ),
        ),
        contentPadding = PaddingValues()
    )
}
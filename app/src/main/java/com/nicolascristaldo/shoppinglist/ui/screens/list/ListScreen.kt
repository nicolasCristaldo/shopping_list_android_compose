package com.nicolascristaldo.shoppinglist.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.data.providers.CategoriesProvider
import com.nicolascristaldo.shoppinglist.ui.AppViewModelProvider
import com.nicolascristaldo.shoppinglist.ui.components.ShoppingListTopAppBar
import com.nicolascristaldo.shoppinglist.ui.model.CategoriesModel
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination
import com.nicolascristaldo.shoppinglist.ui.screens.list.components.EmptyListScreen
import com.nicolascristaldo.shoppinglist.ui.screens.list.components.ProductCard

object ListDestination : NavDestination {
    override val route: String = "list"
    override val titleRes: Int = R.string.products_list
}

@SuppressLint("StateFlowValueCalledInComposition")
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
            //productList = listUiState.productList,
            selectedCategories = viewModel.selectedCategories,
            onCategoryUnselected = viewModel.selectedCategories::remove,
            onCategorySelected = viewModel.selectedCategories::add,
            listUiState = viewModel.listUiState.value,
            contentPadding = innerPadding
        )
    }
}

@Composable
fun ListScreenBody(
    navigateToEdit: (Int) -> Unit,
    selectedCategories: SnapshotStateList<CategoriesModel>,
    onCategorySelected: (CategoriesModel) -> Unit,
    onCategoryUnselected: (CategoriesModel) -> Unit,
    listUiState: ListUiState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val categories = CategoriesProvider().getCategories()

    if (listUiState.productList.isEmpty()) {
        EmptyListScreen(modifier = Modifier.fillMaxSize())
    } else {
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            LazyRow {
                items(items = categories, key = { it.name }) { category ->
                    FilterChip(
                        selected = selectedCategories.contains(category),
                        onClick = {
                            if (selectedCategories.contains(category)) onCategoryUnselected(category)
                            else onCategorySelected(category)
                        },
                        label = {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        leadingIcon = {
                            if (selectedCategories.contains(category)) Icon(
                                imageVector = Icons.Filled.Check,
                                tint = colorResource(id = category.color),
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.small_padding))
                    )
                }
            }
            LazyColumn {
                items(items = listUiState.productList, key = { it.id }) { product ->
                    if (
                        selectedCategories.isEmpty() ||
                        selectedCategories.contains(CategoriesProvider().getCategory(product.category))
                    ) {
                        ProductCard(
                            product = product,
                            navigateToEdit = navigateToEdit
                        )
                    }
                }
            }
        }

    }
}

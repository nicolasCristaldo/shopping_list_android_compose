package com.nicolascristaldo.shoppinglist.ui.screens.product.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
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

object ProductDetailsDestination : NavDestination {
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
    ) { innerPadding ->
        ProductDetailsBody(
            viewModel = viewModel,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateProduct()
                    navController.navigateUp()
                }
            },
            onDeleteClick = {
                coroutineScope.launch {
                    viewModel.deleteProduct()
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
fun ProductDetailsBody(
    viewModel: ProductDetailsViewModel,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isDeleteDialogVisible by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        DetailsInputForm(
            productDetails = viewModel.productUiState.productDetails,
            onValueChange = viewModel::updateUiState
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(
                title = stringResource(id = R.string.save),
                enabled = viewModel.productUiState.isEntryValid,
                onClick = { onSaveClick() }
            )

            AppButton(
                title = stringResource(id = R.string.delete),
                onClick = {
                    isDeleteDialogVisible = true
                },
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onErrorContainer,
                    contentColor = MaterialTheme.colorScheme.onError,
                    disabledContentColor = MaterialTheme.colorScheme.outline,
                    disabledContainerColor = MaterialTheme.colorScheme.outlineVariant
                )
            )
        }
    }
    if(isDeleteDialogVisible) {
        DeleteConfirmationDialog(
            onConfirm = onDeleteClick,
            onDismiss = { isDeleteDialogVisible = false },
            modifier = Modifier.padding(dimensionResource(id = R.dimen.large_padding))
        )
    }
}

@Composable
fun DeleteConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = stringResource(id = R.string.attention),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_question),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(
                    text = stringResource(id = R.string.yes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = stringResource(id = R.string.no),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        modifier = modifier
    )
}
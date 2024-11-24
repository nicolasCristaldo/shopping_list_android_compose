package com.nicolascristaldo.shoppinglist.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.ui.components.AppButton
import com.nicolascristaldo.shoppinglist.ui.navigation.NavDestination
import com.nicolascristaldo.shoppinglist.ui.screens.list.ListDestination
import com.nicolascristaldo.shoppinglist.ui.screens.product.add.AddProductDestination

object HomeDestination: NavDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(
    navigateToAddProduct: () -> Unit,
    navigateToList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.app_name))

        AppButton(
            title = stringResource(id = R.string.add_product),
            onClick = { navigateToAddProduct() }
        )

        AppButton(
            title = stringResource(id = R.string.products_list),
            onClick = { navigateToList() }
        )
    }
}
package com.nicolascristaldo.shoppinglist.ui.screens.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.data.providers.CategoriesProvider
import com.nicolascristaldo.shoppinglist.model.Product
import com.nicolascristaldo.shoppinglist.ui.screens.product.formatedPrice

@Composable
fun ProductCard(
    product: Product,
    navigateToEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(75.dp)
            .padding(
                vertical = dimensionResource(id = R.dimen.small_padding),
                horizontal = dimensionResource(id = R.dimen.medium_padding)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))
        ) {
            Image(
                painter = painterResource(
                    id = CategoriesProvider().getCategory(product.category).image
                ),
                contentDescription = product.category,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.large_padding))
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = product.formatedPrice(),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Button(
                onClick = { navigateToEdit(product.id) },
                shape = MaterialTheme.shapes.small
            )
            {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(id = R.string.edit_button)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    ProductCard(
        product = Product(
            name = "banana",
            price = 3.4356565,
            category = CategoriesProvider().getCategory("Furniture").name
        ),
        navigateToEdit = {}
    )
}
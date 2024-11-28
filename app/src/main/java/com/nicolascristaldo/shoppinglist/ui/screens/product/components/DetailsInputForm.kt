package com.nicolascristaldo.shoppinglist.ui.screens.product.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.nicolascristaldo.shoppinglist.R
import com.nicolascristaldo.shoppinglist.data.providers.CategoriesProvider
import com.nicolascristaldo.shoppinglist.ui.screens.product.ProductDetails
import com.nicolascristaldo.shoppinglist.ui.screens.product.isValidLength
import com.nicolascristaldo.shoppinglist.ui.screens.product.isValidPrice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsInputForm(
    productDetails: ProductDetails,
    onValueChange: (ProductDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    var dropdownIsExpanded by remember { mutableStateOf(false) }
    val categoryList = CategoriesProvider().getCategories()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = productDetails.name,
            onValueChange = {
                if (isValidLength(it)) onValueChange(productDetails.copy(name = it))
            },
            label = {
                Text(
                    text = "name",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium_padding))
        )

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            value = productDetails.price,
            onValueChange = {
                if (isValidLength(it) && isValidPrice(it)) {
                    onValueChange(productDetails.copy(price = it))
                }
            },
            label = {
                Text(
                    text = "price",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.medium_padding))
        )

        ExposedDropdownMenuBox(
            expanded = dropdownIsExpanded,
            onExpandedChange = { dropdownIsExpanded = !dropdownIsExpanded },
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.large_padding))
        ) {
            OutlinedTextField(
                value = productDetails.category,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(
                        text = "category",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownIsExpanded)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = dropdownIsExpanded,
                onDismissRequest = { dropdownIsExpanded = false }
            ) {
                categoryList.forEachIndexed { index, category ->

                    if (index != 0) HorizontalDivider()
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = category.name,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onValueChange(productDetails.copy(category = categoryList[index].name))
                            dropdownIsExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsInputFormPreview() {
    DetailsInputForm(
        productDetails = ProductDetails(),
        onValueChange = {}
    )
}
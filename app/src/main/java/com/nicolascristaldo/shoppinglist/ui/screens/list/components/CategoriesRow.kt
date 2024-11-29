package com.nicolascristaldo.shoppinglist.ui.screens.list.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.nicolascristaldo.shoppinglist.data.providers.CategoriesProvider
import com.nicolascristaldo.shoppinglist.ui.model.CategoriesModel

@Composable
fun CategoriesRow(
    selectedCategories: SnapshotStateList<CategoriesModel>,
    onCategorySelected: (CategoriesModel) -> Unit,
    onCategoryUnselected: (CategoriesModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = CategoriesProvider().getCategories()

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
                modifier = modifier
            )
        }
    }
}
package com.nicolascristaldo.shoppinglist.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicolascristaldo.shoppinglist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopAppBar(
    title: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { 
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_button)
                )
            }
        }
    )
}
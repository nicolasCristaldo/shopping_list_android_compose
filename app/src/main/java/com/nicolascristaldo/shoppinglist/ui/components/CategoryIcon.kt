package com.nicolascristaldo.shoppinglist.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nicolascristaldo.shoppinglist.R

@Composable
fun CategoryIcon(
    painter: Painter,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_circle_size))
        ) {
            drawCircle(
                color = color
            )
        }
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.icon_size))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryIconPreview() {
    CategoryIcon(
        painter = painterResource(id = R.drawable.ic_others),
        color = colorResource(id = R.color.color_others)
    )
}
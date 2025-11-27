package com.andy.fakestore.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarRate

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    tint: Color = MaterialTheme.colorScheme.primary
) {
    Row(modifier = modifier) {
        for (i in 1..maxRating) {
            if (i <= rating) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = tint
                )
            } else {
                Icon(
                    imageVector = Icons.Default.StarRate,
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    }
}

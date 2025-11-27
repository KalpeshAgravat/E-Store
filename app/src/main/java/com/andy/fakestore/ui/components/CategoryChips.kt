package com.andy.fakestore.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChips(
    categories: List<String>,
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        item {
            FilterChip(
                selected = selectedCategory == null || selectedCategory == "All",
                onClick = { onCategorySelected("All") },
                label = { Text("All") },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = { Text(category.capitalize()) },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

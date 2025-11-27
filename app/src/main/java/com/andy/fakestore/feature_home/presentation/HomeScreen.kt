package com.andy.fakestore.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.andy.fakestore.ui.components.CategoryChips
import com.andy.fakestore.ui.components.ProductCard
import com.andy.fakestore.ui.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                CategoryChips(
                    categories = state.categories,
                    selectedCategory = state.selectedCategory,
                    onCategorySelected = { viewModel.onEvent(HomeEvent.SelectCategory(it)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.products) { product ->
                        ProductCard(
                            product = product,
                            onClick = {
                                navController.navigate(Screen.ProductDetails.createRoute(product.id))
                            }
                        )
                    }
                }
            }
        }
    }
}

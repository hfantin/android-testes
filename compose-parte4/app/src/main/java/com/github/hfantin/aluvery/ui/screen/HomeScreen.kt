package com.github.hfantin.aluvery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hfantin.aluvery.model.Product
import com.github.hfantin.aluvery.sampledata.sampleCandies
import com.github.hfantin.aluvery.sampledata.sampleDrinks
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.sampledata.sampleSections
import com.github.hfantin.aluvery.ui.components.CardProductItem
import com.github.hfantin.aluvery.ui.components.ProductsSection
import com.github.hfantin.aluvery.ui.components.SearchTextField
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections() = searchText.isBlank()
}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var text by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { p: Product ->
        p.name.contains(text, ignoreCase = true) || p.description?.contains(text, ignoreCase = true) ?: false
    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) // { it.name.contains(text, ignoreCase = true) || it.description?.contains(text, ignoreCase = true) ?: false }
            products.filter(containsInNameOrDescription())
        } else emptyList()
    }


    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)

}

@Composable
fun HomeScreen(state: HomeScreenUiState = HomeScreenUiState()) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts = state.searchedProducts
        SearchTextField(
            searchText = text,
            onSearchChange = state.onSearchChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        LazyColumn(
            Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                sections.forEach {
                    item {
                        ProductsSection(
                            title = it.key, products = it.value
                        )
                    }
                }
            } else {
                items(searchedProducts) {
                    CardProductItem(product = it, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(sections = sampleSections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUiState(
                    searchText = "a", sections = sampleSections
                ),
            )
        }
    }
}
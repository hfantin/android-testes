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
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.sampledata.sampleSections
import com.github.hfantin.aluvery.ui.components.CardProductItem
import com.github.hfantin.aluvery.ui.components.ProductsSection
import com.github.hfantin.aluvery.ui.components.SearchTextField
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(sections: Map<String, List<Product>>, searchText: String = "") {
    Column {
        var text by remember { mutableStateOf(searchText) }
        SearchTextField(
            searchText = text,
            onSearchChange = {
                text = it
            },
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        val searchedProducts = remember(text) {
            if (text.isNotBlank()) {
                sampleProducts.filter { it.name.contains(text, ignoreCase = true) || it.description?.contains(text, ignoreCase = true) ?: false }
            } else {
                emptyList()
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (text.isBlank()) {
                sections.forEach {
                    item {
                        ProductsSection(
                            title = it.key,
                            products = it.value
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
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections, "pizza")
        }
    }
}
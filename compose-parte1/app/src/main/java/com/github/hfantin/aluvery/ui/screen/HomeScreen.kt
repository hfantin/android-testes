package com.github.hfantin.aluvery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.hfantin.aluvery.R
import com.github.hfantin.aluvery.model.Product
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.ui.components.ProductsSection
import java.math.BigDecimal

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProductsSection("Promoções", sampleProducts)
        ProductsSection(
            "Doces", listOf(
                Product(
                    name = "Chocolate",
                    price = BigDecimal("5.99"),
                    image = R.drawable.placeholder
                )
            )
        )
        ProductsSection("Bebidas", sampleProducts)
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
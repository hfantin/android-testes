package com.github.hfantin.aluvery.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.hfantin.aluvery.model.Product
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

@Composable
fun ProductsSection(title: String, products: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = title,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
                Modifier
                    .padding(
                        top = 8.dp
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { ProductItem(it)}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsSectionPreview() {
    AluveryTheme {
        Surface {
            ProductsSection("Promoções", products = sampleProducts)
        }
    }
}
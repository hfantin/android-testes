package com.github.hfantin.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.github.hfantin.aluvery.model.Product
import com.github.hfantin.aluvery.sampledata.sampleProducts
import com.github.hfantin.aluvery.ui.screen.HomeScreen
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(products = sampleProducts)
        }
    }
}

@Composable
fun App(products: List<Product>) {
    AluveryTheme {
        Surface {
            HomeScreen()
        }
    }
}

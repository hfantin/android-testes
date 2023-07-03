package com.github.hfantin.aluvery.ui.components.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.github.hfantin.aluvery.dao.ProductDao
import com.github.hfantin.aluvery.ui.screen.ProductFormScreen
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

class ProdutoFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(onSaveClick = { product ->
                        dao.save(product)
                        finish()
                    })
                }
            }
        }

    }
}




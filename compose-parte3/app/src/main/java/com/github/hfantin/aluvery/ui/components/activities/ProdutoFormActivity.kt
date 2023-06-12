package com.github.hfantin.aluvery.ui.components.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.hfantin.aluvery.ui.theme.AluveryTheme

class ProdutoFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }

    }
}

@Composable
fun ProductFormScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp,
        )
        var url by remember { mutableStateOf("") }
        TextField(
            url, onValueChange = { url = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da imagem")
            },
        )

        var name by remember { mutableStateOf("") }
        TextField(
            name, onValueChange = { name = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
        )

        var price by remember { mutableStateOf("") }
        TextField(
            price, onValueChange = { price = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            },
        )

        var description by remember { mutableStateOf("") }
        TextField(
            description, onValueChange = { description = it },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
        )

        Button(
            onClick = { /*TODO*/ },
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10)
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }

}


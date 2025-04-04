package com.example.fakestoreapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductCard(
    product: Product,
    onClick: (Product) -> Unit = {}
) {
    Card (
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
            .padding(5.dp)
            .clickable {
                onClick(product)
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .weight(3f),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = product.computedTitle,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            // Precio
            Text(
                text = "$${product.price}",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview
@Composable
fun ProductCardPreview() {
    val testProduct = Product(
        id = 1,
        title = "iPhone 16",
        description = "Un buen telefono",
        price = 17000.00,
        category = "Telefonía",
        image = "",
        rating = Rating(count = 5, rate = 5.0)
    )
    FakeStoreAppTheme {
        ProductCard(testProduct, onClick = { println("Arriba el León") })
    }
}

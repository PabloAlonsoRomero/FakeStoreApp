package com.example.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.services.ProductService
import com.example.fakestoreapp.ui.theme.Amarillo
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Composable
fun ProductDetailScreen(
    innerPadding : PaddingValues,
    productId: Int
) {
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://fakestoreapi.com/"
    var product by remember {
        mutableStateOf(
            Product(
                id = 0,
                description = "",
                title = "",
                image = "",
                price = 0.0,
                category = "",
                rating = Rating(rate = 0.0, count = 0)
            )
        )
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    // Efecto secundario
    LaunchedEffect(key1 = true) {

        scope.launch {
            try {
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val productService = retrofitBuilder.create(ProductService::class.java)

                val response = productService.getProductById(productId)
                product = response
                isLoading = false
                Log.i("ProductDetail", response.toString())
            } catch (e:Exception) {

            }
        }


    }

    if (isLoading) {
        // Loading
        Box (
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = product.computedTitle,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(16.dp),
                    contentScale = ContentScale.Fit
                )

                // Nombre del producto
                Text(
                    text = product.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                        .align(Alignment.Start)
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ){
                    repeat(product.rating.rate.toInt()) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Amarillo,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${product.rating.rate}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                // Descripcion del producto
                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end=16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Tarjeta con precio y botón de compra
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        // Precio del producto
                        Text(
                            text = "PRICE:\n$${product.price}",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        // Boton de compra
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Amarillo)
                        ) {
                            Text(
                                text = "Comprar",
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }


}
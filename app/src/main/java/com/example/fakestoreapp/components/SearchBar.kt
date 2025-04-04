package com.example.fakestoreapp.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var textSearch by remember {
        mutableStateOf("")
    }


    Column (
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        TextField(
            value = textSearch,
            onValueChange = { valor ->
                textSearch = valor
                Log.i("TextField", valor)
            },
            placeholder = { Text("Busca aqui") },
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Icono de busqueda") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    FakeStoreAppTheme {
        SearchBar()
    }
}
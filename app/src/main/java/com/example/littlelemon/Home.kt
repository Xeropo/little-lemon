package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Home(){
    Column() {
        Text(text = "Hello World")
    }
}

@Preview (showBackground = true)
@Composable
fun HomePreview(){

}
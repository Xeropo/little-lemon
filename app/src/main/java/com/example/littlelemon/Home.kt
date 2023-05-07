package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
            ) {
            Header()
            Button(onClick = { navController.navigate(Profile.route) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White, contentColor = Color.White),
                modifier = Modifier
                    .align(Alignment.TopEnd),
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(0.dp)
                ) {
                Image(
                    painter = painterResource(id = R.drawable.profil),
                    contentDescription = "Header logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(75.dp).padding(10.dp)
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun HomePreview(){

}
package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Markazi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.*

@Composable
fun Profile(navController: NavController, sharedPreferences: SharedPreferences) {
    Column(modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        Column(Modifier.fillMaxSize().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ){
                Text(
                    text = "Personal Information",
                    fontSize = 30.sp,
                    fontFamily = Markazi,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
            TextData(title = "First Name",
                data = sharedPreferences.getString("first_name","").toString())
            TextData(title = "Last Name",
                data = sharedPreferences.getString("last_name","").toString())
            TextData(title = "Email",
                data = sharedPreferences.getString("email","").toString())
            Button(onClick = { sharedPreferences.edit().clear().apply()
                             navController.navigate(Onboarding.route)},
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondPrimaryColor),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Log out",fontSize = 20.sp,
                    fontFamily = Karla, color = BlackHighLightColor)
            }
        }
    }
}

@Composable
fun TextData(title: String, data: String){
    val textFieldColors = TextFieldDefaults.textFieldColors(
        unfocusedLabelColor = FirstPrimaryColor,
        backgroundColor = Color.White,
        unfocusedIndicatorColor = FirstPrimaryColor
    )
   OutlinedTextField(
       modifier = Modifier
           .padding(10.dp)
           .fillMaxWidth(0.9f),
       value = data,
       onValueChange = {},
       label = { Text(text = title)},
       enabled = false,
       colors = textFieldColors
       )
}
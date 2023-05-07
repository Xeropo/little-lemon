package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController){
    val context = LocalContext.current
    val sharedPreferences by lazy {  context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)}
    NavHost(navController = navController,
        startDestination = isDataStored(sharedPreferences)
    ) {
        composable(Onboarding.route) {
            Onboarding(navController,sharedPreferences)
        }
        composable(Home.route){
            Home()
        }
        composable(Profile.route){
            Profile()
        }
    }
}

fun isDataStored(sharedPreferences: SharedPreferences): String{
    val firstName = sharedPreferences.getString("first_name", "")
    val lastName = sharedPreferences.getString("last_name", "")
    val email = sharedPreferences.getString("email", "")
    return if (firstName.isNullOrEmpty() || lastName.isNullOrEmpty() || email.isNullOrEmpty())
    { Onboarding.route }
    else Home.route
}
package com.example.littlelemon


import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.*


@Composable
fun Onboarding(navController: NavController, sharedPreferences: SharedPreferences){
    val context = LocalContext.current
    Column(Modifier.fillMaxSize(),) {
        Header()
        Box(modifier = Modifier
            .background(FirstPrimaryColor)
            .fillMaxWidth()
            .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = WhiteHighlightColor,
                fontSize = 35.sp,
                fontFamily = Markazi,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = "Personal Information",
                fontSize = 30.sp,
                fontFamily = Markazi,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
        RegisterForm(context, navController, sharedPreferences)
    }
}

@Composable
fun Header(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Header logo",
        modifier = Modifier
            .width(200.dp)
            .height(100.dp),
    )
}

@Composable
fun RegisterForm(context: Context, navController: NavController, sharedPreferences: SharedPreferences){
    val toastMessage = "Registration unsuccessful.\nPlease enter all data."
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var buttonPressed by remember { mutableStateOf(false) }
    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.White)
    val textFieldColorsError = TextFieldDefaults.textFieldColors(
        focusedLabelColor = Color.Red,
        disabledLabelColor = Color.Red,
        unfocusedLabelColor = Color.Red,
        backgroundColor = Color.White,
        placeholderColor = Color.Red,

    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 10.dp),
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(text = "First name") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = if (firstName.isNotEmpty() || !buttonPressed) {textFieldColors}
            else textFieldColorsError
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 10.dp),
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(text = "Last name") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = if (lastName.isNotEmpty() || !buttonPressed) {textFieldColors}
            else textFieldColorsError

       )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 10.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = if (isValidEmail(email) || !buttonPressed) {textFieldColors}
            else textFieldColorsError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Button(
            onClick = {
                buttonPressed = true
                if (!isValidEmail(email) || lastName.isEmpty() || firstName.isEmpty()){
                  Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }
                else {
                    val editor = sharedPreferences.edit()
                    editor.putString("first_name", firstName)
                    editor.putString("last_name", lastName)
                    editor.putString("email", email)
                    editor.apply()
                    navController.navigate(Home.route)
                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                }
 },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = SecondPrimaryColor),
            shape = RoundedCornerShape(10.dp)
        ) { Text(text = "Register",fontSize = 20.sp,
            fontFamily = Karla, color = BlackHighLightColor) }
    }
}

fun isValidEmail(email: String): Boolean {
    val pattern = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
    return pattern.matches(email)
}

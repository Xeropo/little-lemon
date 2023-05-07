package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.*

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(navController)
        HeroSection(items)
    }
}

@Composable
fun TopBar(navController: NavController){
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Header logo",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.075f)
                .padding(top = 5.dp, bottom = 5.dp)
        )
        Button(onClick = { navController.navigate(Profile.route) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White, contentColor = Color.White),
            modifier = Modifier
                .align(Alignment.CenterEnd),
            shape = CircleShape,
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profil),
                contentDescription = "Profile image",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun HeroSection(items: List<MenuItemRoom>){
    val textFieldColors = TextFieldDefaults.textFieldColors( backgroundColor = WhiteHighlightColor)
    var searchPhrase by remember { mutableStateOf("") }
    var menuItems = items
    Column(
        modifier = Modifier
            .background(FirstPrimaryColor)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = SecondPrimaryColor,
            fontFamily = Markazi
        )
        Row {
            Column {
                Text(
                    text = stringResource(id = R.string.location),
                    fontSize = 35.sp,
                    color = WhiteHighlightColor,
                    fontFamily = Markazi
                )

                Text(
                    text = stringResource(id = R.string.description),
                    color = WhiteHighlightColor,
                    fontFamily = Karla,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(0.6f)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Hero Image",
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
            )
        }
        TextField(value = searchPhrase, onValueChange = {
            searchPhrase = it
        },
            label = { Text(text = "Enter search phrase")},
            colors = textFieldColors,
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally))
        menuItems = if (searchPhrase.isNotEmpty()){
            menuItems.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }
        } else items
    }
    MenuCategory(menuItems)
}

@Composable
fun MenuCategory(items: List<MenuItemRoom>){
    var menuItems by remember {
        mutableStateOf(items)
    }
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = SecondPrimaryColor)
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {


            Text(
                text = "ORDER FOR DELIVERY!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Markazi,
                modifier = Modifier
                    .padding(8.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = {  menuItems = items.filter { it.category.lowercase() == "starters" } },
                    shape = RoundedCornerShape(16.dp),
                    colors = buttonColors) {
                    Text(text = "Starters",
                        fontFamily = Markazi, color = BlackHighLightColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Button(onClick = { menuItems = items.filter { it.category.lowercase() == "mains" } },
                    shape = RoundedCornerShape(16.dp),
                    colors = buttonColors) {
                    Text(text = "Mains",
                        fontFamily = Markazi, color = BlackHighLightColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Button(onClick = { menuItems = items.filter { it.category.lowercase() == "desserts" } },
                    shape = RoundedCornerShape(16.dp),
                    colors = buttonColors) {
                    Text(text = "Desserts",
                        fontFamily = Markazi, color = BlackHighLightColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
                Button(onClick = { menuItems = items.filter { it.category.lowercase() == "drinks" } },
                    shape = RoundedCornerShape(16.dp),
                    colors = buttonColors) {
                    Text(text = "Drinks",
                        fontFamily = Markazi, color = BlackHighLightColor, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
        }
    }
    if(menuItems.isEmpty()) {menuItems = items}
    MenuItemsList(items = menuItems)
}

@Composable
fun MenuItemsList(items: List<MenuItemRoom>){
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()){
        items(items = items) {menuItem ->
            MenuItemCard(menuItem = menuItem)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItemCard(menuItem: MenuItemRoom){
    Card {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Column {
                Text(text = menuItem.title,
                    fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = menuItem.description,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f))
                Text(text = "%.2f".format(menuItem.price), fontWeight = FontWeight.Bold)
            }
            GlideImage(
                model = menuItem.image,
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
        }
    }
}
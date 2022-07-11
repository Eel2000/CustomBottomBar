package com.example.custombottombar

 import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.custombottombar.ui.theme.CustomBottomBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomBottomBarTheme {
                MainApplication()
            }
        }
    }
}

@Composable
fun MainApplication(){
    Scaffold(
        bottomBar = {
            CustomBottomNavBar()
        }
    ) {

    }
}

@Composable
private fun BottomNavBar(modifier: Modifier){
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp,
                    bottomEnd = 15.dp,
                    bottomStart = 15.dp
                )
            )
            .padding(vertical = 50.dp, horizontal = 45.dp)
            .shadow(elevation = 30.dp),
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            },
            label = {
                Text(text = "Home")
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = "Profile")
            }
        )
    }
}

data class MenutItem(val ItemName: String, val Image : ImageVector)
var MenuItems = listOf(
    MenutItem(ItemName = "Home", Image = Icons.Outlined.Home),
    MenutItem(ItemName = "Favorites", Image = Icons.Outlined.Favorite),
    MenutItem(ItemName = "Profile", Image = Icons.Outlined.Person),
)

@Composable
fun CustomBottomNavBar(menuItems: List<MenutItem> = MenuItems){

    var selectedMenuItem by remember{
        mutableStateOf(0)
    }
    val middleItem = menuItems[(menuItems.size/2)];

    Box(
        modifier = Modifier
            .padding(horizontal = 90.dp, vertical = 50.dp)
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp),
                clip = true,
            )
            .clip(RoundedCornerShape(55.dp))
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ){
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.selectableGroup(),
        ){
            items(menuItems.size){
                if(menuItems[it] == middleItem){
                    IconButton(onClick = {
                        selectedMenuItem = it
                    }, modifier= Modifier.padding(
                        start = 10.dp,
                        end = 10.dp
                    )) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if(it == selectedMenuItem){
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text(text = menuItems[it].ItemName, color = Color.White, fontSize = 12.sp)
                            }else{
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Text(text = menuItems[it].ItemName, color = Color.Black, fontSize = 12.sp)
                            }
                        }
                    }
                }else{
                    IconButton(onClick = {
                        selectedMenuItem = it
                    }) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if(it == selectedMenuItem){
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Text(text = menuItems[it].ItemName, color = Color.White, fontSize = 12.sp)
                            }else{
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                                Text(text = menuItems[it].ItemName, color = Color.Black, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainAplicationPreview(){
    CustomBottomBarTheme {
        MainApplication()
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview(){
    CustomBottomBarTheme {
        BottomNavBar(modifier = Modifier.padding())
    }
}

@Preview(showBackground = true)
@Composable
fun CustomBottomNavBarPreview(){
    CustomBottomBarTheme {
        CustomBottomNavBar()
    }
}

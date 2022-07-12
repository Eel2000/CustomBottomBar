package com.example.custombottombar

 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.annotation.DrawableRes
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.background
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.LazyRow
 import androidx.compose.foundation.lazy.items
 import androidx.compose.foundation.selection.selectableGroup
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material.*
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.AccountCircle
 import androidx.compose.material.icons.filled.Home
 import androidx.compose.material.icons.outlined.Favorite
 import androidx.compose.material.icons.outlined.Home
 import androidx.compose.material.icons.outlined.Person
 import androidx.compose.material.icons.outlined.Search
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.draw.shadow
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.graphics.vector.ImageVector
 import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.text.font.FontWeight
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
        ScreenHome()
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

data class SectionItem(
    val Title: String,
    val ContentTitle: String,
    val ContentSubTitle: String,
    @DrawableRes val ContentImage: Int
)
data class MenutItem(
    val ItemName: String,
    val Image: ImageVector
)
var MenuItems = listOf(
    MenutItem(ItemName = "Home", Image = Icons.Outlined.Home),
    MenutItem(ItemName = "Favorites", Image = Icons.Outlined.Favorite),
    MenutItem(ItemName = "Profile", Image = Icons.Outlined.Person),
)

var SectionItems = listOf(
    SectionItem("Jump Back In","Legs", "30 mins", R.drawable.jumping_high_for_fitness),
    SectionItem("Body Focus","Squatting", "30 mins", R.drawable.squatting_exercise),
    SectionItem("Stretching","Body", "5 mins", R.drawable.stretching_at_workout),
)

@Composable
fun CustomBottomNavBar(menuItems: List<MenutItem> = MenuItems){

    var selectedMenuItem by remember{
        mutableStateOf(0)
    }
    val middleItem = menuItems[(menuItems.size/2)]

    Box(
        modifier = Modifier
            .padding(horizontal = 90.dp, vertical = 50.dp)
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp),
                clip = true,
            )
            .clip(RoundedCornerShape(55.dp))
            .background(Color.White)
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
                                    tint = Color(244,204,204)
                                )
                                Text(text = menuItems[it].ItemName, color = Color(244,204,204), fontSize = 12.sp)
                            }else{
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                                Text(text = menuItems[it].ItemName, color = Color.Gray, fontSize = 12.sp)
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
                                    tint = Color(244,204,204)
                                )
                                Text(text = menuItems[it].ItemName, color = Color(244,204,204), fontSize = 12.sp)
                            }else{
                                Icon(
                                    imageVector = menuItems[it].Image,
                                    contentDescription = null,
                                    tint = Color.Gray
                                )
                                Text(text = menuItems[it].ItemName, color = Color.Gray, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun BodyTop(){
    Column(modifier = Modifier
        .height(490.dp)
        .fillMaxWidth()
        .background(Color.DarkGray)) {
        Box {
            Image(
                painterResource(id = R.drawable.female_yoga_fashion_and_mat_one),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 25.dp, top = 10.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.woman_on_exercise_bike),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_search_icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .width(190.dp)
                            .padding(start = 20.dp),
                    ) {
                        Text(
                            text = "Get into shape before the lockdown ends",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight(600),
                            color = Color.White
                        )
                    }
                }


            }
        }
    }
}

@Composable
fun BodyEnd(){
    Column(modifier = Modifier
        .height(400.dp)
        .fillMaxWidth()
        .background(Color(246, 246, 246))) {

        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 10.dp)) {
            LazyColumn(modifier = Modifier.padding(vertical = 10.dp)){
                items(SectionItems){ item ->
                    BodySection(
                        sectionTile = item.Title,
                        contentTile = item.ContentTitle,
                        contentSubTile = item.ContentSubTitle,
                        contentImage = item.ContentImage
                    )
                }

            }
        }
    }
}

@Composable
fun BodySection(
    sectionTile: String,
    contentTile: String,
    contentSubTile: String,
    @DrawableRes contentImage: Int
){
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
                .fillMaxWidth(),
        ){
            Text(
                text = sectionTile,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight(600)
            )
            Text(
                text = "View all",
                style = MaterialTheme.typography.body1,
                color = Color(244, 204, 204)
            )
        }

        Box(modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(top = 10.dp, start = 20.dp)) {
            //TODO: put item card in here
            ItemCard(title = contentTile, subTitle = contentSubTile, backImage = contentImage )
        }
    }
}

@Composable
fun ItemCard(
    title: String,
    subTitle: String,
    @DrawableRes backImage: Int
){
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //TODO: make some action
            },
        elevation = 5.dp,
    ) {
        Box (modifier = Modifier
            .height(145.dp)
            .width(100.dp)){
            Image(
                painter = painterResource(id = backImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,

            )
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 10.dp,
                        top = 10.dp
                    )
            ){
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        color = Color.White,
                        fontWeight = FontWeight(600)
                    )
                    Text(
                        text = subTitle,
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Composable
fun MiddleCard(){
    Column {
        Box(modifier = Modifier.padding(horizontal = 20.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Weekly Focus",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight(600),
                    color = Color.Black
                )
                Text(
                    text = "View all",
                    color = Color.White
                )
            }
        }

        Card(
            modifier = Modifier
                .background(color = Color.Transparent)
                .height(220.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .shadow(5.dp, shape = RoundedCornerShape(11.dp)),
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.femal_abs),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Box(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
                    Column {
                        Text(
                            text = "Abs",
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            fontWeight = FontWeight(600)
                        )
                        Text(
                            text = "30 mins",
                            color = Color.White,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenHome(){
   Box(
       contentAlignment = Alignment.Center
   ) {
       Column {
           BodyTop()
           BodyEnd()
       }
       Box(modifier = Modifier.padding(top = 15.dp)) {
           MiddleCard()
       }
   }
}
/**-----------------------------------------Preview Scope-------------------------------------------------**/
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

@Preview(showBackground = true)
@Composable
fun BodyTopPreview(){
    CustomBottomBarTheme {
        BodyTop()
    }
}

@Preview(showBackground = true)
@Composable
fun BodyEndPreview(){
    CustomBottomBarTheme {
        BodyEnd()
    }
}

@Preview(showBackground = true)
@Composable
fun MiddleCardPreview(){
    CustomBottomBarTheme {
        MiddleCard()
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenHomePreview(){
    CustomBottomBarTheme {
        ScreenHome()
    }
}

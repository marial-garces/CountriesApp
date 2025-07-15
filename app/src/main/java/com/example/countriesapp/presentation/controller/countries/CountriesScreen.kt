package com.example.countriesapp.presentation.controller.countries

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.R
import com.example.countriesapp.presentation.components.CountriesBackground
import com.example.countriesapp.presentation.components.noRippleClickable
import com.example.countriesapp.presentation.navigation.Screen
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountriesScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                state = rememberTopAppBarState()
            )
            TopBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) {  paddingValues ->
        CountriesBackground(painter = R.drawable.background_app)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {

                items(count = 10) { index ->
                    CountriesCardView(navController = navController)
                }
            }
        }

    }
}

//@Composable
//fun CountriesContent(paddingValues: PaddingValues){
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = paddingValues.calculateTopPadding())
//    ){
//        Card(
//            modifier = Modifier.fillMaxSize(),
//        ){
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//
//            ) {
//
//            }
//        }
//
//    }
//}


@Composable
fun CountriesCardView(navController: NavController){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 1.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountriesCard(navController)
        CountriesCard(navController)
    }
}

@Composable
fun CountriesCard(navController: NavController){
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(5.dp)
            .noRippleClickable {
                navController.popBackStack()
                navController.navigate(Screen.States.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2EF)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.dominican),
                contentDescription = "Country Flag",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(80.dp)
                    .aspectRatio(1f)
            )
            Text(
                text = "Dominican Republic",
                fontSize = 24.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF443E32),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior){

    TopAppBar(
        scrollBehavior =scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE9E4E0)),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.public_icon),
                    contentDescription = "World Icon",
                    colorFilter = ColorFilter.tint(Color(0xFF443E32)),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(end = 4.dp),
                )

                Text(
                    text = "Countries",
                    fontSize = 30.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF443E32),
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    )
}


@Preview
@Composable
fun CountriesCardViewPreview() {
    val navController = rememberNavController()

    CountriesCardView(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBarPreview() {
    TopBar(scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior())
}

@Preview
@Composable
fun CountriesScreenPreview(){
    val navController = rememberNavController()
    CountriesScreen(navController = navController)
}

//@Preview
//@Composable
//fun CountriesContentPreview(){
//    CountriesContent(
//        paddingValues = PaddingValues(
//            top = 24.dp,
//            bottom = 16.dp
//        )
//    )
//}
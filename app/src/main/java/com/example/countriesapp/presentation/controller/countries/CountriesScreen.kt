package com.example.countriesapp.presentation.controller.countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.R
import com.example.countriesapp.presentation.components.noRippleClickable
import com.example.countriesapp.presentation.navigation.Screen
import java.nio.file.WatchEvent

@Composable
fun CountriesScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Countries Screen",
            fontSize = 44.sp
        )
    }
}


@Composable
fun CountriesCardView(navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation =10.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color(0xFFA0A0A0)
            )
            .noRippleClickable {
//                navController.popBackStack()
//                navController.navigate(Screen.States.route)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier.background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp) ),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(R.drawable.dominican),
                    contentDescription = "Country Flag",
                    modifier = Modifier
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                        .size(90.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Dominican Republic",
                        fontSize = 24.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF000000)
                    )
                }
            }
        }

    }
}

@Composable
fun TopAppBar(){

    Column(modifier = Modifier.fillMaxWidth().padding(top = 28.dp, bottom = 24.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.public_icon),
                contentDescription = "World Icon",
                colorFilter = ColorFilter.tint(Color(0xFF000000)),
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 8.dp),
            )

            Text(
                text = "Countries",
                fontSize = 30.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000),
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun CountriesCardViewPreview() {
    val navController = rememberNavController()

    CountriesCardView(navController = navController)
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}
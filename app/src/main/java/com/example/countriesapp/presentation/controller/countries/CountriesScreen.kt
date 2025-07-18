package com.example.countriesapp.presentation.controller.countries

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.countriesapp.R
import com.example.countriesapp.data.countries.model.Country
import com.example.countriesapp.presentation.components.CountriesBackground
import com.example.countriesapp.presentation.components.noRippleClickable
import com.example.countriesapp.presentation.navigation.Screen
import com.example.countriesapp.presentation.viewmodel.CountriesViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CountriesScreen(navController: NavController, viewModel: CountriesViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

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
            when {
                uiState.value.isLoading -> {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                uiState.value.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error: ${uiState.value.error}",
                            color = Color(0xFFC99F4A),
                            modifier = Modifier.padding(16.dp)
                        )
                        TextButton(onClick = {viewModel.retry()}) {
                            Text("Retry")
                        }
                    }
                }
                true -> {
                    CountriesContent(countries = uiState.value.countries, navController = navController)

                }
                else -> {
                    Text("No countries available")
                }
            }
        }

    }
}

@Composable
fun CountriesContent(countries: List<Country>, navController: NavController){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = countries.chunked(2),
            key = { row -> row.first().iso2 + (row.getOrNull(1)?.iso2 ?: "") },
        ){countryRow ->
            CountriesCardRow(countries = countryRow, navController = navController)
        }
    }
}


@Composable
fun CountriesCardRow(navController: NavController, countries: List<Country>){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 1.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        countries.forEach { country ->
            CountriesCard(
                country = country,
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CountriesCard(navController: NavController, country: Country, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(5.dp)
            .noRippleClickable {
                val encoded = URLEncoder.encode(country.name, StandardCharsets.UTF_8.toString())
                navController.navigate(Screen.States.createRoute(encoded))
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2EF)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model= country.flag,
                contentDescription = "Flag of ${country.name}",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(80.dp)
                    .aspectRatio(1f),
                error = painterResource(R.drawable.dominican),
            )
            Text(
                text = country.name,
                fontSize = 24.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF443E32),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp),
                maxLines = 2
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
//    val navController = rememberNavController()

//    CountriesCardRow(
//        navController = navController,
//        countries =
//    )
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
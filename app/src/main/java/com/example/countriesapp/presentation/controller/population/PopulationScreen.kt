package com.example.countriesapp.presentation.controller.population

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.R
import com.example.countriesapp.R.*
import com.example.countriesapp.data.states.model.PopulationCount
import com.example.countriesapp.presentation.components.CountriesBackground
import com.example.countriesapp.presentation.components.noRippleClickable
import com.example.countriesapp.presentation.viewmodel.PopulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PopulationScreen(navController: NavController, viewModel: PopulationViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                state = rememberTopAppBarState()
            )
            PopulationTopBar(scrollBehavior)
        }
    ) { paddingValues ->
        CountriesBackground(painter = drawable.background_app)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            when {
                uiState.value.isLoading -> {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
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
                        Text(text = "Retry", modifier = Modifier
                            .padding(8.dp)
                            .noRippleClickable { viewModel.retry() })
                    }
                }
                else -> {
                    PopulationContent(counts = uiState.value.counts)
                }
            }
        }
    }
}

@Composable
fun PopulationContent(counts: List<PopulationCount>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(counts) { count ->
            PopulationRow(count)
        }
    }
}

@Composable
fun PopulationRow(count: PopulationCount) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2EF)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = count.year, fontSize = 20.sp)
            Text(text = count.value, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopulationTopBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
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
                    painter = painterResource(id = drawable.public_icon),
                    contentDescription = "World Icon",
                    colorFilter = ColorFilter.tint(Color(0xFF443E32)),
                    modifier = Modifier
                        .size(45.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = "Population",
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
fun PopulationScreenPreview() {
    val navController = rememberNavController()
    PopulationScreen(navController = navController)
}
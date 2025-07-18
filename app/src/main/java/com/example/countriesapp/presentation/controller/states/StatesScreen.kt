package com.example.countriesapp.presentation.controller.states

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapp.R
import com.example.countriesapp.data.states.model.States
import com.example.countriesapp.presentation.components.CountriesBackground
import com.example.countriesapp.presentation.viewmodel.StatesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StateScreenController(navController: NavController, viewModel: StatesViewModel = hiltViewModel()){
    Scaffold(
        modifier = Modifier,
        topBar = {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
                state = rememberTopAppBarState()
            )
            TopBarState(scrollBehavior)
        }
    ) {paddingValues ->
        CountriesBackground(painter = R.drawable.background_app)

        val uiState = viewModel.uiState.collectAsState()

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
                        OutlinedButton(onClick = { viewModel.retry() }) {
                            Text("Retry")
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp)
                    ) {
                        items(uiState.value.states.size){ index ->
                            StateItem(states = uiState.value.states[index])
                        }
                    }
                }
            }

//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = PaddingValues(top = 8.dp, bottom = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(1.dp)
//            ) {
//
//                items(count = 10) { index ->
//                    StateItem()
//                }
//            }
        }
    }
}


@Composable
fun StateItem(states: States) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F2EF)),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        StateItemContent(states)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarState(scrollBehavior: TopAppBarScrollBehavior){
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


@Composable
fun StateItemContent(states: States) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(modifier = Modifier.weight(1f).padding(12.dp)) {
            Text(
                text = states.name,
                fontSize = 23.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            if (expanded){
                StateCityList()
            }
        }

        IconButton(onClick =  { expanded = !expanded }) {
            Icon(
                imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded){
                    stringResource(R.string.show_less)
                }else{
                    stringResource(R.string.show_more)
                }
            )

        }
    }

}


@Composable
fun StateCityList() {
    Row(modifier = Modifier.padding(3.dp)) {
        Row (
            modifier = Modifier
                .weight(1.5f),
            horizontalArrangement = Arrangement.Start,

        ) {
            Text(
                text = "City Name",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 12.dp, start = 5.dp),
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .padding(top = 6.dp)
                .size(35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.population),
                contentDescription = "Population Icon",
                tint = Color(0xFF443E32),
                )
        }
    }
    Box(
        modifier = Modifier
            .width(500.dp)
            .height(1.dp)
            .background(Color.LightGray)
    )
}






@Preview(showBackground = true)
@Composable
fun StateCityListPreview(){
    StateCityList()
}

@Preview
@Composable
fun StateItemContentPreview() {
    StateItemContent(states = States("Preview"))
}

@Preview
@Composable
fun StateItemPreview() {
    StateItem(states = States("Preview"))
}

@Preview
@Composable
fun StateScreenControllerPreview() {
    val navController = rememberNavController()
    StateScreenController(navController)
}
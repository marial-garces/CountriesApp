package com.example.countriesapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.countriesapp.R

@Composable
fun CountriesBackground(painter: Int = R.drawable.background_app){
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(painter),
            contentDescription = "background_image",
            contentScale = ContentScale.FillBounds
        )
    }
}
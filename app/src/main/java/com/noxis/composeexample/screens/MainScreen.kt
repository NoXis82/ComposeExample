package com.noxis.composeexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.noxis.composeexample.R
import com.noxis.composeexample.ui.theme.BlueLight


@Composable
@Preview(showBackground = true)
fun MainScreen() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(BlueLight),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(10.dp)
        ) {

        }
    }
}
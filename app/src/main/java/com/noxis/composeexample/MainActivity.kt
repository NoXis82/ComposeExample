package com.noxis.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Row(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                horizontalArrangement = Arrangement.SpaceEvenly,
                //verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.background(Color.Cyan).fillMaxWidth(0.5f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Column Text1")
                    Text(text = "Column Text2")
                    Text(text = "Column Text3")
                }
                Column(
                    modifier = Modifier
                        .background(Color.Red)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Column1 Text1")
                    Text(text = "Column1 Text2")
                    Text(text = "Column1 Text3")
                }
                Column(
                    modifier = Modifier
                        .background(Color.Green)
                        .fillMaxHeight(0.5f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Column2 Text1")
                    Text(text = "Column2 Text2")
                    Text(text = "Column2 Text3")
                }
            }
        }
    }
}

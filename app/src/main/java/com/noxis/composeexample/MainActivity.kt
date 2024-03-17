package com.noxis.composeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ListItem(name = "Name Actor1", prof = "Actor")
                ListItem(name = "Name Actor2", prof = "Actor")
                ListItem(name = "Name Actor3", prof = "Actor")
                ListItem(name = "Name Actor4", prof = "Actor")
                ListItem(name = "Name Actor5", prof = "Actor")
                ListItem(name = "Name Actor6", prof = "Actor")
                ListItem(name = "Name Actor7", prof = "Actor")
                ListItem(name = "Name Actor8", prof = "Actor")
                ListItem(name = "Name Actor9", prof = "Actor")
                ListItem(name = "Name Actor10", prof = "Actor")
                ListItem(name = "Name Actor11", prof = "Actor")
                ListItem(name = "Name Actor12", prof = "Actor")
            }
        }
    }
}

@Composable
private fun ListItem(name: String, prof: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
//            .clickable {
//                Log.d("TAG", "ListItem: $name, $prof")
//            }
            .pointerInput(Unit) {
                detectTapGestures {
                    Log.d("TAG", "ListItem Tap: $it")
                }
//                detectDragGesturesAfterLongPress { change, dragAmount ->
//                    Log.d("TAG", "ListItem Long press: $dragAmount")
//                }
            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.batman),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(4.dp)
                        .clip(CircleShape)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(text = name)
                    Text(text = prof)
                }
            }
        }
    }
}


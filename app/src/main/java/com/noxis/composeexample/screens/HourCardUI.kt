package com.noxis.composeexample.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.noxis.composeexample.data.WeatherModel
import com.noxis.composeexample.ui.theme.BlueLight

@Composable
fun HourCardUI(item: WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        colors = CardDefaults.cardColors(BlueLight),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 6.dp, top = 6.dp, bottom = 6.dp)
            ) {
                Text(text = item.time)
                Text(text = item.condition, style = TextStyle(color = Color.White))
            }
            Text(text = item.currentTempC.ifEmpty { "${item.minTempC}/${item.maxTempC}" }, style = TextStyle(color = Color.White, fontSize = 24.sp))
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "image_3",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 6.dp)
            )
        }
    }
}
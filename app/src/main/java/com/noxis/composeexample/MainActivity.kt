package com.noxis.composeexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.noxis.composeexample.data.WeatherModel
import com.noxis.composeexample.screens.MainCard
import com.noxis.composeexample.screens.TabLayout
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val daysList = remember {
                mutableStateOf(listOf<WeatherModel>())
            }
            getDataWeather(CITY, daysList, this)
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "image",
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )
            Column {
                MainCard()
                TabLayout(daysList)
            }
        }
    }

    companion object {
        const val API_KEY = "2d25b81b309c4ac5aee122007241803"
        const val CITY = "Saint-Petersburg"
    }
}

private fun getDataWeather(city: String, state: MutableState<List<WeatherModel>>, context: Context) {
    val url = "https://api.weatherapi.com/v1/forecast.json" +
            "?key=${MainActivity.API_KEY}" +
            "&q=$city" +
            "&days=3" +
            "&aqi=no" +
            "&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            state.value = getWeatherByDays(response)
        },
        { error ->
            Log.e("MyTAG", "getDataWeather: ${error.message}")
        }
    )
    queue.add(stringRequest)
}

private fun getWeatherByDays(response: String): List<WeatherModel> {
    val dataWeatherList = arrayListOf<WeatherModel>()
    val obj = JSONObject(response)
    val locale = obj.getJSONObject("location").getString("name")
    val days = obj.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        val dayInfo = item.getJSONObject("day")
        val conditionInfo = item.getJSONObject("day").getJSONObject("condition")
        dataWeatherList.add(
            WeatherModel(
                city = locale,
                time = item.getString("date"),
                currentTempC = "",
                condition = conditionInfo.getString("text"),
                icon = conditionInfo.getString("icon"),
                maxTempC = dayInfo.getString("maxtemp_c"),
                minTempC = dayInfo.getString("mintemp_c"),
                hours = item.getJSONArray("hour").toString()
            )
        )
    }
    return dataWeatherList
}

package com.noxis.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.noxis.composeexample.screens.MainCard
import com.noxis.composeexample.screens.TabLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                TabLayout()
            }
        }
    }

    companion object {
        const val API_KEY = "2d25b81b309c4ac5aee122007241803"
        const val CITY = "Saint-Petersburg"
    }
}

//private fun getResult(city: String, state: MutableState<String>, context: Context) {
//    val url = "https://api.weatherapi.com/v1/current.json" +
//            "?key=${MainActivity.API_KEY}&" +
//            "q=$city" +
//            "&aqi=no"
//    val queue = Volley.newRequestQueue(context)
//    val stringRequest = StringRequest(
//        com.android.volley.Request.Method.GET,
//        url,
//        { response ->
//            val obj = JSONObject(response)
//            state.value = obj.getJSONObject("current").getString("temp_c")
//        },
//        { error ->
//            state.value = error.networkResponse.statusCode.toString()
//        }
//    )
//    queue.add(stringRequest)
//
//}

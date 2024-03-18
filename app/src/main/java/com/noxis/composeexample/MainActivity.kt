package com.noxis.composeexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.noxis.composeexample.screens.MainScreen
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
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

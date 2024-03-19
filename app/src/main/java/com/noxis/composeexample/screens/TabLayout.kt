package com.noxis.composeexample.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.noxis.composeexample.data.WeatherModel
import com.noxis.composeexample.ui.theme.BlueLight
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(
    daysList: MutableState<List<WeatherModel>>,
    currentDayInfo: MutableState<WeatherModel>
) {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(start = 6.dp, end = 6.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(pos[tabIndex])
                )
            },
            containerColor = BlueLight,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, name ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = name) }
                )
            }
        }
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1.0f)) { page ->
           val list = when(page) {
                0 -> getWeatherByHours(currentDayInfo.value.hours)
                1 -> daysList.value
                else -> daysList.value
            }
            MainList(list, currentDayInfo,pagerState)
        }
    }
}

private fun getWeatherByHours(hours: String): List<WeatherModel> {
    val hourInfo = arrayListOf<WeatherModel>()
    if (hours.isEmpty()) return hourInfo
    val hoursArray = JSONArray(hours)
    for (i in 0 until hoursArray.length()) {
        val item = hoursArray[i] as JSONObject
        hourInfo.add(
            WeatherModel(
                time = item.getString("time"),
                currentTempC = "${item.getString("temp_c").toFloatOrNull()?.toInt()}℃",
                condition = item.getJSONObject("condition").getString("text"),
                icon = item.getJSONObject("condition").getString("icon"),
            )
        )
    }
    return hourInfo
}
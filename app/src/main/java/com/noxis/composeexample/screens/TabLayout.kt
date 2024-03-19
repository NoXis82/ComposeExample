package com.noxis.composeexample.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.noxis.composeexample.data.WeatherModel
import com.noxis.composeexample.ui.theme.BlueLight
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout() {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState(pageCount = { tabList.size })
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    val testModel = listOf(
        WeatherModel(
            "Saint Petersburg",
            "9:00",
            "1.0℃",
            "Partly cloudy",
            "//cdn.weatherapi.com/weather/64x64/day/116.png",
            "",
            "",
            ""
        ),
        WeatherModel(
            "Saint Petersburg",
            "2024-03-19",
            "",
            "Cloudy",
            "//cdn.weatherapi.com/weather/64x64/day/119.png",
            "2.8℃",
            "-0.4℃",
            ""
            )
    )

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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                itemsIndexed(testModel) { _, item ->
                    HourCardUI(item)
                }
            }
        }
    }
}
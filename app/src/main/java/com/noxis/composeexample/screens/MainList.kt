package com.noxis.composeexample.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.noxis.composeexample.data.WeatherModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainList(
    days: List<WeatherModel>,
    currentDayInfo: MutableState<WeatherModel>,
    pagerState: PagerState
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        itemsIndexed(days) { _, item ->
            HourCardUI(item,pagerState, currentDayInfo)
        }
    }
}
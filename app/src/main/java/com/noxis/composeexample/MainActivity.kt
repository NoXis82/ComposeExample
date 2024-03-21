package com.noxis.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noxis.composeexample.ui.theme.ComposeExampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                val searchText = remember {
                    mutableStateOf("")
                }
                val isActive = remember {
                    mutableStateOf(false)
                }
                val listState = remember {
                    mutableStateOf(Until.names)
                }

                Column {
                    SearchBar(
                        modifier = Modifier.padding(6.dp),
                        query = searchText.value,
                        onQueryChange = { text ->
                            searchText.value = text
                            listState.value = Until.search(text)
                        },
                        onSearch = {
                            isActive.value = false
                            searchText.value = ""
                        },
                        placeholder = { Text(text = "Search...") },
                        active = isActive.value,
                        onActiveChange = { isActive.value = it }
                    ) {
                        LazyColumn {
                            items(listState.value) {
                                Text(text = it, modifier = Modifier.padding(6.dp).clickable {
                                    searchText.value = it
                                    listState.value = Until.search(it)
                                    isActive.value = false
                                })
                            }
                        }
                    }
                    LazyColumn {
                        items(listState.value) {
                            Text(text = it, modifier = Modifier.padding(6.dp))
                        }
                    }
                }
            }
        }
    }
}

object Until {
    val names = listOf(
        "Egor",
        "Andrey",
        "Slava",
        "Oleg",
        "Victor",
        "Sergey"
    )

    fun search(text: String): List<String> {
        return names.filter { it.lowercase().startsWith(text.lowercase()) }
    }
}
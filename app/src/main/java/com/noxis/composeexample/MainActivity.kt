package com.noxis.composeexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(applicationContext)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(applicationContext: Context) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(0) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Drawer title",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.Red)
                        .wrapContentHeight(Alignment.CenterVertically)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    fontSize = 26.sp,
                    color = Color.White

                )
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item 1") },
                    selected = selectedItem.value == 1,
                    shape = RoundedCornerShape(0.dp),
                    icon = {
                        Icon(imageVector = Icons.Filled.Call, contentDescription = null)
                    },
                    onClick = {
                        coroutineScope.launch {
                            selectedItem.value = 1
                            drawerState.close()
                        }
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item 2") },
                    selected = selectedItem.value == 2,
                    icon = {
                        Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = null)
                    },
                    onClick = {
                        coroutineScope.launch {
                            selectedItem.value = 2
                            drawerState.close()
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState) { data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = "AppBar") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    "Search",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = {
                            coroutineScope.launch {
                                val result =
                                    snackbarHostState.showSnackbar(
                                        "Search",
                                        actionLabel = "Action",
                                        duration = SnackbarDuration.Short
                                    )
                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        Toast.makeText(
                                            applicationContext,
                                            "Build ActionPerformed",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                    }

                                    SnackbarResult.Dismissed -> {
                                        Toast.makeText(
                                            applicationContext,
                                            "Build Dismissed",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Build, contentDescription = "Build")
                        }
                    }
                )
            }
        ) {
            it
        }
    }
}

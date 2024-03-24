package com.noxis.composeexample.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


}
package com.noxis.composeexample.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noxis.composeexample.repository.MainRepository
import com.noxis.lib_product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository
) : ViewModel() {

    private val _stateProduct = MutableStateFlow(mutableListOf<Product>())
    val stateProduct = _stateProduct.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllProducts().collect {
                _stateProduct.value = it.toMutableList()
            }
        }
    }

    fun addProduct(product: Product) {
        println("Product: $product")
        viewModelScope.launch {
            repository.addProduct(product)
            repository.getAllProducts().collect {
                _stateProduct.value = it.toMutableList()
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
            _stateProduct.value = mutableListOf()
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
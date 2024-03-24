package com.noxis.composeexample.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noxis.composeexample.repository.MainRepository
import com.noxis.lib_product.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository
) : ViewModel() {

    private val test = arrayListOf<Product>()

    var products = repository.getAllProducts()

    private val _stateProduct = MutableStateFlow(arrayListOf<Product>())
    val stateProduct = _stateProduct.asStateFlow()

//    init {
//        viewModelScope.launch {
//            repository.getAllProducts().onEach {
//                println("List product: $it")
//                _stateProduct.value.addAll(it)
//            }
//        }
//    }

    fun addProduct(product: Product) {
        println("Product: $product")
        viewModelScope.launch {
            repository.addProduct(product)
         //   test.add(product)
         //   println(test)
         //   _stateProduct.value.addAll(test)
            products = repository.getAllProducts()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
            products = repository.getAllProducts()
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
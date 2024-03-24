package com.noxis.composeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.noxis.composeexample.ui.theme.ComposeExampleTheme
import com.noxis.composeexample.viewmodel.MainViewModel
import com.noxis.lib_product.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        result.contents?.let {
            Toast.makeText(this, "Scanned:$it", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {
                val viewModel = viewModel<MainViewModel>()
                val coroutineScope = rememberCoroutineScope()
                val products = viewModel.stateProduct.collectAsState()
                println("Products: ${products.value}")
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.9f)
                    ) {

                        items(products.value) { product ->
                            Text(text = product.name, textAlign = TextAlign.Center)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = {
                            coroutineScope.launch {
                                counter++
                                viewModel.addProduct(
                                    Product(
                                        id = 0,
                                        name = "Product $counter",
                                        "Test $counter",
                                        null,
                                        null
                                    )
                                )
                            }
                        }) {
                            Text(text = "Add product")
                        }

                        Button(onClick = {
                            viewModel.clearAll()
                        }) {
                            Text(text = "Clear all")
                        }
                    }
                }

            }
        }
    }

    private fun scan() {
        val options = ScanOptions()
        options.apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan a barcode")
            setCameraId(0)  // Use a specific camera of the device
            setBeepEnabled(false)
            setBarcodeImageEnabled(true)
        }
        barcodeLauncher.launch(options)
    }
}
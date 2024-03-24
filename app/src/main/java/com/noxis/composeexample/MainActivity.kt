package com.noxis.composeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.noxis.composeexample.ui.theme.ComposeExampleTheme
import com.noxis.composeexample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        result.contents?.let {
            Toast.makeText(this, "Scanned:$it", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExampleTheme {


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
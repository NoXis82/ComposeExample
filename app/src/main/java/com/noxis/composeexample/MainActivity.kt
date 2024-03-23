package com.noxis.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (text, button, image) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.6f)
        Button(onClick = { }, modifier = Modifier.constrainAs(button) {
            bottom.linkTo(bottomGuideLine)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(bottomGuideLine)
        }) {
            Text(text = "Click")
        }
        Text(text = "Hello world", modifier = Modifier.constrainAs(text) {
            bottom.linkTo(button.top)
            start.linkTo(button.start)
            end.linkTo(button.end)
        })
        Image(
            painter = painterResource(id = R.drawable.simpson),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp)
                .constrainAs(image) {
                    bottom.linkTo(text.top)
                    start.linkTo(button.start)
                    end.linkTo(button.end)
                }
        )
    }
}
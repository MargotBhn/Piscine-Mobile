package com.example.ex01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ex01.ui.theme.Ex01Theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex01Theme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    // remember resets its value on configuration changes (e.g., screen rotation)
    // Use rememberSaveable to preserve state across those changes

    var welcomeText by rememberSaveable {  mutableStateOf("Hello")}
    var count by rememberSaveable { mutableIntStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = welcomeText,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.SansSerif,
            fontSize = 40.sp,
            color = Color.DarkGray
        )
        Button(
            onClick = {
                count++
                welcomeText = if (count % 2 == 0) "Hello" else "Hello World"
                      },
            modifier = Modifier.size(160.dp, 60.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = "Click me",
                color = Color.LightGray,
                fontSize = 20.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex01Theme {
        Greeting ()
    }
}
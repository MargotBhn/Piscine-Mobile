package com.example.ex02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.ex02.ui.theme.Ex02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ex02Theme {
                    Calculator()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator () {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        }

    ) {


        paddingValues ->
        Screen(
            modifier = Modifier.padding(paddingValues)
        )


    }
}

@Composable
fun Screen(modifier: Modifier = Modifier){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar (
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior){
    TopAppBar(
        modifier = Modifier.background(Color.Red),
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "Caculator"
                )

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE)
        )
        )
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex02Theme {
        Greeting("Android")
    }
}
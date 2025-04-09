package com.example.ex02


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var calcultorList = listOf(
        listOf("7", "8", "9", "C", "AC"),
        listOf("4", "5", "6", "+", "-"),
        listOf("1", "2", "3", "x", "/"),
        listOf("0", ".", "00", "=")

        )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Calculator",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary

                )

            )
        }
    )
    { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = MaterialTheme.colorScheme.background)

        ) {
            Box(
                modifier = Modifier
                    //  .padding(contentPadding)
                    .background(color = Color.Blue)
                    .fillMaxWidth(),

                contentAlignment = Alignment.CenterEnd

            ) {
                Column()
                {
                    Text(
                        text = "0",
                        fontSize = 35.sp
                    )

                    Text(
                        text = "0",
                        fontSize = 35.sp
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    //    .padding(contentPadding)
                    .background(color = Color.Red)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {

                calcultorList.forEach { ligne ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        ligne.forEach { buttonName ->
                            CalculatorButton(buttonName)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton (buttonName : String) {
   androidx.compose.material3.Button(
       onClick = {Log.d("test","$buttonName was pressed")}
   ) {
       Text(text = buttonName)
   }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex02Theme {
         Calculator()
    }
}
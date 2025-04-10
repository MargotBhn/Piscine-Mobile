package com.example.ex02



import android.content.res.Configuration
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
                CalculatorLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorLayout () {
    val calculatorList = listOf(
        listOf("7", "8", "9", "C", "AC"),
        listOf("4", "5", "6", "+", "-"),
        listOf("1", "2", "3", "x", "/"),
        listOf("0", ".", "00", "=")

        )

    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    val displayWeight = if (isPortrait) 0.4f else 0.3f
    val keypadWeight = if (isPortrait) 0.6f else 0.7f

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
                ),
                expandedHeight = 40.dp

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
                    // .background(color = Color.Blue)
                    .fillMaxSize()
                    .weight(displayWeight),

                contentAlignment = Alignment.Center
            ) {
                CalculatorDisplay()
            }
            Spacer(
                modifier = Modifier
                    .weight(0.1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(keypadWeight)
                 //   .padding(8.dp)
                    .background(Color.Green),
//                verticalArrangement = Arrangement.SpaceEvenly
                verticalArrangement = Arrangement.Bottom

            ) {

                CalculatorKeypad(calculatorList)
            }
        }
    }
}

@Composable
fun CalculatorDisplay (){
    val calculationText by remember { mutableStateOf("0")}
    Column(
        modifier = Modifier
            .fillMaxSize(),
           // .background(Color.Red),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center

    )
    {
        Text(
           text = calculationText,
            maxLines = 2,
            fontSize = 25.sp
        )

        Text(
            text = "0",
            fontSize = 35.sp
        )
    }
}

@Composable
fun CalculatorKeypad(
    calculatorList: List<List<String>>
){

    calculatorList.forEach {line ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // padding entre les boutons,
           // horizontalArrangement = Arrangement.SpaceEvenly,

            modifier = Modifier
                .fillMaxWidth()
               // .height(100.dp)
             //   .padding(4.dp),
             //   .background(Color.Blue),

                //.padding(horizontal = 3.dp, vertical = 3.dp),// padding sur les bords


        ) {
            line.forEach { buttonName ->

                if(buttonName == "=") {
                    CalculatorButton(
                        modifier = Modifier
                            .weight(2f),
                        buttonName = buttonName
                    )
                }
                else{
                    CalculatorButton(
                        modifier = Modifier
                            .weight(1f),
                        buttonName = buttonName
                    )
                }
            }

        }
    }
}

@Composable
fun CalculatorButton (buttonName: String, modifier: Modifier) {
    val buttonColor = when (buttonName) {
        "C", "AC" -> MaterialTheme.colorScheme.surfaceContainer
        "=" -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.secondaryContainer
    }

    androidx.compose.material3.Button(
        onClick = { Log.d("test", "$buttonName was pressed") },
        modifier = modifier
            .fillMaxWidth(),
        // shape = RoundedCornerShape(60.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 1.dp
        )
    ) {
        Text(text = buttonName)
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ex02Theme {
         CalculatorLayout()
    }
}
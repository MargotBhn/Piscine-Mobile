package com.example.calculator_app

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator_app.ui.theme.Calculator_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculator_appTheme {
                CalculatorLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorLayout (viewModel: CalculatorViewModel = viewModel()) {
    val calculatorList = listOf(
        listOf("7", "8", "9", "C", "AC"),
        listOf("4", "5", "6", "+", "-"),
        listOf("1", "2", "3", "x", "/"),
        listOf("0", ".", "00", "=")

    )

    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

    val displayWeight = if (isPortrait) 0.5f else 0.4f
    val keypadWeight = if (isPortrait) 0.5f else 0.6f


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
                    .fillMaxSize()
                    .weight(displayWeight)
                    .padding(16.dp),

                ) {
                CalculatorDisplay(
                   viewModel.resultValue,
                   viewModel.enteredValue
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(keypadWeight)
                    .padding(4.dp)
            )
            {
                CalculatorKeypad(calculatorList)
            }
        }
    }
}

@Composable
fun CalculatorDisplay (
    enteredValue: String,
    resultValue: String
){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val adaptiveFontSizeCalculation = (minOf(screenWidth, screenHeight) / 15).sp
    val adaptiveFontSizeResult = (minOf(screenWidth, screenHeight) / 10).sp

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center

    )
    {
        Text(
            text = enteredValue,
            maxLines = 2,
            fontSize = adaptiveFontSizeCalculation,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End

        )

        Text(
            text = resultValue,
            fontSize = adaptiveFontSizeResult,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun CalculatorKeypad(
    calculatorList: List<List<String>>
){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        calculatorList.forEach {line ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                line.forEach { buttonName ->
                    Box(
                        modifier = Modifier
                            .weight(if (buttonName == "=") 2f else 1f)
                            .padding(3.dp)
                            .fillMaxHeight()
                    ){
                        CalculatorButton(
                            buttonName,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton (buttonName: String,
                      modifier: Modifier,
                      viewModel: CalculatorViewModel = viewModel()
) {
    val buttonColor = when (buttonName) {
        "C", "AC" -> MaterialTheme.colorScheme.errorContainer
        "+", "-", "x", "/" -> MaterialTheme.colorScheme.secondaryContainer
        "=" -> MaterialTheme.colorScheme.tertiaryContainer
        else -> MaterialTheme.colorScheme.primaryContainer
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    val adaptiveFontSize = (minOf(screenWidth, screenHeight) / 30).sp

    androidx.compose.material3.Button(
        onClick = {
            Log.d("test", "$buttonName was pressed")
            viewModel.checkSyntax(buttonName)
        },
        modifier = modifier
            .fillMaxWidth(),
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
        Text(
            text = buttonName,
            fontSize = adaptiveFontSize,
            fontFamily = FontFamily.SansSerif,
            color = MaterialTheme.colorScheme.onSurface

        )
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Calculator_appTheme {
        CalculatorLayout()
    }
}
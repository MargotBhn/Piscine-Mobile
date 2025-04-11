package com.example.calculator_app

import androidx.collection.emptyLongSet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel (){

    val numericList = listOf("00", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    val signList = listOf("+", "-")
    val operationList = listOf("+", "-", "x", "/")


    var enteredValue by mutableStateOf("0")
        private set
    var resultValue by mutableStateOf("")
        private set

    fun checkSyntax(input: String){
        when (input){
            "C" -> removeLastCharacter()
            "AC" -> eraseAll()

            else -> parsing(input)
        }
        calculateResult()
    }

    private fun parsing(input:String)
    {
        when{
            input == "." && enteredValue.isEmpty() -> enteredValue = "0."
            input in operationList && enteredValue.isNotEmpty() && enteredValue.last().toString() in operationList -> enteredValue = enteredValue.dropLast(1) + input

            else -> enteredValue += input
        }


    }


    private fun removeLastCharacter(){
        enteredValue = if (enteredValue.isNotEmpty()) enteredValue.dropLast(1) else enteredValue
        calculateResult()
    }

    private fun eraseAll(){
        enteredValue = "0"
        resultValue = ""
    }
    private fun calculateResult(){

    }

}
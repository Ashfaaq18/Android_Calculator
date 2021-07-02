package com.example.calculator.ui.standard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.App
import com.example.calculator.R
import net.objecthunter.exp4j.ExpressionBuilder

class StandardViewModel : ViewModel() {

    private val _answer = MutableLiveData("")
    private var equation = ""

    fun answer():LiveData<String>{
        return _answer
    }

    fun calculate(){

        val eval = ExpressionBuilder(equation).build()

        if(eval.validate().isValid)
            _answer.value = eval.evaluate().toString()
    }

    fun setString(readChar:String, cursorPos: Int, stringLength:Int){
        var varReadChar = ""
        if(readChar == App.resourses.getString(R.string.BtnStr_mul)){
            varReadChar = "*"
        }
        else if(readChar == App.resourses.getString(R.string.BtnStr_div)){
            varReadChar = "/"
        }
        else{
            varReadChar = readChar
        }

        if(cursorPos == stringLength){
            equation = "${equation}${varReadChar}"
        }
        else{
            equation = "${equation.substring(0,cursorPos-1)}${varReadChar}${equation.substring(cursorPos-1,stringLength-1)}"
        }
        calculate()
    }

    fun deleteStringChar(pos:Int, stringLength:Int){
        if(pos == stringLength){
            equation = "${equation.substring(0,pos-1)}"
        }
        else{
            equation = "${equation.substring(0,pos-1)}${equation.substring(pos,stringLength)}"
        }
        //setString("",pos-1,stringLength-1)
        calculate()
    }

    fun getString():String{
        return equation
    }

    fun reset(){
        equation = ""
    }

}
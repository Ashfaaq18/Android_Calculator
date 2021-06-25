package com.example.calculator.ui.standard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.App
import com.example.calculator.R
import com.example.calculator.logic.Arithmetic

class StandardViewModel : ViewModel() {

    val app = App()
    private val _answer = MutableLiveData("")
    val arithmetic = Arithmetic()
    private var fullEditTextInput:String = ""
    fun answer():LiveData<String>{
        return _answer
    }

    fun setDigit(readChar:String){
        fullEditTextInput = readChar
       // _answer.value = readChar
    }

    fun setOper(readChar:String){
        if(readChar == App.resourses.getString(R.string.BtnStr_add)){
            _answer.value += "1"
        }
    }

    fun interpretInput() {

    }

}
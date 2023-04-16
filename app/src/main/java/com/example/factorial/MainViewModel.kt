package com.example.factorial

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.math.BigInteger

class MainViewModel(
    application: Application
): ViewModel() {

    private val repository = DatabaseRepository(HistoryDatabase.getInstance(application).itemDao())

    val history = repository.getItemList()

    private val _errorInput = MutableLiveData<Boolean>()
    val errorInput: LiveData<Boolean>
        get() = _errorInput

    fun getResult(number: String) = viewModelScope.launch {
        var result = 0L
        val string = number.trim()
        var resultString = ""
        if (isValidInput(string)) {
            try {
                resultString = "$string! = ${getFactorial(string.toLong())}"
            } catch (e: java.lang.Exception) {
                _errorInput.value = true
                resultString = "Ошибка!"
            }
        }
        val item = HistoryItem(expression = resultString)
        repository.addItem(item)
    }

    fun getFactorial(n: Long): BigInteger {
        var result = BigInteger.ONE
        for (i in 2..n) {
            result = result.multiply(BigInteger.valueOf(i.toLong()))
        }
        return result
    }

    fun deleteTodoItem(item: HistoryItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    private fun isValidInput(input: String): Boolean {
        var result = true
        if (input.isBlank() || (input == "")) {
            _errorInput.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputDesc() {
        _errorInput.value = false
    }
}
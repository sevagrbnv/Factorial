package com.example.factorial

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigInteger

class MainViewModelTest {

    @Test
    fun testFactorial0() {
        val viewModel = MainViewModel(App())
        val result = viewModel.getFactorial(0)
        assertEquals(BigInteger("1"), result)
    }

    @Test
    fun testFactorial5() {
        val viewModel = MainViewModel(App())
        val result = viewModel.getFactorial(5)
        assertEquals(BigInteger("120"), result)
    }

    @Test
    fun testFactorial10() {
        val viewModel = MainViewModel(App())
        val result = viewModel.getFactorial(10)
        assertEquals(BigInteger("3628800"), result)
    }

    @Test
    fun testFactorial14() {
        val viewModel = MainViewModel(App())
        val result = viewModel.getFactorial(14)
        assertEquals(BigInteger("87178291200"), result)
    }
}
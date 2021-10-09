package com.tiooooo.gmtrading.ui.main

import com.tiooooo.gmtrading.model.Currency
import com.tiooooo.gmtrading.model.Items
import com.tiooooo.gmtrading.util.InputConverter
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private val glob = "glob is I"
    private val prok = "prok is V"
    private val pish = "pish is X"
    private val tegj = "tegj is L"

    private val creditsOne = "glob glob Silver is 34 Credits"
    private val creditsTwo = "glob prok Gold is 57800 Credits"
    private val creditsThree = "pish pish Iron is 3910 Credits"

    private val question1 = "how much is pish tegj glob glob ?"
    private val question2 = "how many credits is glob prok Silver ?"
    private val question3 = "how many credits is glob prok Gold ?"
    private val question4 = "how many credits is glob prok Iron ?"
    private val question5 = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"

    private val expectedAnswer1 = "pish tegj glob glob is 42"
    private val expectedAnswer2 = "glob prok silver is 68 Credits"
    private val expectedAnswer3 = "glob prok gold is 57800 Credits"
    private val expectedAnswer4 = "glob prok iron is 782 Credits"
    private val expectedAnswer5 = "I have no idea what you are talking about"



    @Before
    fun before(){
        mainViewModel = MainViewModel()
        mainViewModel.manageInputTesting(glob)
        mainViewModel.manageInputTesting(prok)
        mainViewModel.manageInputTesting(pish)
        mainViewModel.manageInputTesting(tegj)

        mainViewModel.manageInputTesting(creditsOne)
        mainViewModel.manageInputTesting(creditsTwo)
        mainViewModel.manageInputTesting(creditsThree)
    }



    @Test
    fun getResultTextQuestion1() {
        val answer = mainViewModel.manageInputTesting(question1)
        assertEquals(expectedAnswer1,answer)
    }

    @Test
    fun getResultTextQuestion2() {
        val answer = mainViewModel.manageInputTesting(question2)
        assertEquals(expectedAnswer2,answer)
    }

    @Test
    fun getResultTextQuestion3() {
        val answer = mainViewModel.manageInputTesting(question3)
        assertEquals(expectedAnswer3,answer)
    }

    @Test
    fun getResultTextQuestion4() {
        val answer = mainViewModel.manageInputTesting(question4)
        assertEquals(expectedAnswer4,answer)
    }

    @Test
    fun getResultTextQuestion5() {
        val answer = mainViewModel.manageInputTesting(question5)
        assertEquals(expectedAnswer5,answer)
    }
}
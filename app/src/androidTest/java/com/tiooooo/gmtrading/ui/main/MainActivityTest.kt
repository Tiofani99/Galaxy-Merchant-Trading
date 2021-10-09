package com.tiooooo.gmtrading.ui.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tiooooo.gmtrading.R
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

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
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.edtQuery)).perform(typeText(glob), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(prok), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(pish), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(tegj), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(creditsOne), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(creditsTwo), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(creditsThree), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())
    }

    @Test
    fun testQuestion1(){
        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(question1), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.tvAnswer)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAnswer)).check(matches(withText(expectedAnswer1)))
    }

    @Test
    fun testQuestion2(){
        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(question2), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.tvAnswer)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAnswer)).check(matches(withText(expectedAnswer2)))
    }

    @Test
    fun testQuestion3(){
        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(question3), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.tvAnswer)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAnswer)).check(matches(withText(expectedAnswer3)))
    }

    @Test
    fun testQuestion4(){
        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(question4), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.tvAnswer)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAnswer)).check(matches(withText(expectedAnswer4)))
    }

    @Test
    fun testQuestion5(){
        onView(withId(R.id.edtQuery)).perform(clearText()).perform(typeText(question5), closeSoftKeyboard())
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.tvAnswer)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAnswer)).check(matches(withText(expectedAnswer5)))
    }
}
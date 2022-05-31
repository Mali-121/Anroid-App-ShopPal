package com.myshoppal.ui.activities


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.myshoppal.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest7 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest7() {
        val editText = onView(
            allOf(
                withId(R.id.et_email), withText("Email ID"),
                withParent(withParent(withId(R.id.til_email))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Email ID")))

        val editText2 = onView(
            allOf(
                withId(R.id.et_password), withText("Password"),
                withParent(withParent(withId(R.id.til_password))),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("Password")))

        val editText3 = onView(
            allOf(
                withId(R.id.et_password), withText("Password"),
                withParent(withParent(withId(R.id.til_password))),
                isDisplayed()
            )
        )
        editText3.check(matches(isDisplayed()))
    }
}

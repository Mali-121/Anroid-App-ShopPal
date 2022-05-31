package com.myshoppal.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.myshoppal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest4 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest4() {
        val mSPEditText = onView(
            allOf(
                withId(R.id.et_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_email),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        mSPEditText.perform(replaceText("mytestuser@gmail.com"), closeSoftKeyboard())

        val mSPEditText2 = onView(
            allOf(
                withId(R.id.et_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.til_password),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        mSPEditText2.perform(replaceText("123456"), closeSoftKeyboard())

        val editText = onView(
            allOf(
                withId(R.id.et_email), withText("mytestuser@gmail.com"),
                withParent(withParent(withId(R.id.til_email))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("mytestuser@gmail.com")))

        val editText2 = onView(
            allOf(
                withId(R.id.et_password), withText("123456"),
                withParent(withParent(withId(R.id.til_password))),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("123456")))

        val mSPButton = onView(
            allOf(
                withId(R.id.btn_login), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        mSPButton.perform(click())

        val frameLayout = onView(
            allOf(
                withId(R.id.navigation_sold_products), withContentDescription("Sold Products"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout.check(matches(isEnabled()))

        val frameLayout2 = onView(
            allOf(
                withId(R.id.navigation_sold_products), withContentDescription("Sold Products"),
                withParent(withParent(withId(R.id.nav_view))),
                isDisplayed()
            )
        )
        frameLayout2.check(matches(isDisplayed()))

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_settings), withContentDescription("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar_settings_activity),
                        childAtPosition(
                            withId(R.id.fl_header_bg),
                            1
                        )
                    ),
                    2
                )
            )
        )
        appCompatImageButton.perform(scrollTo(), click())

        pressBack()

        val recyclerView = onView(
            allOf(
                withId(R.id.rv_dashboard_items),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

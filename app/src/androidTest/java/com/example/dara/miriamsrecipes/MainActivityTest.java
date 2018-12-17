package com.example.dara.miriamsrecipes;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.dara.miriamsrecipes.ui.list.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String RECIPE_NAME = "Nutella Pie";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void clickOnRecyclerViewItem_StartDetailActivity() {
        // Click on the RecyclerView item at position 0
        onView(withId(R.id.rv_recipe_list)).perform(actionOnItemAtPosition(0, click()));

        // Checks that the OrderActivity opens with the correct tea name displayed
        onView(withText(RECIPE_NAME)).check(matches(isDisplayed()));

    }

}

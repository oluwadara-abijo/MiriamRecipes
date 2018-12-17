package com.example.dara.miriamsrecipes;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.dara.miriamsrecipes.ui.list.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityIntentTest {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void clickOnRecipe_launchRecipeDetailAActivity () {
        //Perform click on recycler view item at position 1
        onView(withId(R.id.rv_recipe_list)).perform(actionOnItemAtPosition(1, click()));

        //Verify that an intent to the RecipeDetailActivity was sent with the correct extra
        Intents.intended(Matchers.is(IntentMatchers.hasExtra("Recipe Name", "Brownies")));

    }
}

package com.example.api;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.api.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAnnecySearch() {

        onView(withId(R.id.editTextNameCity))
                .perform(typeText("Annecy"), closeSoftKeyboard());

        onView(withId(R.id.imageViewSearch))
                .perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.editTextNameCity))
                .check(matches(withText(containsString("Annecy"))));

        onView(withId(R.id.textViewTemperature))
                .check(matches(isDisplayed()));

        onView(withId(R.id.textViewMin))
                .check(matches(isDisplayed()));

        onView(withId(R.id.textViewMax))
                .check(matches(isDisplayed()));

        onView(withId(R.id.activityRecyclerView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.activityRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.activityIcon))
                .check(matches(isDisplayed()));

        onView(withId(R.id.textViewPlaceName))
                .check(matches(isDisplayed()));

        onView(withId(R.id.textViewPlaceDescription))
                .check(matches(isDisplayed()));

        onView(withId(R.id.buttonOpenMaps))
                .check(matches(isDisplayed()));

        onView(withId(R.id.toolbar))
                .perform(click());
    }
}
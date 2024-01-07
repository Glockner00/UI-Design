package com.example.projekt_cards;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static com.example.projekt_cards.MainActivity.customCardView1;
import static com.example.projekt_cards.MainActivity.customCardView2;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UiTesting {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.projekt_cards", appContext.getPackageName());

        //Här ska testlogiken mha av Espresso in
        //Espresso.onView(ViewMatchers.withId(R.id.container)).perform(ViewActions.click());
        //Lägg till fler test om så behövs
    }

    @Test
    public void testClickOnCard1() {
        onView(ViewMatchers.withId(customCardView1.getId())).perform(ViewActions.click());
        // Add assertions or verifications as needed
    }

    @Test
    public void testClickOnCard2() {
        onView(ViewMatchers.withId(customCardView2.getId())).perform(ViewActions.click());
        // Add assertions or verifications as needed
    }

}
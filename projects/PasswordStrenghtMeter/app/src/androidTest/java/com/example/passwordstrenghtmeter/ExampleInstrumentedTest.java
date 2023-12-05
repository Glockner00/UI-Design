package com.example.passwordstrenghtmeter;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.content.Context;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Instrumented test class for testing the password strength meter.
 *
 * This class contains a set of tests to ensure the proper functionality of the password strength meter
 * in the context of an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /**
     * Ensures that we are running main activity.
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Verify that the application's context is correct.
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.passwordstrenghtmeter", appContext.getPackageName());
    }

    /**
     * Test for a weak password.
     *
     * This test enters a weak password and checks if the strength bar is displayed and if the
     * password strength is correctly identified as "Weak".
     */
    @Test
    public void weakPasswordTest() {
        onView(withId(Password.passwordId)).perform(typeText("password"));
        onView(withId(StrengthBar.strengthBarId)).check(matches(isDisplayed()));
        assertEquals("Weak", getPasswordStrength());
    }

    /**
     * Test for a medium-strength password.
     *
     * This test enters a medium-strength password and checks if the strength bar is displayed and
     * if the password strength is correctly identified as "Medium".
     */
    @Test
    public void mediumPasswordTest() {
        onView(withId(Password.passwordId)).perform(typeText("Password"));
        onView(withId(StrengthBar.strengthBarId)).check(matches(isDisplayed()));
        assertEquals("Medium", getPasswordStrength());
    }

    /**
     * Test for a strong password.
     *
     * This test enters a strong password and checks if the strength bar is displayed and if the
     * password strength is correctly identified as "Strong".
     */
    @Test
    public void strongPasswordTest() {
        onView(withId(Password.passwordId)).perform(typeText("Password@"));
        onView(withId(StrengthBar.strengthBarId)).check(matches(isDisplayed()));
        assertEquals("Strong", getPasswordStrength());
    }

    /**
     * Helper method to get the password strength.
     *
     * This method retrieves the password strength from the StrengthBar and returns it as a string.
     *
     * @return The password strength ("Weak", "Medium", or "Strong").
     */
    private String getPasswordStrength() {
        final String[] passwordStrength = new String[1];
        activityRule.getScenario().onActivity(activity -> {
            StrengthBar strengthBar = activity.findViewById(StrengthBar.strengthBarId);
            passwordStrength[0] = strengthBar.getPasswordStrenght();
        });
        return passwordStrength[0];
    }
}

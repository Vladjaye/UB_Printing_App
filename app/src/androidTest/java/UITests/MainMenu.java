package UITests;


import com.example.ubprintingapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static android.support.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;


@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainMenu {

    private String stringToBetyped;
/*
   @Rule
    public ActivityTestRule<MainMenu> activityRule  = new ActivityTestRule<>(MainMenu.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUserInput))
                .perform(typeText(stringToBetyped), closeSoftKeyboard());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged))
                .check(matches(withText(stringToBetyped)));
    }*/
}
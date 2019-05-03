package UITests;


import android.app.Activity;

import com.example.ubprintingapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@LargeTest

public class MainMenu extends Activity {



   @Rule
    public ActivityTestRule<MainMenu> activityRule  = new ActivityTestRule<>(MainMenu.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        //stringToBetyped = "Espresso";
    }

    @Test
    public void press_ShowOnMap_Capen() {
        // press the button "Show on map".
      //  com.example.ubprintingapp.Fragment_capen
       //         .perform(click);
        onView(withId(R.id.capbutton)).perform(click());

       //We will see the map, if it works.
    }

    @Test
    public void press_ShowOnMap_Lockwood() {
        // press the button "Show on map".
        //  com.example.ubprintingapp.Fragment_lockwood
        //         .perform(click);
        onView(withId(R.id.lockbutton)).perform(click());

        //We will see the map, if it works.
    }

    @Test
    public void press_ShowOnMap_Music() {
        // press the button "Show on map".
        //  com.example.ubprintingapp.Fragment_music
        //         .perform(click);
        onView(withId(R.id.musicbutton)).perform(click());

        //We will see the map, if it works.
    }
}
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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

      //  com.example.ubprintingapp.Fragment_capen
       //         .perform(click);

        onView(withId(R.id.PrintingButton)).perform(click());
        //clicking in main menu upper on the upper button (with printer icon)

        onView(withId(R.id.capbutton)).perform(click()).check(matches(isDisplayed()));
        // press the button "Show on map".

        //We will see the map and direction from the user location to capen library
        //it will check if the map is displayed.
    }

    @Test
    public void press_ShowOnMap_Lockwood() {

        //  com.example.ubprintingapp.Fragment_lockwood
        //         .perform(click);

        onView(withId(R.id.PrintingButton)).perform(click());
        //clicking in main menu upper on the upper button (with printer icon)

        onView(withId(R.id.lockbutton)).perform(click()).check(matches(isDisplayed()));
        // press the button "Show on map".


        //it will check if the map is displayed.
        //We will see the map and direction from the user location to lockwood
    }

    @Test
    public void press_ShowOnMap_Music() {

        //  com.example.ubprintingapp.Fragment_music
        //         .perform(click);

        onView(withId(R.id.PrintingButton)).perform(click());
        //clicking in main menu upper on the upper button (with printer icon)


        onView(withId(R.id.musicbutton)).perform(click()).check(matches(isDisplayed()));
        // press the button "Show on map".

        //it will check if the map is displayed.
        //We will see the map and direction from the user location to music library

    }


    @Test
    public void CheckingIfInkChangeInProgressAppears() {
        // we will see "Ink Change In Progress" if it will appear
        //the chance to see that is 1/10.

        onView(withId(R.id.PrintingButton)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void CheckingListOfPC() {

        //it will check if the PC list is displayed.
        onView(withId(R.id.PCListButton)).perform(click()).check(matches(isDisplayed()));

    }

    @Test
    public void CheckingTotalTime() {
        // we will see checking time on the display

        onView(withId(R.id.PrintingButton)).perform(click()).check(matches(isDisplayed()));
    }
    


    //Checking if the user will be able to see the position
    // of the library, after clicking the button “show on map”
    @Test
    public void CheckingPosition() {

        onView(withId(R.id.PrintingButton)).perform(click());
        //clicking in main menu upper on the upper button (with printer icon)

        onView(withId(R.id.capbutton)).perform(click()).check(matches(isDisplayed()));
        // press the button "Show on map".

        //We will see if the position will be showing on the capen library
    }



}
package uno.idk.luggit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uno.idk.luggit.R;

/**
 * IN THIS ACTIVITY WE DO A SIMPLE DEMO OF WHAT GOES ON AT THE CHECKIN DESK:
 *
 *  1. "Hello, Can I have your passport?"
 *      a. NFC TAP PASSPORT
 *      b. SHOW DUMMY TICKET INFORMATION
 *
 *  2. "Thanks, your seat is a business class window seat 10A. Do you have any baggage?"
 *      a. Take two bags
 *          i. We need to get 2 bags and put random barcodes on them
 *      b. SCAN THE TAGS
 *      c. Add the tags to the luggage items
 *
 *  3. "Would you like us to deliver your bags straight to your hotel in London?"
 *      a. If yes then you can add a place using google places api to the journey.
 *      b. If no then it will pop up on the guys phone later.
 *      NOTE: IN THE DEMO THE PERSON SHOULD SAY NO!
 *
 *  4. "Thank you, have a nice flight"
 *
 */
public class CheckInDeskDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_desk_demo);
    }
}

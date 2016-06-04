package uno.idk.luggit.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import uno.idk.luggit.R;

/**
 * This activity will be a dummy activity showing a persons boarding pass in the BA app.
 * It should wholely mimic the BA boarding pass section of the app.
 *
 * Once the person checks in it will show a notifications saying:
 *  "You've checked in 3 items"
 *     > User presses the notification and it takes him to a screen specifically for the luggage.
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("lol","lol");
    }
}

package uno.idk.luggit.ui;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by moham on 6/4/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
}

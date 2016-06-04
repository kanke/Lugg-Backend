package uno.idk.luggit.api;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by moham on 6/4/2016.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
private static final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        Log.d(TAG, "onTokenRefresh: ");
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        //register the token
    }
}

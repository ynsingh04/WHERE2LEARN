package co.in.where2learn_new.social;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.facebook.FacebookSdk;

import co.in.where2learn_new.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AndroidFacebookActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook);
    }
}

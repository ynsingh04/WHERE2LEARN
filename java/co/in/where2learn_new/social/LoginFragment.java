package co.in.where2learn_new.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FooterBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.footer.LoginActivity;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.util.CheckInternet;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class LoginFragment extends Fragment {

    private CallbackManager callbackManager = null;
    private AccessTokenTracker mtracker = null;
    private ProfileTracker mprofileTracker = null;

    public static final String PARCEL_KEY = "parcel_key";

    private LoginButton loginButton;

    private DataBaseClass dataBaseClass;

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            Profile profile = Profile.getCurrentProfile();
            homeFragment(profile);

            /******************Not used*********************/
            String accessToken = loginResult.getAccessToken().getToken();
            Log.i("accessToken", accessToken);

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.i("onCompleted", response.toString());
                    // Get facebook data from login
                    Bundle bFacebookData = getFacebookData(object);

                    Log.e(TAG,"Email: " + object.toString());
                    setUserModel(object);
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
            request.setParameters(parameters);
            request.executeAsync();
            /*********************************************/

//            setUserModel(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    private void setUserModel(JSONObject object) {
        if(object==null) {
            Log.e(TAG,"object is null");
            return;
        }

        try{
            String UserID = object.getString("id");
            String UserName = object.getString("first_name") + " " + object.getString("last_name");

            Log.e(TAG, "Name: " + UserName + ", id: " + UserID);

            MainActivity.userIdentificaton.setText(UserName.toUpperCase());

            UserModel model = new UserModel();
            model.setEmailString(UserID);
            model.setNameString(UserName);
            model.setPhoneString("");

            AppConfig.UserModel = model;

            updateUI(true);
        }
        catch(JSONException e){
            Log.e(TAG,"JSONException:\n" + e.toString());
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
//			activity.finish();

            FooterBusinessLogic footerBusinessLogic = new FooterBusinessLogic(MainActivity.ref);
            footerBusinessLogic.invokeRegistrationLogic(AppConfig.UserModel);

            dataBaseClass = new DataBaseClass(MainActivity.ref);
            dataBaseClass.createUserDetails(AppConfig.UserModel);

            ((TextView)MainActivity.ref.findViewById(R.id.login)).requestFocus();
            ((TextView)MainActivity.ref.findViewById(R.id.login)).setText(MainActivity.ref.getString(R.string.logout));

//			MainActivity.ref.finish();
//			Intent intent = new Intent(activity, MainActivity.class);
//			activity.startActivity(intent);

            Toast.makeText(
                    MainActivity.ref,
                    MainActivity.ref.getString(R.string.login_successfully),
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.ref,
                    MainActivity.ref.getString(R.string.logout_successfully),
                    Toast.LENGTH_SHORT).show();
        }

        getActivity().finish();
        LoginActivity.ref.finish();
//        MainActivity.ref.finish();
//			Intent intent = new Intent(MainActivity.ref, MainActivity.class);
//        MainActivity.ref.startActivity(intent);
    }

    /**************************Not used*******************************/
    private Bundle getFacebookData(JSONObject object) {

        Bundle bundle = new Bundle();
        try {

            String id = object.getString("id");

            /*try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }*/

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

        }
        catch(JSONException e) {
            Log.d(TAG,"Error parsing JSON");
        }

        return bundle;
    }
    /*********************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();


        mtracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                Log.v("AccessTokenTracker", "oldAccessToken=" + oldAccessToken + "||" + "CurrentAccessToken" + currentAccessToken);
            }
        };


        mprofileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                Log.v("Session Tracker", "oldProfile=" + oldProfile + "||" + "currentProfile" + currentProfile);
                homeFragment(currentProfile);

            }
        };

        mtracker.startTracking();
        mprofileTracker.startTracking();
    }


    private void homeFragment(Profile profile) {

        if (profile != null) {
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(PARCEL_KEY, profile);
            HomeFragment hf = new HomeFragment();
            hf.setArguments(mBundle);

//            hf.getActivity().finish();

////            FragmentManager fragmentManager = MainActivity.ref.getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager
//                    .beginTransaction();
//            fragmentTransaction.replace(R.id.mainContainer, new HomeFragment());
//            fragmentTransaction.commit();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
        loginButton.setFragment(this);

//        if(CheckInternet.getConnectivityStatus(MainActivity.ref)!=CheckInternet.TYPE_NOT_CONNECTED)
        loginButton.registerCallback(callbackManager, callback);
//        else
//            Toast.makeText(MainActivity.ref,
//                    MainActivity.ref.getString(R.string.no_internet_found),
//                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        mtracker.stopTracking();
        mprofileTracker.stopTracking();
    }


    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null)
        AppConfig.ID = accessToken.getUserId();
        return accessToken != null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isLoggedIn()) {
            loginButton.setVisibility(View.INVISIBLE);
            Profile profile = Profile.getCurrentProfile();
            homeFragment(profile);
        }

    }
}

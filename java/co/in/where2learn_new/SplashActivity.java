package co.in.where2learn_new;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.VideoView;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.util.SuggestionDialog;

public class SplashActivity extends Activity {

	private Activity activity;

	private VideoView imgAnim;
	
	private FrameLayout parentLayout;
	
	private ImageView logo;
	private ImageView centerImage;
	
	private TableRow msgText;

	private DataBaseClass dataBaseClass;
	
	private View.OnClickListener startBtnOnClickListener;

	/* For Permissions Management */
	final int MY_PERMISSIONS_REQUEST_CALL_PHONE=0;
	final int MY_PERMISSIONS_REQUEST_GET_ACCOUNTS=1;
	final String[] permissions = new String[]{Manifest.permission.CALL_PHONE,
			Manifest.permission.GET_ACCOUNTS};
	int count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		activity = this;

		getActionBar().hide();
		
		allowPermission();

	}

	/* Call Initialize Methods */
	private void callInitMethods() {
		initListener();

		initComponent();

		initSplashVideo();
	}


	private void initSplashVideo() {

		String path = "android.resource://" + getPackageName() + "/"
				+ R.raw.splash_video;

		imgAnim.setVideoURI(Uri.parse(path));
		imgAnim.requestFocus();
		imgAnim.start();
				
		
		/*int SPLASH_DISPLAY_LENGTH = 5000;
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

//				splashBG.setVisibility(View.VISIBLE);
				startBtn.setVisibility(View.VISIBLE);
				
				Intent mainIntent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(mainIntent);

				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);*/
		
		imgAnim.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
//				((ImageView)findViewById(R.id.splash_imageView_logoOnLoad)).setVisibility(View.INVISIBLE);
			}
		});
		
		imgAnim.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
//				imgAnim.setAlpha(.8f);
//				startBtn.setVisibility(View.VISIBLE);
//				parentLayout.setAlpha(0.5f);
//				splashBG.setVisibility(View.VISIBLE);
				
				msgText
				.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.splash_screen_zoom_animation));
				msgText.setVisibility(View.VISIBLE);				

				logo.setVisibility(View.VISIBLE);
				logo
				.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_bottom));
				
//				parentLayout.setEnabled(true);
			}
		});
		
		imgAnim.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				imgAnim.setVisibility(View.GONE);
				
				centerImage
				.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_bottom));
				
				msgText
				.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.splash_screen_zoom_animation));
				msgText.setVisibility(View.VISIBLE);
				
				AppConfig.UserModel = dataBaseClass.getUserModel();
				
				return false;
			}
		});

		AppConfig.UserModel = dataBaseClass.getUserModel();

	}

	private void initComponent() {

		dataBaseClass = new DataBaseClass(activity);

		imgAnim = (VideoView) findViewById(R.id.splash_videoVideoView);
		
		parentLayout = (FrameLayout)findViewById(R.id.splash_rl_parent);
		
		logo = (ImageView)findViewById(R.id.splash_imageView_logo);
		
		centerImage = (ImageView)findViewById(R.id.splash_iv_centerImage);
		
		msgText = (TableRow)findViewById(R.id.splash_tv_msg);
		
		parentLayout.setOnClickListener(startBtnOnClickListener);
		
//		parentLayout.setEnabled(false);

	}
	
	public void initListener(){
		startBtnOnClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imgAnim.stopPlayback();
				Intent mainIntent = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(mainIntent);

				finish();
			}
		};
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		onBackPressed();
	}

	private void allowPermission() {
		if (ContextCompat.checkSelfPermission(activity,
				permissions[count])
				!= PackageManager.PERMISSION_GRANTED){

			// Should we show an explanation?
			/*if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
					permissions[count])) {
				Log.e(TAG,"2");
				// Show an expanation to the user *asynchronously* -- don't block
				// this thread waiting for the user's response! After the user
				// sees the explanation, try again to request the permission.

			} else {*/
			// No explanation needed, we can request the permission.

			ActivityCompat.requestPermissions(activity,
					new String[]{permissions[count]},
					count);

			// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
			// app-defined int constant. The callback method gets the
			// result of the request.
//			}
		}
		else {
			if((ContextCompat.checkSelfPermission(activity,
					Manifest.permission.CALL_PHONE)
					==PackageManager.PERMISSION_GRANTED) &&
					(ContextCompat.checkSelfPermission(activity,
							Manifest.permission.GET_ACCOUNTS)
							==PackageManager.PERMISSION_GRANTED)) {
				callInitMethods();
			}
			else {
				count++;
				allowPermission();
			}
		}
	}


	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		if(count<this.permissions.length)
		{
			switch (requestCode) {
				case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
							&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//						Toast.makeText(this, "CALL_PHONE Permission Granted", Toast.LENGTH_LONG).show();
						count ++;
						allowPermission();
						// permission was granted, yay! Do the
						// contacts-related task you need to do.
					} else
						(new SuggestionDialog(activity)).initAlertDialogForFinish("Please grant all permissions for the app to work properly.");
//						Toast.makeText(this, "CALL_PHONE Permission not granted", Toast.LENGTH_LONG).show();


					return;
				}

				// other 'case' lines to check for other
				// permissions this app might request
				case MY_PERMISSIONS_REQUEST_GET_ACCOUNTS: {
					// If request is cancelled, the result arrays are empty.
					if (grantResults.length > 0
							&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//						Toast.makeText(this, "GET_ACCOUNTS Permission Granted", Toast.LENGTH_LONG).show();
						allowPermission();
						// permission was granted, yay! Do the
						// contacts-related task you need to do.
					} else
						(new SuggestionDialog(activity)).initAlertDialogForFinish("Please grant all permissions for the app to work properly.");
//						Toast.makeText(this, "GET_ACCOUNTS Permission not granted", Toast.LENGTH_LONG).show();


					return;
				}

			}
		}
		else{
			return;
		}

	}
}

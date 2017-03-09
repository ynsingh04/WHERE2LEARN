package co.in.where2learn_new.footer;

import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FooterBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.social.AndroidFacebookActivity;
import co.in.where2learn_new.social.GooglePlusIntegration;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.CheckInternet;
import co.in.where2learn_new.webservice.ForgotPasswordWebServiceCall;

public class LoginActivity extends Activity {

	private Activity activity;
	public static Activity ref;

	private TextView signUpTextView;
	private TextView passwordHint;
	
	private Button forgot_password;
	
	private ImageView back;

	private FooterBusinessLogic footerBusinessLogic;

	private View.OnClickListener signUpOnClickListener;
	private View.OnClickListener submitOnClickListener;
	private View.OnClickListener facebookOnClickListener;
	private View.OnClickListener googlePlusOnClickListener;
	private View.OnClickListener forgotPasswordOnClickListener;
	private View.OnClickListener backOnClickListener;

	private EditText emailEditText;
	private EditText passwordEditText;

	private ImageView submitImageView;
	private ImageView facebookImageView;
	private ImageView googlePlusImageView;

	private UserModel userModel;
	
private String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//"[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	
//	private String password_pattern = "^[a-zA-Z0-9]*$";
private String password_pattern = "^[a-zA-Z0-9@#$*_-]*$";
	
	private String name_pattern = "^[\\p{L} .'-]+$";
	
	public static Dialog mDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		activity = this;
		ref = activity;

		getActionBar().hide();
		changeFooter();

		initListener();
		initComponent();

	}

	private void initListener() {

		signUpOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				footerBusinessLogic.invokeSignUp();

			}
		};

		submitOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				initModel();

				boolean isDataValid = isDataValid();

				if(CheckInternet.getConnectivityStatus(activity)==CheckInternet.TYPE_NOT_CONNECTED){
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
				}
				else if (!isDataValid) {
					
					Toast.makeText(activity,
							activity.getString(R.string.enter_email_id),
							Toast.LENGTH_LONG).show();

//					footerBusinessLogic.invokeLoginValidation(userModel);
				}
				/*else if((!userModel.getEmailString().matches(email_pattern)) &&
						(userModel.getEmailString().length()>10 || userModel.getEmailString().length()<10))
				{
					Toast.makeText(activity,
							activity.getString(R.string.invalid_email_id_or_phone),
							Toast.LENGTH_LONG).show();
				}*/
				else if(userModel.getPasswordString().contains(" ") || !userModel.getPasswordString().matches(password_pattern))
				{
					Toast.makeText(activity,
							activity.getString(R.string.invalid_password),
							Toast.LENGTH_LONG).show();
				}
				else
				{
					footerBusinessLogic.invokeLoginValidation(userModel);
				}

			}
		};
		
		facebookOnClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppConfig.UserModel!=null) {
					Toast.makeText(activity,
							activity.getString(R.string.already_login),
							Toast.LENGTH_LONG).show();
					return;
				}

				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED) {
					startActivity(new Intent(activity, AndroidFacebookActivity.class));
				}
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();

//				Toast.makeText(activity, activity.getString(R.string.coming_soon), Toast.LENGTH_LONG).show();
			}
		};
		
		googlePlusOnClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(AppConfig.UserModel!=null) {
					Toast.makeText(activity,
							activity.getString(R.string.already_login),
							Toast.LENGTH_LONG).show();
					return;
				}

				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED) {
					MainActivity.mainActivity.signIn();
					finish();
				}
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();

//				Toast.makeText(activity, activity.getString(R.string.coming_soon), Toast.LENGTH_LONG).show();
			}
		};
		
		forgotPasswordOnClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialogForgotPassword();
			}
		};
		
		backOnClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		};

	}

	protected void initModel() {

		emailEditText.setText(emailEditText.getText().toString().trim().toLowerCase());
		passwordEditText.setText(passwordEditText.getText().toString().trim());
		
		userModel.setEmailString(emailEditText.getText().toString());
		userModel.setPasswordString(passwordEditText.getText().toString());

	}

	protected boolean isDataValid() {

		return userModel.isLoginDataValid();
	}

	private void initComponent() {

		footerBusinessLogic = new FooterBusinessLogic(activity);
		userModel = new UserModel();

		signUpTextView = (TextView) findViewById(R.id.activity_login_signUpTextView);
		passwordHint = (TextView) findViewById(R.id.login_tv_passwordHint);

		emailEditText = (EditText) findViewById(R.id.activity_login_emailEditText);
		passwordEditText = (EditText) findViewById(R.id.activity_login_passwordEditText);
		
		forgot_password = (Button) findViewById(R.id.login_btn_forgotPassword);
		
		back = (ImageView) findViewById(R.id.login_iv_back);

		submitImageView = (ImageView) findViewById(R.id.activity_login_submitImageView);
		
		facebookImageView = (ImageView) findViewById(R.id.login_iv_facebook);
		googlePlusImageView = (ImageView) findViewById(R.id.login_iv_googlePlus);

		signUpTextView.setOnClickListener(signUpOnClickListener);
		submitImageView.setOnClickListener(submitOnClickListener);
		facebookImageView.setOnClickListener(facebookOnClickListener);
		googlePlusImageView.setOnClickListener(googlePlusOnClickListener);
		forgot_password.setOnClickListener(forgotPasswordOnClickListener);
		back.setOnClickListener(backOnClickListener);
		
		passwordHint.setText("Special characters allowed are @, #, $, *, _, -");

	}
	
	/** Forgot Password Dialog */
	public void showDialogForgotPassword(){
		Dialog mDialog = new Dialog(activity);
    	mDialog.setContentView(R.layout.dialog_forgot_password);
    	mDialog.setTitle(activity.getString(R.string.forgot_password));
    	
    	final EditText email = (EditText)mDialog.findViewById(R.id.forgotPassword_et_email);
    	Button send = (Button)mDialog.findViewById(R.id.forgotPassword_btn_submit);
    	
    	mDialog.show();
    	LoginActivity.mDialog = mDialog;
    	
    	send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email_text = email.getText().toString().trim();
				
				String validationsCheck = validationsCheck(email_text);
				
				if(validationsCheck.equalsIgnoreCase("Success"))
		    	{
					ForgotPasswordWebServiceCall sendEmail = new ForgotPasswordWebServiceCall();
		        	try {
//		    			writeToUsServiceCall.getDetails(activity, name_text, email_text, phone_text, message_text);
		        		sendEmail.sendEmail(activity, email_text);
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    	}
			}
		});    	
	}
	
	public String validationsCheck(String email_text)
    {
    	String validationsCheck = "";
    	boolean checkMessage = false;
    	
    	try{
    		checkMessage = email_text.matches(email_pattern);
    	} catch(NullPointerException e){
    		e.printStackTrace();
    	} catch(PatternSyntaxException e){
    		e.printStackTrace();
    	}
    	
    	if(CheckInternet.getConnectivityStatus(activity)==CheckInternet.TYPE_NOT_CONNECTED){
			Toast.makeText(activity,
					activity.getString(R.string.no_internet_found),
					Toast.LENGTH_LONG).show();
		}
    	else if(email_text.equalsIgnoreCase(""))
    	{
    		Toast.makeText(activity, activity.getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
    	}
    	else if(!checkMessage)
    	{
    		Toast.makeText(activity,
					activity.getString(R.string.invalid_email_id),
					Toast.LENGTH_LONG).show();
    	}
    	else {
    		validationsCheck = "Success";
    	}
    	
    	return validationsCheck;
    }

	/**
	 * This method is called to change footer bar
	 */
	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
}

package co.in.where2learn_new.footer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FooterBusinessLogic;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.CheckInternet;

public class SignUpActivity extends Activity {

	private Activity activity;

	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText confirmPasswordEditText;
	private EditText phoneEditText;
	private EditText nameEditText;

	private TextView okTextView;
	private TextView passwordHint;
	
	private ImageView back;

	private View.OnClickListener okOnClickListener;
	private View.OnClickListener backOnClickListener;

	private UserModel model;

	private FooterBusinessLogic footerBusinessLogic;
	
	private String email_pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//	"[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
// Another alternate soln	[a-zA-Z0-9._-]+@[a-z]+[\\.+[a-z]+]+[\\w]$
	
//	private String password_pattern = "^[a-zA-Z0-9]*$";
	private String password_pattern = "^[a-zA-Z0-9@#$*_-]*$";
	
	private String name_pattern = "^[\\p{L} .'-]+$";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		activity = this;

		getActionBar().hide();
		changeFooter();

		initListener();
		initComponent();

	}

	private void initListener() {

		okOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				initModelWithEditTextValue();

				boolean isPasswordAndConfirmMatchAndValidData = validateData();
				
//				Matcher matcherObj = Pattern.compile(email_pattern).matcher(model.getEmailString());

				if(CheckInternet.getConnectivityStatus(activity)==CheckInternet.TYPE_NOT_CONNECTED){
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
				}
				else if (!isPasswordAndConfirmMatchAndValidData) {
					
					Toast.makeText(activity,
							activity.getString(R.string.fill_all_fields),
							Toast.LENGTH_LONG).show();
					
//					footerBusinessLogic.invokeRegistrationLogic(model);
				}
				else if(!model.getNameString().matches(name_pattern)) {
					
					Toast.makeText(activity,
							activity.getString(R.string.invalid_name),
							Toast.LENGTH_LONG).show();
				}
				else if((model.getEmailString().length()==0) && (model.getPhoneString().length()==0)) {
					Toast.makeText(activity,
							activity.getString(R.string.enter_phone_or_email),
							Toast.LENGTH_LONG).show();
				}
				else if((!model.getEmailString().matches(email_pattern)) && (model.getEmailString().length()!=0)) {
					
					Toast.makeText(activity,
							activity.getString(R.string.invalid_email_id),
							Toast.LENGTH_LONG).show();
				}
				else if((model.getPhoneString().length()<10) && (model.getPhoneString().length()!=0)) {
					
					Toast.makeText(activity,
							activity.getString(R.string.invalid_phone_no),
							Toast.LENGTH_LONG).show();
				}
				else if(model.getPasswordString().contains(" ") || !model.getPasswordString().matches(password_pattern)) {
					
//					passwordEditText.setError("Special characters allowed are @, #, $, *, _, -");
					
					Toast.makeText(activity,
							activity.getString(R.string.invalid_password),
							Toast.LENGTH_LONG).show();
				}
				else {
					isPasswordAndConfirmMatchAndValidData = matchPasswordAndConfirm();
					
					if(!isPasswordAndConfirmMatchAndValidData){
						Toast.makeText(activity,
								activity.getString(R.string.passwords_not_matching),
								Toast.LENGTH_LONG).show();
					}
					else{
						footerBusinessLogic.invokeRegistrationLogic(model);
					}
				}
				
				isPasswordAndConfirmMatchAndValidData = matchPasswordAndConfirm();

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

	protected void initModelWithEditTextValue() {
		
		emailEditText.setText(emailEditText.getText().toString().trim().toLowerCase());
		passwordEditText.setText(passwordEditText.getText().toString().trim());
		confirmPasswordEditText.setText(confirmPasswordEditText.getText().toString().trim());
		phoneEditText.setText(phoneEditText.getText().toString().trim());
		nameEditText.setText(nameEditText.getText().toString().trim());

		model.setEmailString(emailEditText.getText().toString());
		model.setPasswordString(passwordEditText.getText().toString());
		model.setConfirmPasswordString(confirmPasswordEditText.getText()
				.toString());
		model.setPhoneString(phoneEditText.getText().toString());
		model.setNameString(nameEditText.getText().toString());

	}

	protected boolean matchPasswordAndConfirm() {

		return model.isPasswordAndConfirmMatch();

	}

	protected boolean validateData() {

		return model.isValid();

	}

	private void initComponent() {

		footerBusinessLogic = new FooterBusinessLogic(activity);
		model = new UserModel();

		emailEditText = (EditText) findViewById(R.id.activity_sign_up_emailEditText);
		passwordEditText = (EditText) findViewById(R.id.activity_sign_up_passwordEditText);
		confirmPasswordEditText = (EditText) findViewById(R.id.activity_sign_up_confirmPasswordEditText);
		phoneEditText = (EditText) findViewById(R.id.activity_sign_up_phoneEditText);
		nameEditText = (EditText) findViewById(R.id.activity_sign_up_nameEditText);
		passwordHint = (TextView) findViewById(R.id.signUp_tv_passwordHint);

		okTextView = (TextView) findViewById(R.id.activity_sign_up_okTextView);
		
		back = (ImageView) findViewById(R.id.signup_iv_back);

		okTextView.setOnClickListener(okOnClickListener);
		back.setOnClickListener(backOnClickListener);
		
		passwordHint.setText("Special characters allowed are @, #, $, *, _, -");

	}

	/**
	 * This method is called to change footer bar
	 */
	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
}

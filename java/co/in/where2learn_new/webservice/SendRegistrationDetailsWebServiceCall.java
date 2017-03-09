package co.in.where2learn_new.webservice;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.footer.LoginActivity;
import co.in.where2learn_new.model.UserModel;

public class SendRegistrationDetailsWebServiceCall {

	private String TAG = this.getClass().getName();

	private UserModel model;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	private String Result;

	private DataBaseClass dataBaseClass;

	public SendRegistrationDetailsWebServiceCall(UserModel model) {
		this.model = model;

	}

	public void sendRegistrationDetails(Activity activityParam)
			throws Exception {

		this.activity = activityParam;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Sending Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					callWebService();

				} catch (Exception e) {
					Log.e(TAG, e.toString());
					isError = true;
				}
				ringProgressDialog.dismiss();

			}
		}).start();

		ringProgressDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {

				if (isError) {

					Toast.makeText(
							activity,
							activity.getString(R.string.error_in_data_initialization),
							Toast.LENGTH_LONG).show();
				} else {

					if (Result.equalsIgnoreCase("Success")) {

						AppConfig.UserModel = model;
						if(model.getEmailString()==null || model.getEmailString().equalsIgnoreCase("")) {
							AppConfig.UserModel.setUserID(model.getPhoneString());
							model.setUserID(model.getPhoneString());
						}
						else {
							AppConfig.UserModel.setUserID(model.getEmailString());
							model.setUserID(model.getEmailString());
						}

						dataBaseClass = new DataBaseClass(activity);
						dataBaseClass.createUserDetails(model);

						/** Success Dialog */
						showDialogForSuccess();
						
						Toast.makeText(
								activity,
								activity.getString(R.string.login_successfully),
								Toast.LENGTH_LONG).show();

						
					} 
					else if(Result.equalsIgnoreCase("User name already exists")) {
						if(model.getEmailString().length()>0 && model.getPhoneString().length()==0
								&& model.getPasswordString()!=null && !model.getPasswordString().equalsIgnoreCase("")) {
							Toast.makeText(
									activity,
									activity.getString(R.string.email_id_already_exists),
									Toast.LENGTH_LONG).show();
						}
						else if(model.getEmailString().length()==0 && model.getPhoneString().length()>0) {
							Toast.makeText(
									activity,
									activity.getString(R.string.phone_no_already_exists),
									Toast.LENGTH_LONG).show();
						}
						else if(model.getEmailString().length()>0 && model.getPhoneString().length()>0) {
							Toast.makeText(
									activity,
									activity.getString(R.string.user_already_exists),
									Toast.LENGTH_LONG).show();
						}
					}
					else {
						Toast.makeText(
								activity,
								activity.getString(R.string.operation_failed),
								Toast.LENGTH_LONG).show();
					}

				}

			}
		});

	}
	
	public void showDialogForSuccess(){
		Dialog mDialog = new Dialog(activity);
    	mDialog.setContentView(R.layout.dialog_about);
    	mDialog.setTitle(activity.getString(R.string.welcome));
    	
    	TextView app_version = (TextView) mDialog.findViewById(R.id.about_tv_versionName);
    	Button ok = (Button) mDialog.findViewById(R.id.about_btn_submit);
    	
    	app_version.setText(AppConfig.UserModel.getEmailString() + " successfully logged in");
    	ok.setVisibility(View.VISIBLE);
    	
    	ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**** Intent to school filter ***/
				activity.finish();
				LoginActivity.ref.finish();
				MainActivity.ref.finish();
				Intent intent = new Intent(activity, MainActivity.class);
				activity.startActivity(intent);
				/**** End Intent to online store ***/
			}
		});
    	
    	mDialog.show();
	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = "";
		if(model.getPasswordString()==null || model.getPasswordString().equalsIgnoreCase("")) {
			URI = AppConfig.URI_USERREGISTRATION + "?UserID="
					+ model.getEmailString() + "&UserName=" + model.getNameString()
					+ "&Password=" + model.getEmailString() + "&MobileNo="
					+ "" + "&Platform=app";
		}
		else {
			URI = AppConfig.URI_USERREGISTRATION + "?UserID="
					+ model.getEmailString() + "&UserName=" + model.getNameString()
					+ "&Password=" + model.getPasswordString() + "&MobileNo="
					+ model.getPhoneString() + "&Platform=app";
		}


		URI = URI.replace(" ", "%20");

		Log.i(TAG, URI);
		Log.e(TAG, URI);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI);

		ResponseHandler<String> handler = new BasicResponseHandler();
		Object result = new Object();

		result = client.execute(httpGet, handler);

		parseAreaXML(result);

	}

	private void parseAreaXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONObject item = new JSONObject(resultString);

		Result = item.getString("Result");

		Log.i(TAG, Result);

	}

}

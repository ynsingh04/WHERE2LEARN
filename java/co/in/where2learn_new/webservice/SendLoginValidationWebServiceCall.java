package co.in.where2learn_new.webservice;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;
import co.in.where2learn_new.model.UserModel;

public class SendLoginValidationWebServiceCall {

	private String TAG = this.getClass().getName();

	private UserModel model;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	private String Result;

	private DataBaseClass dataBaseClass;
	
	private List<NameValuePair> params;

	public SendLoginValidationWebServiceCall(UserModel model) {
		this.model = model;

	}

	public void sendLoginValidation(Activity activityParam) throws Exception {

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
							"Please check user-id and password",
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
						
						((TextView)activity.findViewById(R.id.login)).requestFocus();
						((TextView)activity.findViewById(R.id.login)).setText(activity.getString(R.string.logout));

						
						/**** onBackPressed ***/
						activity.onBackPressed();
						MainActivity.ref.finish();
						Intent intent = new Intent(activity, MainActivity.class);
						activity.startActivity(intent);
						
						Toast.makeText(
								activity,
								activity.getString(R.string.login_successfully),
								Toast.LENGTH_LONG).show();
//						activity.onBackPressed();
//						MainActivity.userIdentificaton.setText(AppConfig.UserModel.getNameString().toUpperCase());
						/**** End onBackPressed ***/
					} else {
						Toast.makeText(
								activity,
										"Please check user-id and password",
								Toast.LENGTH_LONG).show();
					}

				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_GETUSERREGDETAILS + "?UserID="
				+ model.getEmailString() + "&Password="
				+ model.getPasswordString();
		
		/*String URI = AppConfig.URI_GETUSERREGDETAILS + "?UserID="
				+ URLEncoder.encode(model.getEmailString(), "UTF-8") + "&Password="
				+ URLEncoder.encode(model.getPasswordString(), "UTF-8");*/

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

//		JSONArray item = new JSONArray(resultString);
		JSONObject item = new JSONObject(resultString);

		if (item.isNull("UserID") && item.isNull("MobileNo")) {

			Result = resultString;

		} else {
			
			

//			JSONObject jsonObject = item.getJSONObject(0);
			JSONObject jsonObject = item;

			String mobileNo = jsonObject.getString("MobileNo");
			String Password = jsonObject.getString("Password");
			// String Platform = jsonObject.getString("Platform");
			// String SysDateTime = jsonObject.getString("SysDateTime");
			String UserID = jsonObject.getString("UserID");
			String UserName = jsonObject.getString("UserName");

			UserModel model = new UserModel();

			model.setEmailString(UserID);
			model.setNameString(UserName);
			model.setPasswordString(Password);
			model.setPhoneString(mobileNo);
			
			this.model = model;

			Result = "Success";

		}

	}
}

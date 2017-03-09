package co.in.where2learn_new.webservice;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.footer.LoginActivity;

public class ForgotPasswordWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;
	private String email;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;
	private boolean noEmailID = false;
	
	public void sendEmail(Activity activityParam,
			String email) throws Exception {

		this.activity = activityParam;
		this.email = email;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Sending Email ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

						Log.e(TAG, ForgotPasswordWebServiceCall.this.email);
						callWebService(ForgotPasswordWebServiceCall.this.email);
					

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
							activity.getString(R.string.send_failure),
							Toast.LENGTH_LONG).show();
					
				} else if(noEmailID) {
					
					Toast.makeText(
							activity,
							activity.getString(R.string.emailid_not_found),
							Toast.LENGTH_LONG).show();
					
				} else {
					/**** Intent to workshop filter ***/
						LoginActivity.mDialog.dismiss();
					
						sendAlertDialog();
					/*Toast.makeText(
							activity,
							activity.getString(R.string.send_successful),
							Toast.LENGTH_LONG).show();*/
					/**** End Intent to workshop store ***/
				}

			}
		});

	}
	
	/** Alert Dialog in case of failure */
	private void sendAlertDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(activity.getString(R.string.send_successful))
		.setTitle("CONFIRMATION")
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// do things
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService(String email) throws Exception {

		String URI = AppConfig.URI_ForgotPassword +
				"?Email=" + email;

//		URI = URI + type;
		
		Log.i(TAG, URI);
		Log.e(TAG, URI);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI);
//		HttpPost httpGet = new HttpPost(URI);

		ResponseHandler<String> handler = new BasicResponseHandler();
		Object result = new Object();

		result = client.execute(httpGet, handler);
		
		/*String resultString = result.toString();		
		Log.e(TAG, resultString);*/		

//		initArrayList();

		parseSchoolXML(result);

	}
	
	private void parseSchoolXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		/*JSONObject cast = new JSONObject(resultString);

		String Result = "";
		Result = cast.getString("Result");*/
		
		if(!resultString.contains("Successfully send email"))
			noEmailID = true;
		else
			noEmailID = false;
	}
}

package co.in.where2learn_new.webservice;

import java.net.URLEncoder;
import java.util.ArrayList;

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
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.SuggestionDialog;

public class WriteToUsWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;
	private String name;
	private String email;
	private String phone;
	private String message;

	private ArrayList<String> categoryFilter;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private CityListWebServiceCall cityListWebServiceCall;
	
	public void getDetails(Activity activityParam,
			String name,
			String email,
			String phone,
			String message) throws Exception {

		this.activity = activityParam;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.message = message;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Sending Message ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

						Log.e(TAG, WriteToUsWebServiceCall.this.name);
						callWebService(WriteToUsWebServiceCall.this.name,
								WriteToUsWebServiceCall.this.email,
								WriteToUsWebServiceCall.this.phone,
								WriteToUsWebServiceCall.this.message);
					

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
							activity.getString(R.string.submission_failed),
							Toast.LENGTH_LONG).show();
				} else {
					/**** Intent to workshop filter ***/
					if(MainActivity.mDialog!=null)
						MainActivity.mDialog.dismiss();
					else if(SuggestionDialog.mDialog!=null)
						SuggestionDialog.mDialog.dismiss();
					
					Toast.makeText(
							activity,
							activity.getString(R.string.submission_successfull),
							Toast.LENGTH_LONG).show();
					/**** End Intent to workshop store ***/
				}

			}
		});

	}
	
	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService(String name,
			String email,
			String phone,
			String message) throws Exception {

		String URI = AppConfig.URI_WriteToUs +
				"?Name=" + name +
				"&Email=" + email +
				"&Phone=" + phone +
				"&Message=" + URLEncoder.encode(message, "UTF-8");
//				message;

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

		JSONObject cast = new JSONObject(resultString);

		String Result = "";
		Result = cast.getString("Result");
		
		if(!Result.equalsIgnoreCase("Success"))
			isError = true;
	}

}

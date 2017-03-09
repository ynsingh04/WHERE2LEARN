package co.in.where2learn_new.webservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.homescreenfooter.PrivacyPolicyActivity;
import co.in.where2learn_new.homescreenfooter.TnC_Activity;

public class Policy_TnC_WebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;
	private String type;

	private ArrayList<String> categoryFilter;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private CityListWebServiceCall cityListWebServiceCall;
	
	public void getDetails(Activity activityParam, String type) throws Exception {

		this.activity = activityParam;
		this.type = type;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Fetching Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

						Log.e(TAG, Policy_TnC_WebServiceCall.this.type);
						callWebService(Policy_TnC_WebServiceCall.this.type);
					

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
					/**** Intent to workshop filter ***/
					if(Policy_TnC_WebServiceCall.this.type.equalsIgnoreCase(AppConfig.PrivacyPolicyColumn))
					{
						Intent intent = new Intent(activity, PrivacyPolicyActivity.class);
						activity.startActivity(intent);
					} 
					else if(Policy_TnC_WebServiceCall.this.type.equalsIgnoreCase(AppConfig.TnCColumn))
					{
						Intent intent = new Intent(activity, TnC_Activity.class);
						activity.startActivity(intent);
					}
					/**** End Intent to workshop store ***/
				}

			}
		});

	}
	
	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService(String type) throws Exception {

		String URI = AppConfig.URI_PrivacyPolicyTnC;

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

//		initArrayList();

		parseSchoolXML(result);

	}
	
	private void parseSchoolXML(Object result) throws JSONException {

		List<String> messageList;
		
		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			/*String PrivacyPolicy = item.getString(AppConfig.PrivacyPolicyColumn);
			String Tnc = item.getString(AppConfig.TnCColumn);*/
			
			String callType = item.getString("Type");
			String message = item.getString("Message");

			/** Check for search name for adding in relevant array list */
			if (type.equalsIgnoreCase(callType)) {

				AppConfig.Policy_TnC_text = message;
				break;

			}
			else if (type.equalsIgnoreCase(AppConfig.TnCColumn)) {

				AppConfig.Policy_TnC_text = message;
				break;

			}
		}
	}
}

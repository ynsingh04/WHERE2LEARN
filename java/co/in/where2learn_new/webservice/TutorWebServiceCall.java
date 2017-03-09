package co.in.where2learn_new.webservice;

import java.util.ArrayList;

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
import co.in.where2learn_new.navigation.TutorActivity;

public class TutorWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private ArrayList<String> boardFilter;
	private ArrayList<String> classesFilter;
	private ArrayList<String> subjectFilter;
	private ArrayList<String> typeFilter;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private CityListWebServiceCall cityListWebServiceCall;

	public void getDetails(Activity activityParam) throws Exception {

		this.activity = activityParam;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Fetching Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					cityListWebServiceCall = new CityListWebServiceCall();
					cityListWebServiceCall.callWebService();

					callWebService(AppConfig.URITutorType);

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
					/**** Intent to school filter ***/
					Intent intent = new Intent(activity, TutorActivity.class);
					activity.startActivity(intent);
					/**** End Intent to online store ***/
				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService(String type) throws Exception {

		String URI = AppConfig.URI_ATTRIBUTE;

		URI = URI + type;

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

		initArrayList();

		parseSchoolXML(result);

	}

	private void initArrayList() {

		boardFilter = AppConfig.TuitionFilterModel.getBoard();
		classesFilter = AppConfig.TuitionFilterModel.getClasses();
		subjectFilter = AppConfig.TuitionFilterModel.getSubject();
		typeFilter = AppConfig.TuitionFilterModel.getType();

		boardFilter.clear();
		classesFilter.clear();
		subjectFilter.clear();
		typeFilter.clear();

//		typeFilter.add("Home");
//		typeFilter.add("Tuition Center");

	}

	private void parseSchoolXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		Log.e(TAG + " response: ", resultString);
		JSONArray cast = new JSONArray(resultString);
		
		boardFilter.add(activity.getString(R.string.select_all));
		classesFilter.add(activity.getString(R.string.select_all));
		subjectFilter.add(activity.getString(R.string.select_all));
		typeFilter.add(activity.getString(R.string.select_all));

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Search_Name = item.getString("Search_Name");
			String Value = item.getString("Value");

			/** Check for search name for adding in relevant array list */
			if (Search_Name.equalsIgnoreCase("Board")) {

				boardFilter.add(Value);

			} else if (Search_Name.equalsIgnoreCase("Class")) {

				classesFilter.add(Value);

			} else if (Search_Name.equalsIgnoreCase("Subjects")) {

				subjectFilter.add(Value);

			} else if (Search_Name.equalsIgnoreCase("Type")) {

				typeFilter.add(Value);
				if(Value.contains("home") || Value.contains("HOME") || Value.contains("Home"))
					AppConfig.HomeString = Value;

			}
		}

	}

}

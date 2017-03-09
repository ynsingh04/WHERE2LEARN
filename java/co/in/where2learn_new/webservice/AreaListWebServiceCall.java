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
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.navigation.AreaInterface;
import co.in.where2learn_new.util.DropDownListBaseClass;

public class AreaListWebServiceCall {

	private String TAG = this.getClass().getName();

	private ArrayList<String> areaFilter;

	private String city;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	private DropDownListBaseClass dropDownListBaseClass;
	private boolean[] checkSelectedAreaFilterTextView;
	private TextView professionalAreaFilterTextView;

	public AreaListWebServiceCall(String city,
			TextView professionalAreaFilterTextView) {
		this.city = city;
		this.professionalAreaFilterTextView = professionalAreaFilterTextView;

		dropDownListBaseClass = new DropDownListBaseClass();
	}

	public void getDetails(Activity activityParam) throws Exception {

		this.activity = activityParam;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Fetching Data ...", true);
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

					initAreaSelection();
					if(checkSelectedAreaFilterTextView == null)
					{
						Toast.makeText(activity, activity.getString(R.string.no_data_found), Toast.LENGTH_LONG).show();
						return;
					}
					
					if(checkSelectedAreaFilterTextView.length == 0)
					{
						Toast.makeText(activity, activity.getString(R.string.no_data_found), Toast.LENGTH_LONG).show();
						return;
					}
					
					AreaInterface.checkSelectedAreaFilterTextView = checkSelectedAreaFilterTextView;
					showPopUp();

				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_AREA + city;
		
		Log.e(TAG, "URI: " + URI);

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

		parseAreaXML(result);

	}

	private void parseAreaXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);
		
		areaFilter.add(activity.getString(R.string.select_all));

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Value = item.getString("AreaName");

			areaFilter.add(Value);

		}

	}

	private void initArrayList() {

		areaFilter = AppConfig.AreaFilterModel.getAreaFilter();
		areaFilter.clear();

	}

	private void initAreaSelection() {

		checkSelectedAreaFilterTextView = new boolean[AppConfig.AreaFilterModel
				.getAreaFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedAreaFilterTextView.length; i++) {
			checkSelectedAreaFilterTextView[i] = false;
		}
	}

	private void showPopUp() {
		
		DropDownListBaseClass.checkSelected = checkSelectedAreaFilterTextView;

		dropDownListBaseClass.setActivity(activity);
		dropDownListBaseClass.initiatePopUp(
				AppConfig.AreaFilterModel.getAreaFilter(),
				professionalAreaFilterTextView);
	}

}

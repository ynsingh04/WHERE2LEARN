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

import android.os.StrictMode;
import android.util.Log;
import co.in.where2learn_new.config.AppConfig;

public class CityListWebServiceCall {

	private String TAG = this.getClass().getName();

	private ArrayList<String> cityFilter;

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_CITY;
		

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

		initArrayList();

		parseSchoolXML(result);

	}

	private void parseSchoolXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		Log.e(TAG + " response", resultString);
		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Value = item.getString("CityName");
			String Status = item.getString("Status");

			if(Status.trim().equalsIgnoreCase("1"))
			cityFilter.add(Value);
			else if(Status.trim().equalsIgnoreCase("0"))
				cityFilter.add(Value + " (Coming Soon...)");

			Log.e(TAG, "Value : " + Value + "\nStatus : " + Status);
		}

	}

	private void initArrayList() {

		cityFilter = AppConfig.CityFilterModel.getCityFilter();
		cityFilter.clear();
		
	}

}

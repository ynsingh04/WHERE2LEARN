package co.in.where2learn_new.webservice;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
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
import co.in.where2learn_new.detailspage.TutorDetailsActivity;

public class TuitionDetailsWebServiceCall {

	private String TAG = this.getClass().getName();

	private Map<String, String> classifiedDetails;

	private String classified_id;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	public TuitionDetailsWebServiceCall(String classified_id) {
		this.classified_id = classified_id;
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
					

					initMap();

					callClassifiedSearchsWebService();
					callFacilitiesSearchWebService();
					callExternallinkSearchWebService();
//					callImageGalleriesSearchService();
					callRatingSearchService();

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
					Intent intent = new Intent(activity,
							TutorDetailsActivity.class);
					activity.startActivity(intent);
					/**** End Intent to online store ***/
				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callClassifiedSearchsWebService() throws Exception {

		String URI = AppConfig.URI_GETCLASSIFIEDSEARCHS + "?id="
				+ classified_id;

		Log.i(TAG, URI);

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


		parseClassifiedSearchsXML(result);

	}

	private void parseClassifiedSearchsXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String id = item.getString("Classified_id");
			String title = item.getString("Title");
			String descrption = item.getString("Descrption");
			String email = item.getString("Email");
			String mobilenumber = item.getString("Mobilenumber");
			String address = item.getString("Address");

			Log.i(TAG, title);

			classifiedDetails.put("id", id);
			classifiedDetails.put("title", title);
			classifiedDetails.put("description", descrption);
			classifiedDetails.put("email", email);
			classifiedDetails.put("address", address);
			classifiedDetails.put("mobilenumber", mobilenumber);

		}

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callFacilitiesSearchWebService() throws Exception {

		String URI = AppConfig.URI_GETFACILITIESSEARCH + "?id=" + classified_id;

		Log.i(TAG, URI);

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


		parseFacilitiesSearchWebService(result);

	}

	private void parseFacilitiesSearchWebService(Object result)
			throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String facilitiesValue = item.getString("FacilitiesValue");

			Log.i(TAG, facilitiesValue);

			classifiedDetails.put("facilitiesValue", facilitiesValue);

		}

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callExternallinkSearchWebService()
			throws Exception {

		String URI = AppConfig.URI_GETEXTERNALLINKSEARCH + "?id="
				+ classified_id;

		Log.i(TAG, URI);

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


		parseExternallinkSearchXML(result);

	}

	private void parseExternallinkSearchXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String link = item.getString("Link");
			String type = item.getString("Type");

			Log.i(TAG, link);

			classifiedDetails.put("type", type);
			classifiedDetails.put("link", link);

		}

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callImageGalleriesSearchService() throws Exception {

		String URI = AppConfig.URI_GETIMAGEGALLERIESSEARCH + "?id="
				+ classified_id;

		Log.i(TAG, URI);

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


		parseImageGalleriesXML(result);

	}

	private void parseImageGalleriesXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String imageurl = item.getString("Imageurl");

			Log.i(TAG, imageurl);

			classifiedDetails.put("imageurl"+i, imageurl);

		}

	}
	


	protected void callRatingSearchService() throws ClientProtocolException,
			IOException, JSONException {

		String URI = AppConfig.URI_GETRATINGSEARCH + "?id=" + classified_id;

		Log.i(TAG, URI);

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

		parseRatingXML(result);

	}

	private void parseRatingXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);

		JSONObject item = new JSONObject(resultString);

		String rating = item.getString("RatingNo");

		Log.i(TAG, rating + "");

		if (rating == null) {

			rating = AppConfig.DEFAULT_RATING;

		}

		classifiedDetails.put("rating", rating);


	}


	private synchronized void initMap() {

		classifiedDetails = AppConfig.CLASSIFIEDMODEL;
		classifiedDetails.clear();

	}

}

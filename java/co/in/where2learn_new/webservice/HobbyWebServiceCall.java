package co.in.where2learn_new.webservice;

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
import co.in.where2learn_new.model.HobbyFilterCategoryModel;
import co.in.where2learn_new.navigation.HobbyClassesActivity;

public class HobbyWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private List<String> type;
	private List<String> age;
	private List<String> sex;
	private List<String> category1;
	private List<String> category2;
	private List<HobbyFilterCategoryModel> category1Complete;
	private List<HobbyFilterCategoryModel> category2Complete;

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

					callWebService(AppConfig.URIHobbyClassesType);

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
							HobbyClassesActivity.class);
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

	private void initArrayList() {

		type = AppConfig.HobbyFilterModel.getType();
		age = AppConfig.HobbyFilterModel.getAge();
		sex = AppConfig.HobbyFilterModel.getSex();
		category1 = AppConfig.HobbyFilterModel.getCategory1();
		category2 = AppConfig.HobbyFilterModel.getCategory2();

		category1Complete = AppConfig.HobbyFilterModel.getCategory1Complete();
		category2Complete = AppConfig.HobbyFilterModel.getCategory2Complete();

		type.clear();
		age.clear();
		sex.clear();
		category1.clear();
		category2.clear();
		category1Complete.clear();
		category2Complete.clear();

	}

	private void parseSchoolXML(Object result) throws JSONException {

		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);
		
		type.add(activity.getString(R.string.select_all));
		age.add(activity.getString(R.string.select_all));
		sex.add(activity.getString(R.string.select_all));
//		category1.add(activity.getString(R.string.select_all));

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Search_Name = item.getString("Search_Name");
			String Value = item.getString("Value");

			/** Check for search name for adding in relevant array list */
			if(Search_Name.equalsIgnoreCase("Type")) {
				
				type.add(Value);				
				if(Value.contains("home") || Value.contains("HOME") || Value.contains("Home"))
					AppConfig.HomeString = Value;
				
			} else if (Search_Name.equalsIgnoreCase("Age")) {

				age.add(Value);

			} else if (Search_Name.equalsIgnoreCase("sex")) {

				sex.add(Value);

			} else if (Search_Name.equalsIgnoreCase("Category")) {

				HobbyFilterCategoryModel filterCategoryModel = getCategoryModel(
						item, Value);

				category1.add(Value);
				category1Complete.add(filterCategoryModel);

			} else if (Search_Name.equalsIgnoreCase("Sub Category")) {

				HobbyFilterCategoryModel filterCategoryModel = getCategoryModel(
						item, Value);

				category2Complete.add(filterCategoryModel);

			}			
		}

	}

	private HobbyFilterCategoryModel getCategoryModel(JSONObject item,
			String value) throws JSONException {

		HobbyFilterCategoryModel filterCategoryModel = new HobbyFilterCategoryModel();

		String master_Id = item.getString("Master_Id");
		String parent_id = item.getString("Parent_id");

		filterCategoryModel.setMaster_Id(master_Id);
		filterCategoryModel.setSearch_Id(parent_id);
		filterCategoryModel.setValue(value);

		return filterCategoryModel;
	}

}

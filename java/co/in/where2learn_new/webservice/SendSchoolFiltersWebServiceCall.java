package co.in.where2learn_new.webservice;

import java.net.URLEncoder;
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
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.list.SchoolListActivity;

public class SendSchoolFiltersWebServiceCall {

	private String TAG = this.getClass().getName();

	private ArrayList<String> areaFilter;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	private static String city;
	private static String area;
	private static String age;
	private static String board;
	private static String sex;
	private static String type;

	private ArrayList<SearchListItemBean> itemList;

	private int min = AppConfig.CURRENTRANGE;
	private int max = AppConfig.CURRENTRANGE + AppConfig.LISTRANGE;

	private SchoolListActivity schoolListActivity;

	public void sendSchoolFilters(Activity activityParam, String city,
			String area, String age, String board, String sex, String type)
			throws Exception {

		this.activity = activityParam;

		SendSchoolFiltersWebServiceCall.city = city;
		SendSchoolFiltersWebServiceCall.area = area;
		SendSchoolFiltersWebServiceCall.age = age;
		SendSchoolFiltersWebServiceCall.board = board;
		SendSchoolFiltersWebServiceCall.sex = sex;
		SendSchoolFiltersWebServiceCall.type = type;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Searching Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					
					initItemList();
					
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

					/**** Intent to school filter ***/
					Intent intent = new Intent(activity,
							SchoolListActivity.class);
					activity.startActivity(intent);
//					activity.finish();
					/**** End Intent to online store ***/
				}

			}
		});

	}

	protected void initItemList() {

		itemList = AppConfig.SearchedItemList;
		itemList.clear();
		AppConfig.CURRENTRANGE = 1;

		min = AppConfig.CURRENTRANGE;
		max = AppConfig.CURRENTRANGE + AppConfig.LISTRANGE;
		
	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_POSTSCHOOLSEARCH + "?city=" + URLEncoder.encode(city, "UTF-8")
				+ "&area=" + URLEncoder.encode(area, "UTF-8")
				+ "&group=" + URLEncoder.encode(age, "UTF-8")
				+ "&board=" + URLEncoder.encode(board, "UTF-8")
				+ "&sex="	+ URLEncoder.encode(sex, "UTF-8")
				+ "&type=" + URLEncoder.encode(type, "UTF-8")
				+ getRange();

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

//		initArrayList();

		Log.i(TAG, result.toString());

		parseAreaXML(result);

	}

	private String getRange() {
		return "&Rmin=" + min + "&Rmax=" + max;
	}

	public void nextRange() {		
		AppConfig.CURRENTRANGE += AppConfig.LISTRANGE;
		min = AppConfig.CURRENTRANGE;
		if(min>10) {
			min += 1;
		}
//		AppConfig.CURRENTRANGE += AppConfig.LISTRANGE;
		max = AppConfig.CURRENTRANGE + AppConfig.LISTRANGE;
		Log.e(TAG, "Min : " + min + "\nMax : " + max);
	}

	private void parseAreaXML(Object result) throws JSONException {


		String resultString = result.toString();

		Log.i(TAG, resultString);
		Log.e(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);
		
		AppConfig.EMAIL = "";
		AppConfig.COUNT = "";

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Descrption = item.getString("Descrption");
			// String Email = item.getString("Email");
			String Mobilenumber = item.getString("Mobilenumber");
			String Title = item.getString("Title");
			String classified_id = item.getString("Classified_id");

			SearchListItemBean itemBean = new SearchListItemBean();
			itemBean.setImgViewIcon(R.drawable.icon_school_img);
			itemBean.setTxtViewTitle(Title);
			itemBean.setTxtViewDescrption(Descrption);
			itemBean.setTxtViewPhoneNo(Mobilenumber);
			itemBean.setClassified_id(classified_id);
			
			/**
			 * Set Email & Listings Count
			 */
			if(i==0) {
				String Email = item.getString("Email");
				AppConfig.EMAIL_AND_COUNT = Email;
				
				if(AppConfig.EMAIL_AND_COUNT.contains(",") && AppConfig.EMAIL_AND_COUNT.charAt(0)==',') {
					AppConfig.EMAIL = "";
					AppConfig.COUNT = AppConfig.EMAIL_AND_COUNT.replace(",", "");
				}
				else if(AppConfig.EMAIL_AND_COUNT.contains(",") && AppConfig.EMAIL_AND_COUNT.charAt(AppConfig.EMAIL_AND_COUNT.length()-1)==',') {
					AppConfig.EMAIL = AppConfig.EMAIL_AND_COUNT.replace(",", "");
					AppConfig.COUNT = "";
				}
				else if(AppConfig.EMAIL_AND_COUNT.contains(",")) {
					String[] splitString = AppConfig.EMAIL_AND_COUNT.split(",");
					AppConfig.EMAIL = splitString[0];					
					AppConfig.COUNT = splitString[1];
				}
				else {
					AppConfig.EMAIL = "";
					AppConfig.COUNT = "";
				}
			}

			itemList.add(itemBean);

		}

	}

	private void initArrayList() {

		areaFilter = AppConfig.AreaFilterModel.getAreaFilter();
		areaFilter.clear();

	}
	
	public void loadSchoolFilters(SchoolListActivity schoolListActivityParam)
			throws Exception {
 
		this.schoolListActivity = schoolListActivityParam;

		ringProgressDialog = ProgressDialog.show(schoolListActivityParam, "Please wait ...",
				"Loading more data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					itemList = AppConfig.SearchedItemList;
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

					schoolListActivity.initListView();
				}

			}
		});

	}

}

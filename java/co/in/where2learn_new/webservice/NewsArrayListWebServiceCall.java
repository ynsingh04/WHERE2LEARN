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
import co.in.where2learn_new.bean.NotificationListItemBean;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.navigation.NewsActivity;

public class NewsArrayListWebServiceCall {

	private String TAG = this.getClass().getName();

	private ArrayList<NotificationListItemBean> notificationListItemBeans;
	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	public void getNewsArrayDetails(Activity activityParam) {
		this.activity = activityParam;

		ringProgressDialog = ProgressDialog.show(activity, "Please wait ...",
				"Fetching Data ...", true);
		ringProgressDialog.setCancelable(false);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					initArrayList();

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
					/**** Intent to news activity ***/
					Intent intent = new Intent(activity, NewsActivity.class);
					activity.startActivity(intent);
					/**** End Intent to news ***/
				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_NewsDetails + "?min=1&max=50";

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

		parseNewsXML(result);

	}

	private void parseNewsXML(Object result) throws JSONException {

		String resultString = result.toString();

		NotificationListItemBean notificationListItemBean;

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String classifiedID = item.getString("Classified_id");
			String title = item.getString("Title");
			String Date = item.getString("Date");
			String Message = item.getString("Message");
			String categoryName = item.getString("CategoryName");
			
			String startDate = item.getString("StartDate");
			String endDate = item.getString("EndDate");

			notificationListItemBean = new NotificationListItemBean();

			notificationListItemBean
					.setImgViewIcon(R.drawable.notification_date_and_time);
			notificationListItemBean.setDataAndTime(Date);
			notificationListItemBean.setMessage(Message);
			notificationListItemBean.setCategoryName(categoryName);
			notificationListItemBean.setTitle(title);
			notificationListItemBean.setClassifiedID(classifiedID);
			
			notificationListItemBean.setStartDateAndTime(startDate);
			notificationListItemBean.setEndDateAndTime(endDate);

			notificationListItemBeans.add(notificationListItemBean);

		}

	}

	private void initArrayList() {

		notificationListItemBeans = AppConfig.NotificationOrNewsItemList;
		notificationListItemBeans.clear();

	}

}

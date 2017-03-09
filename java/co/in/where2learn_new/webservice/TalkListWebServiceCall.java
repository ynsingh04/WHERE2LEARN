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
import co.in.where2learn_new.bean.TalkListItemBean;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.navigation.TalksActivity;

public class TalkListWebServiceCall {

	private String TAG = this.getClass().getName();

	private ArrayList<TalkListItemBean> talkItemList;
	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Activity activity;

	@SuppressWarnings("unused")
	private String userID;

	public TalkListWebServiceCall(String userID) {
		this.userID = userID;
	}

	public void getTalkList(Activity activityParam) {
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

				/*if (isError) {

					Toast.makeText(
							activity,
							activity.getString(R.string.error_in_data_initialization),
							Toast.LENGTH_LONG).show();
				} else {*/
					/**** Intent to school filter ***/
					Intent intent = new Intent(activity, TalksActivity.class);
					activity.startActivity(intent);
					/**** End Intent to online store ***/
//				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_SearchTalkAndQueries + "?RMax=20&RMin=0";

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

		parseSchoolXML(result);

	}

	private void parseSchoolXML(Object result) throws JSONException {

		String resultString = result.toString();

		TalkListItemBean talkListItemBean;

		Log.i(TAG, resultString);

		JSONArray cast = new JSONArray(resultString);

		for (int i = 0; i < cast.length(); i++) {
			JSONObject item = cast.getJSONObject(i);

			String Message = item.getString("Message");
			String SysDate = item.getString("SysDate");
			String UserName = item.getString("UserName");

			talkListItemBean = new TalkListItemBean();

//			talkListItemBean.setImgViewIcon(R.drawable.career_header);
			talkListItemBean.setName(UserName);
			talkListItemBean.setDateAndTime(SysDate);
			talkListItemBean.setMessage(Message);
			talkListItemBean.setLike(0 + "");

			talkItemList.add(talkListItemBean);

		}

	}

	private void initArrayList() {

		talkItemList = AppConfig.TalkItemList;
		talkItemList.clear();

	}

}

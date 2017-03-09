package co.in.where2learn_new.webservice;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;

public class NotifyMeWebServiceCall {

	private String TAG = this.getClass().getName();

	private String userName;
	private String classifiedID;

	private ProgressDialog ringProgressDialog;

	private boolean isError = false;

	private Context context;

	private String result;

	public NotifyMeWebServiceCall(Context context) {
		this.context = context;
	}

	public void setNotifyMe(String userName, String classifiedID)
			throws Exception {

		this.userName = userName;
		this.classifiedID = classifiedID;

		ringProgressDialog = ProgressDialog.show(context, "Please wait ...",
				"Sending Data ...", true);
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

				if (isError || (!result.equalsIgnoreCase("Success"))) {
					Toast.makeText(context,
							context.getString(R.string.error_at_server_end),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(context,
							context.getString(R.string.request_processed),
							Toast.LENGTH_LONG).show();
				}

			}
		});

	}

	/**
	 * This method is called to call web service
	 */
	public synchronized void callWebService() throws Exception {

		String URI = AppConfig.URI_SaveNotify + "?UserID=" + userName
				+ "&ClassifiedID=" + classifiedID;

		Log.e(TAG,URI);

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

		parseAreaXML(result);

	}

	private void parseAreaXML(Object resultObject) throws JSONException {

		String resultString = resultObject.toString();

		Log.e(TAG, resultString);

		JSONObject item = new JSONObject(resultString);

		result = item.getString("Result"); 

	}

}

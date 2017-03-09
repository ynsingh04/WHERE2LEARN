package co.in.where2learn_new.webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.navigation.WorkShopAndEventActivity;

public class WorkShopWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;
 
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
					Intent intent = new Intent(activity, WorkShopAndEventActivity.class);
					activity.startActivity(intent);
					/**** End Intent to workshop store ***/
				}

			}
		});

	}

	


 
	 

}

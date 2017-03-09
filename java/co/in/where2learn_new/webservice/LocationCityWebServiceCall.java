package co.in.where2learn_new.webservice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.footer.LocationDialog;

public class LocationCityWebServiceCall {

	private String TAG = this.getClass().getName();

	private Activity activity;

	private ProgressDialog ringProgressDialog;

	private LocationDialog locationDialog;

	private boolean isError = false;

	private CityListWebServiceCall cityListWebServiceCall;

	public void getCityListDetails(Activity activityParam) throws Exception {

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
					/**** Show location dialog ***/
					locationDialog = new LocationDialog(activity);
					locationDialog.show();
					/**** End Show location dialog ***/
				}

			}
		});

	}

}

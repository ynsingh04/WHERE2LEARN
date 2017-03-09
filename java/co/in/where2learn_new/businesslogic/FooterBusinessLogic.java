package co.in.where2learn_new.businesslogic;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.footer.LoginActivity;
import co.in.where2learn_new.footer.SignUpActivity;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.util.PerformBackgroundTask;
import co.in.where2learn_new.webservice.LocationCityWebServiceCall;
import co.in.where2learn_new.webservice.NotificationArrayListWebServiceCall;
import co.in.where2learn_new.webservice.SendLoginValidationWebServiceCall;
import co.in.where2learn_new.webservice.SendRegistrationDetailsWebServiceCall;

public class FooterBusinessLogic {

	private String TAG = this.getClass().getName();
	private Activity activity;

	private SendRegistrationDetailsWebServiceCall sendRegistrationDetailsWebServiceCall;
	private SendLoginValidationWebServiceCall sendLoginValidationWebServiceCall;

	private LocationCityWebServiceCall locationCityWebServiceCall;
	private NotificationArrayListWebServiceCall notificationArrayListWebServiceCall;
	
	private String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	public FooterBusinessLogic(Activity activity) {

		this.activity = activity;

	}

	private void showProgressBar() {

	}

	private void invokeIntent(Intent intent) {

		showProgressBar();

		PerformBackgroundTask backgroundTask = new PerformBackgroundTask(
				activity);
		backgroundTask.execute(intent);

	}

	public void invokeNotification() {

		if (AppConfig.UserModel != null) {

			String userId = AppConfig.UserModel.getEmailString();

			boolean isArrayInitialized = initNotificationArray(userId);

			if (!isArrayInitialized) {
				Toast.makeText(
						activity,
						activity.getString(R.string.error_in_data_initialization),
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(activity, activity.getString(R.string.please_login),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initNotificationArray(String userId) {
		boolean isArrayInitialized = false;

		try {
			notificationArrayListWebServiceCall = new NotificationArrayListWebServiceCall();
			notificationArrayListWebServiceCall.setUserID(userId);
			notificationArrayListWebServiceCall
					.getNotificationArrayDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeLogin() {

		Intent intent = new Intent(activity, LoginActivity.class);

		invokeIntent(intent);

	}

	public void invokeSignUp() {

		Intent intent = new Intent(activity, SignUpActivity.class);

		invokeIntent(intent);

	}

	public void invokeLocation() {

		boolean isArrayInitialized = initLocationCityArray();

		if (!isArrayInitialized) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean initLocationCityArray() {
		boolean isArrayInitialized = false;

		try {

			locationCityWebServiceCall = new LocationCityWebServiceCall();
			locationCityWebServiceCall.getCityListDetails(activity);

			isArrayInitialized = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isArrayInitialized;
	}

	public void invokeRegistrationLogic(UserModel model) {

		boolean isSendedDetails = sendRegistrationDetails(model);

		if (!isSendedDetails) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}

	}

	private boolean sendRegistrationDetails(UserModel model) {
		boolean isSendedDetails = false;

		try {

			sendRegistrationDetailsWebServiceCall = new SendRegistrationDetailsWebServiceCall(
					model);
			sendRegistrationDetailsWebServiceCall
					.sendRegistrationDetails(activity);

			isSendedDetails = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isSendedDetails;
	}

	public void invokeLoginValidation(UserModel userModel) {

		boolean isSendedDetails = sendLoginValidation(userModel);

		if (!isSendedDetails) {
			Toast.makeText(activity,
					activity.getString(R.string.error_in_data_initialization),
					Toast.LENGTH_LONG).show();
		}
		/*else if(!userModel.getEmailString().matches(email_pattern))
		{
			Toast.makeText(activity,
					activity.getString(R.string.invalid_email_id),
					Toast.LENGTH_LONG).show();
		}
		else if(!userModel.getPasswordString().contains(" "))
		{
			Toast.makeText(activity,
					activity.getString(R.string.invalid_password),
					Toast.LENGTH_LONG).show();
		}*/

	}

	private boolean sendLoginValidation(UserModel userModel) {

		boolean isSendedDetails = false;

		try {

			sendLoginValidationWebServiceCall = new SendLoginValidationWebServiceCall(
					userModel);
			sendLoginValidationWebServiceCall.sendLoginValidation(activity);

			isSendedDetails = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isSendedDetails;

	}

}

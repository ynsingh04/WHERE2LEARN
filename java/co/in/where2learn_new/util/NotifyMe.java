package co.in.where2learn_new.util;

import android.app.Activity;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.webservice.NotifyMeWebServiceCall;

public class NotifyMe {

	private Activity activity;

	private NotifyMeWebServiceCall notifyMeWebServiceCall;

	public NotifyMe(Activity activity) {
		this.activity = activity;
	}

	public void notify(String classifiedID) {

		notifyMeWebServiceCall = new NotifyMeWebServiceCall(activity);
		if (AppConfig.UserModel != null) {
			try {
				notifyMeWebServiceCall.setNotifyMe(
						AppConfig.UserModel.getUserID(), classifiedID);
			} catch (Exception e) {
				Toast.makeText(activity,
						activity.getString(R.string.error_at_server_end),
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(activity, activity.getString(R.string.please_login),
					Toast.LENGTH_LONG).show();
		}

	}

}

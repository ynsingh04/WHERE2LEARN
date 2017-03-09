package co.in.where2learn_new.businesslogic;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import co.in.where2learn_new.util.PerformBackgroundTask;

public class DetailsPageBusinessLogic {

	private Activity activity;

	public DetailsPageBusinessLogic(Activity activity) {
		this.activity = activity;
	}

	private void invokeIntent(Intent intent) {

		PerformBackgroundTask backgroundTask = new PerformBackgroundTask(
				activity);
		backgroundTask.execute(intent);

	}

	public void invokePhoneCall(String mobileNumber) {

		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ mobileNumber));
		invokeIntent(intent);
	}

}

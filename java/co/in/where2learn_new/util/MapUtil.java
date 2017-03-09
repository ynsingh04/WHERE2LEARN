package co.in.where2learn_new.util;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class MapUtil {

	private Activity activity;

	public MapUtil(Activity activity) {
		this.activity = activity;
	}

	public void gpsMethod(String address) {

		/*
		 * / The Possible Query params options are the following:
		 * 
		 * Show map at location: geo:latitude,longitude Show zoomed map at
		 * location: geo:latitude,longitude?z=zoom Show map at locaiton with
		 * point: geo:0,0?q=my+street+address Show map of businesses in area:
		 * geo:0,0?q=business+near+city
		 */
		try {
			PackageManager packageManager = activity.getPackageManager();

			// You can also choose to place a point like so:
			String uri = "geo:0,0?q=" + address;

			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse(uri));
			intent.setClassName("com.google.android.apps.maps",
					"com.google.android.maps.MapsActivity");

			List<ResolveInfo> activities = packageManager
					.queryIntentActivities(intent, 0);

			boolean isIntentSafe = activities.size() > 0;

			if (isIntentSafe) {
				activity.startActivity(intent);

			} else {
				Toast.makeText(activity,
						"Please install Google Map for searching location.",
						Toast.LENGTH_LONG).show();

				Log.i("INFO gpsMethod", "5");
				String url = "https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				activity.startActivity(i);

				Log.i("INFO gpsMethod", "6");
			}
		} catch (Exception e) {
			Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
		}
		Log.i("INFO gpsMethod", "7");
	}

}

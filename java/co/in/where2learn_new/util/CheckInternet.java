package co.in.where2learn_new.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author chitresh.verma
 *         <p>
 *         This class check for Internet connection.
 *         </p>
 */
public class CheckInternet {

	public static final int TYPE_WIFI = 1;
	public static final int TYPE_MOBILE = 2;
	public static final int TYPE_NOT_CONNECTED = 0;

	/**
	 * This method is called to get connectivity status.
	 * 
	 * @param context
	 *            Context object
	 * @return Status value
	 */
	public static int getConnectivityStatus(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
				return TYPE_WIFI;
			}

			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				return TYPE_MOBILE;
		}
		return TYPE_NOT_CONNECTED;
	}

	/**
	 * This method is called to get connectivity status string
	 * 
	 * @param context
	 *            Context object
	 * @return Status string
	 */
	public String getConnectivityStatusString(Context context) {
		int conn = CheckInternet.getConnectivityStatus(context);
		String status = null;
		if (conn == CheckInternet.TYPE_WIFI) {
			status = "Wifi enabled";
		} else if (conn == CheckInternet.TYPE_MOBILE) {
			status = "Mobile data enabled";
		} else if (conn == CheckInternet.TYPE_NOT_CONNECTED) {
			status = "Not connected to Internet";
		}
		return status;
	}

}

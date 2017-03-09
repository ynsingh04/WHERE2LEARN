package co.in.where2learn_new.util;

import android.app.Activity;
import android.content.pm.PackageManager;

public class CheckAnAppInstalledOrNot {
	
	private String TAG = this.getClass().getName();
	private Activity activity;
	
	public CheckAnAppInstalledOrNot(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	public boolean appInstalledOrNot(String uri) {
        PackageManager pm = activity.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
	
}

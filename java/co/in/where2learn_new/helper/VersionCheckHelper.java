package co.in.where2learn_new.helper;

import org.jsoup.Jsoup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;

public class VersionCheckHelper extends AsyncTask<Void, String, String>{
	
	private Activity activity;
	private ProgressDialog pd;
	
	public VersionCheckHelper(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		String newVersion = null;
        try {
            newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + activity.getPackageName() + "&hl=it")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop=softwareVersion]")
                    .first()
                    .ownText();
            return newVersion;
        } catch (Exception e) {
            return newVersion;
        }
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		pd = new ProgressDialog(activity);
		pd.setMessage("Please wait...");
		pd.setCancelable(false);
//		pd.show();
	}
	
	@Override
    protected void onPostExecute(String onlineVersion) {
        super.onPostExecute(onlineVersion);
        
        if(pd!=null && pd.isShowing())
        	pd.dismiss();
        
        if (onlineVersion != null && !onlineVersion.isEmpty()) {
            if (Float.valueOf(AppConfig.VERSION) < Float.valueOf(onlineVersion)) {
            	showDialog();
//            	Toast.makeText(activity, "Current version " + AppConfig.VERSION + "playstore version " + onlineVersion, Toast.LENGTH_LONG).show();
//            	activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName())));
                //show dialog
            }
        }
        Log.e("update", "Current version " + AppConfig.VERSION + "playstore version " + onlineVersion);
//        Toast.makeText(activity, "Current version " + AppConfig.VERSION + "playstore version " + onlineVersion, Toast.LENGTH_LONG).show();
    }
	
	public void showDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(activity.getString(R.string.upgrade_alert))
				.setCancelable(true)
				.setPositiveButton("UPGRADE NOW",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName())));
								// do things
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

}

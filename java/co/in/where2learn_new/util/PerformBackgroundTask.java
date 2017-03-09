package co.in.where2learn_new.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author chitresh.verma
 * <p>
 * This AsyncTask handles background intent while showing the progress bar.
 * </p>
 */
public class PerformBackgroundTask extends AsyncTask<Intent, Void, Void> {
	
	 
	private static final String TAG = "PerformBackgroundTask";
	private ProgressDialog Dialog;
	private Context context;
	
	/**
	 * This constructor is called to set activity object and initialize the dialog object.
	 * @param activity
	 */
	public PerformBackgroundTask(Context context)
	{
		this.context=context;
		Dialog = new ProgressDialog(context);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	protected void onPreExecute() {
		Dialog.setMessage("Please wait...");
		Dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		Dialog.show();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	protected void onPostExecute(Void unused)    
    {
        try
        {
            if(Dialog.isShowing())
            {
                Dialog.dismiss();
            }
                    // do your Display and data setting operation here
        }
        catch(Exception e)
        {
        	Log.e(TAG,e.toString());
        }
    }
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Void doInBackground(Intent... intent) {
		// Do your background data fetching here 
		
		context.startActivities(intent);
		return null;
	}
}

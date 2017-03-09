package co.in.where2learn_new.util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import co.in.where2learn_new.R;

public class SearchBar {
	
	private String TAG = this.getClass().getName();
	private Activity activity;
	private View titleBarView;
	private ImageView mainmenu_search;
	
	private View.OnClickListener mainmenu_searchOnClickListener;

	public SearchBar(Activity activity) {

		this.activity = activity;
		
	}

	public void changeBar() {

		try {
 			
			titleBarView = getSerachTitleBarView();

			mainmenu_search = (ImageView) titleBarView
					.findViewById(R.id.mainmenu_search);
			mainmenu_search.setOnClickListener(mainmenu_searchOnClickListener);

			ActionBar actionBar = activity.getActionBar();
			actionBar.setCustomView(titleBarView);
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
 

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}
		
	}
	 

	/**
	 * This method is called to get the view
	 * 
	 * @return Inflated view
	 */
	@SuppressLint("InflateParams")
	private View getSerachTitleBarView() {

		LayoutInflater inflater = LayoutInflater.from(activity); // 1
		View theInflatedView = inflater.inflate(R.layout.titlebar_search, null); // 2

		return theInflatedView;
	}

}

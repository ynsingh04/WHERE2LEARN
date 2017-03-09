package co.in.where2learn_new.util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import co.in.where2learn_new.R;

/**
 * @author chitresh.verma
 *         <p>
 *         This class handles the change of title bar.
 *         </p>
 */
public class ChangeTitleBar {

	private Activity activity;
	private String TAG = this.getClass().getName();

	private ImageView mainmenu_search;
	private View.OnClickListener mainmenu_searchOnClickListener;	

	private SearchBar searchBar;

	/**
	 * This constructor is called create object of this class.
	 * 
	 * @param activity
	 *            Activity object
	 */
	public ChangeTitleBar(Activity activity) {
		this.activity = activity;

		initListener();

	}

	private void initListener() {

		mainmenu_searchOnClickListener = new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				
				searchBar = new SearchBar(activity);				
				searchBar.changeBar();
				
				Toast.makeText(activity,
						activity.getString(R.string.under_construction),
						Toast.LENGTH_SHORT).show();

			}
		};

	}

	/**
	 * This method is called to change the title bar.
	 * 
	 * @return change result.
	 */
	public boolean change() {
		boolean isChanged = false;
		View titleBarView;

		try {

			titleBarView = getTitleBarView();

			mainmenu_search = (ImageView) titleBarView
					.findViewById(R.id.mainmenu_search);
			mainmenu_search.setOnClickListener(mainmenu_searchOnClickListener);

			ActionBar actionBar = activity.getActionBar();
			actionBar.setCustomView(titleBarView);
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

			isChanged = true;

		} catch (Exception e) {

			Log.e(TAG, e.toString());

		}

		return isChanged;
	}

	/**
	 * This method is called to get the view
	 * 
	 * @return Inflated view
	 */
	@SuppressLint("InflateParams")
	private View getTitleBarView() {

		LayoutInflater inflater = LayoutInflater.from(activity); // 1
		View theInflatedView = inflater.inflate(R.layout.titlebar, null); // 2

		return theInflatedView;
	}

}

package co.in.where2learn_new.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;
import co.in.where2learn_new.R;

/**
 * @author chitresh.verma
 *         <p>
 *         This class handles the show current fragment.
 *         </p>
 */
@SuppressLint("NewApi")
public class ChangeFragmentOnlineStoreImageView {

	private Activity activity;

	private ImageView fragment1ImageView;
	private ImageView fragment2ImageView;
	private ImageView fragment3ImageView;

	/**
	 * This constructor is called to ChangeFragmentImageView initialization.
	 * 
	 * @param activity
	 *            Activity object
	 */
	public ChangeFragmentOnlineStoreImageView(Activity activity) {
		this.activity = activity;
	}

	/**
	 * This method is called to change ImageViews.
	 * 
	 * @param currentFragmet
	 *            Current fragment number
	 */
	public void changeImageViews(int currentFragmet) {

		initImageViews();

		setNonSelectedOnImageViews();

		setSelectImageView(currentFragmet);

	}

	/**
	 * This method is called initialize all the ImageViews.
	 */
	private void initImageViews() {

		fragment1ImageView = (ImageView) activity
				.findViewById(R.id.main_iv_fragement1Dot);
		fragment2ImageView = (ImageView) activity
				.findViewById(R.id.main_iv_fragement2Dot);
		fragment3ImageView = (ImageView) activity
				.findViewById(R.id.main_iv_fragement3Dot);
	}

	/**
	 * This method is called to set the non-selected image.
	 */
	private void setNonSelectedOnImageViews() {

		fragment1ImageView.setImageDrawable(activity.getResources()
				.getDrawable(R.drawable.nonselected));
		fragment2ImageView.setImageDrawable(activity.getResources()
				.getDrawable(R.drawable.nonselected));
		fragment3ImageView.setImageDrawable(activity.getResources()
				.getDrawable(R.drawable.nonselected));
	}

	/**
	 * This method is called to set selected image.
	 * 
	 * @param currentFragmet
	 *            Current fragment number.
	 */
	private void setSelectImageView(int currentFragmet) {

		switch (currentFragmet) {
		case 1:
			fragment1ImageView.setImageDrawable(activity.getResources()
					.getDrawable(R.drawable.selected));
			break;
		case 2:
			fragment2ImageView.setImageDrawable(activity.getResources()
					.getDrawable(R.drawable.selected));
			break;
		case 3:
			fragment3ImageView.setImageDrawable(activity.getResources()
					.getDrawable(R.drawable.selected));
			break;

		default:
			Toast.makeText(activity, "Error",
					Toast.LENGTH_SHORT).show();
			break;
		}

	}

}

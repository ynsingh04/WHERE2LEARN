package co.in.where2learn_new.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.MainActivityBusinessLogic;
import co.in.where2learn_new.util.CheckInternet;

public class MainFragmentThree extends Fragment{

	private static final String TAG = "MainFragmentThree";
	private Activity activity;
	private View view;
	
	private RelativeLayout shop;	
	
	private View.OnClickListener shop_Listener;
	
	private MainActivityBusinessLogic businessLogic;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_main_three, container,
				false);
		Log.i(TAG, "Initialized " + TAG);
		
		initListener();
		
		initComponent();
		
		return view;
	}
	
	private void initComponent()
	{
		shop = (RelativeLayout) view.findViewById(R.id.mainFragmentThree_rl_shop);
    	
		shop.setOnClickListener(shop_Listener);
    	
    	businessLogic = new MainActivityBusinessLogic(activity);
	}
	
	private void initListener()
	{
/** New Views' Listeners */
    	
		shop_Listener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					//businessLogic.invokeShops();
						Toast.makeText(activity,
								activity.getString(R.string.coming_soon),
								Toast.LENGTH_LONG).show();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
    	
    	/** End New Views' Listeners */
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#setMenuVisibility(boolean)
	 */
	@Override
	public void setMenuVisibility(boolean menuVisible) {
		// TODO Auto-generated method stub
		super.setMenuVisibility(menuVisible);
		
		Log.i(TAG, "Visible  " + TAG);

		/*** Set bottom bubble on visible ****/
		if (menuVisible) {
			MainActivity.fragmentMainImageView
					.changeImageViews(3);
		}
	}
	
}

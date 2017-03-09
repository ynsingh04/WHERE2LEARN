package co.in.where2learn_new.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.MainActivityBusinessLogic;
import co.in.where2learn_new.util.CheckInternet;

public class MainFragmentOne extends Fragment{

	private static final String TAG = "MainFragmentOne";
	private Activity activity;
	View view;
	
	private RelativeLayout school;
	private RelativeLayout college;
	private RelativeLayout tuition;
	private RelativeLayout coaching;
	
	private View.OnClickListener school_Listener;
	private View.OnClickListener college_Listener;
	private View.OnClickListener tuition_Listener;
	private View.OnClickListener coaching_Listener;
	
	private MainActivityBusinessLogic businessLogic;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_main_one, container,
				false);
		Log.i(TAG, "Initialized " + TAG);
		
		initListener();
		
		initComponent();
		
		return view;
	}
	
	private void initComponent()
	{
		school = (RelativeLayout) view.findViewById(R.id.mainFragmentOne_rl_school);
    	college = (RelativeLayout) view.findViewById(R.id.mainFragmentOne_rl_college);
    	tuition = (RelativeLayout) view.findViewById(R.id.mainFragmentOne_rl_tuition);
    	coaching = (RelativeLayout) view.findViewById(R.id.mainFragmentOne_rl_coaching);
    	
    	school.setOnClickListener(school_Listener);
    	college.setOnClickListener(college_Listener);
    	tuition.setOnClickListener(tuition_Listener);
    	coaching.setOnClickListener(coaching_Listener);
    	
    	businessLogic = new MainActivityBusinessLogic(activity);
	}
	
	private void initListener()
	{
/** New Views' Listeners */
    	
    	school_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeSchoolActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
		
		college_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCollegeActivity();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();
			}
		};
		
		tuition_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeTutorActivity();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		coaching_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCoachingActivity();
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
					.changeImageViews(2);
		}
	}
	
}

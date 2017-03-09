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

public class MainFragmentTwo extends Fragment{

	private static final String TAG = "MainFragmentTwo";
	private Activity activity;
	private View view;
	
	private RelativeLayout hobby;
	private RelativeLayout workshop;
	private RelativeLayout career;
	private RelativeLayout professional;
	
	private View.OnClickListener hobby_Listener;
	private View.OnClickListener workshop_Listener;
	private View.OnClickListener career_Listener;
	private View.OnClickListener professional_Listener;
	
	
	private MainActivityBusinessLogic businessLogic;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		activity = getActivity();

		view = inflater.inflate(R.layout.fragment_main_two, container,
				false);
		Log.i(TAG, "Initialized " + TAG);
		
		initListener();
		
		initComponent();
		
		return view;
	}
	
	private void initComponent()
	{
		hobby = (RelativeLayout) view.findViewById(R.id.mainFragmentTwo_rl_hobby);
		workshop = (RelativeLayout) view.findViewById(R.id.mainFragmentTwo_rl_workshop);
		career = (RelativeLayout) view.findViewById(R.id.mainFragmentTwo_rl_career);
		professional = (RelativeLayout) view.findViewById(R.id.mainFragmentTwo_rl_professional);
    	
		hobby.setOnClickListener(hobby_Listener);
		workshop.setOnClickListener(workshop_Listener);
		career.setOnClickListener(career_Listener);
		professional.setOnClickListener(professional_Listener);
    	
    	businessLogic = new MainActivityBusinessLogic(activity);
	}
	
	private void initListener()
	{
/** New Views' Listeners */
    	
		hobby_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeHobbyClassesActivity();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		workshop_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeWorkshopAndEvents();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		career_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeCareerAndMore();
				else
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
			}
		};
		
		professional_Listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeProfessional();
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
					.changeImageViews(1);
		}
	}
	
}

package co.in.where2learn_new.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.adapter.DropDownListAdapter;
import co.in.where2learn_new.navigation.CareerActivity;
import co.in.where2learn_new.navigation.CoachingActivity;
import co.in.where2learn_new.navigation.CollegeActivity;
import co.in.where2learn_new.navigation.HobbyClassesActivity;
import co.in.where2learn_new.navigation.ProfessionalCourseActivity;
import co.in.where2learn_new.navigation.SchoolActivity;
import co.in.where2learn_new.navigation.TutorActivity;
import co.in.where2learn_new.navigation.WorkShopAndEventActivity;
import co.in.where2learn_new.shop.ShopActivity;

public class DropDownListBaseClass {

	private PopupWindow pw;
	public static boolean[] checkSelected = null;

	private Activity activity;
	
	private static TextView tv;
	
	public static DropDownListAdapter adapter;

	/*
	 * Function to set up the pop-up window which acts as drop-down list
	 */
	@SuppressWarnings("deprecation")
	public void initiatePopUp(ArrayList<String> items, TextView tv) {
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// get the pop-up window i.e. drop-down layout
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.pop_up_window,
				(ViewGroup) activity.findViewById(R.id.PopUpView));
		
		this.tv = tv;

		pw = new PopupWindow(layout, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT, true);

		// Pop-up window background cannot be null if we want the pop-up to
		// listen touch events outside its window
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.setTouchable(true);

		// let pop-up be informed about touch events outside its window. This
		// should be done before setting the content of pop-up
		pw.setOutsideTouchable(true);
		pw.setHeight(LayoutParams.WRAP_CONTENT);

		// dismiss the pop-up i.e. drop-down when touched anywhere outside the
		// pop-up
		pw.setTouchInterceptor(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					pw.dismiss();
					return true;
				}
				return false;
			}
		});
		final TextView checkTV = tv;
		pw.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				/*if(activity==CareerActivity.activity && checkTV==CareerActivity.careerAreaFilterTextView) {
					CareerActivity.checkSelectedAreaFilterTextView = checkSelected;
					CareerActivity.careerAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CareerActivity().getArea());
				}
				else if(activity==CoachingActivity.activity && checkTV==CoachingActivity.coachingAreaFilterTextView) {
					CoachingActivity.checkSelectedAreaFilterTextView = checkSelected;
					CoachingActivity.coachingAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CoachingActivity().getArea());
				}
				else if(activity==HobbyClassesActivity.activity && checkTV==HobbyClassesActivity.hobbyAreaFilterTextView) {
					HobbyClassesActivity.checkSelectedAreaFilterTextView = checkSelected;
					HobbyClassesActivity.hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new HobbyClassesActivity().getArea());
				}
				else if(activity==ProfessionalCourseActivity.activity && checkTV==ProfessionalCourseActivity.professionalAreaFilterTextView) {
					ProfessionalCourseActivity.checkSelectedAreaFilterTextView = checkSelected;
					ProfessionalCourseActivity.professionalAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ProfessionalCourseActivity().getArea());
				}
				else if(activity==SchoolActivity.activity && checkTV==SchoolActivity.schoolAreaFilterTextView) {
					SchoolActivity.checkSelectedAreaFilterTextView = checkSelected;
					SchoolActivity.schoolAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new SchoolActivity().getArea());
				}
				else if(activity==TutorActivity.activity && checkTV==TutorActivity.tutorAreaFilterTextView) {
					TutorActivity.checkSelectedAreaFilterTextView = checkSelected;
					TutorActivity.tutorAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new TutorActivity().getArea());
				}
				else if(activity==ShopActivity.activity && checkTV==ShopActivity.shopAreaFilterTextView) {
					ShopActivity.checkSelectedAreaFilterTextView = checkSelected;
					ShopActivity.shopAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ShopActivity().getArea());
				}*/
				
			if(activity==CareerActivity.activity) {
					CareerActivity.setSelections(checkTV);
				}
			else if(activity==CoachingActivity.activity) {
				CoachingActivity.setSelections(checkTV);
			}
			else if(activity==CollegeActivity.activity) {
				CollegeActivity.setSelections(checkTV);
			}
			else if(activity==HobbyClassesActivity.activity) {
				HobbyClassesActivity.setSelections(checkTV);
			}
			else if(activity==ProfessionalCourseActivity.activity) {
				ProfessionalCourseActivity.setSelections(checkTV);
			}
			else if(activity==SchoolActivity.activity) {
				SchoolActivity.setSelections(checkTV);
			}
			else if(activity==TutorActivity.activity) {
				TutorActivity.setSelections(checkTV);
			}
			else if(activity==WorkShopAndEventActivity.activity) {
				WorkShopAndEventActivity.setSelections(checkTV);
			}
			else if(activity==ShopActivity.activity) {
				ShopActivity.setSelections(checkTV);
			}
			}
		});
		
		ImageButton close_btn = (ImageButton) layout.findViewById(R.id.popup_ib_closePopup);
		close_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pw.dismiss();
			}
		});

		// provide the source layout for drop-down
		pw.setContentView(layout);

		// anchor the drop-down to bottom-left corner of 'tv'
		pw.showAsDropDown(tv);
//		pw.showAtLocation(tv.getRootView(), Gravity.CENTER, 0, 0);
		
		//Demo TextView
		TextView textView = new TextView(activity);

		// populate the drop-down list
//		items.add(0, activity.getString(R.string.select_all));
		final ListView list = (ListView) layout.findViewById(R.id.dropDownList);
		adapter = new DropDownListAdapter(activity, items,
				textView, tv); 
		list.setAdapter(adapter);
	}

	/******* Getter and setter *****/
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/******* Getter and setter *****/
	
	

}

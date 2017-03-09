package co.in.where2learn_new.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.navigation.CareerActivity;
import co.in.where2learn_new.navigation.CoachingActivity;
import co.in.where2learn_new.navigation.CollegeActivity;
import co.in.where2learn_new.navigation.HobbyClassesActivity;
import co.in.where2learn_new.navigation.ProfessionalCourseActivity;
import co.in.where2learn_new.navigation.SchoolActivity;
import co.in.where2learn_new.navigation.TutorActivity;
import co.in.where2learn_new.navigation.WorkShopAndEventActivity;
import co.in.where2learn_new.shop.ShopActivity;
import co.in.where2learn_new.util.DropDownListBaseClass;

public class DropDownListAdapter extends BaseAdapter {

	private String TAG = "DropDownListAdapter";
	
	private ArrayList<String> mListItems;
	private LayoutInflater mInflater;
	private TextView mSelectedItems;
	private TextView tv_selected;
	private static int selectedCount = 0;
	private static String firstSelected = "";
	private ViewHolder holder;
	private static String selected = ""; // shortened selected values
											// representation
	private Activity context;
	
	private ArrayList<Boolean> positionArray;

	public static String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		DropDownListAdapter.selected = selected;
	}

	public DropDownListAdapter(Activity context, ArrayList<String> items,
			TextView tv, TextView tv_selected) {
		mListItems = new ArrayList<String>();
		mListItems.addAll(items);
		mInflater = LayoutInflater.from(context);
		mSelectedItems = tv;
		this.tv_selected = tv_selected;
		
		this.context = context;
		
//		positionArray = new ArrayList<Boolean>(items.size());
	}

	@Override
	public int getCount() {
		return mListItems.size();
	}

	@Override
	public Object getItem(int id) {
		return null;
	}

	@Override
	public long getItemId(int id) {
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
//		positionArray = new ArrayList<Boolean>(mListItems.size());

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.drop_down_list_row, null);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.SelectOption);
			holder.chkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.chkbox.setOnCheckedChangeListener(null);
		}

		holder.chkbox.setFocusable(false);
		holder.tv.setText(mListItems.get(position));
		
		Log.e(TAG, "POsition : " + String.valueOf(position));
		Log.e(TAG, "Size : " + mListItems.size() + "\n" + mListItems.get(position));
		
		if(mListItems.get(position).contains("(Coming Soon...)"))
		{
			/*Log.e(TAG, "POsition : " + String.valueOf(position));
			Log.e(TAG, "Size : " + mListItems.size() + "\n" + mListItems.get(position));*/
			holder.chkbox.setVisibility(View.INVISIBLE);
			holder.chkbox.setChecked(false);
		}
		else
		{
			holder.chkbox.setVisibility(View.VISIBLE);
		}
			
//		Log.e(TAG, "Size : " + mListItems.size() + "\n" + mListItems.get(position));

		final int position1 = position;
		final ViewGroup parent1 = parent;

		// whenever the checkbox is clicked the selected values textview is
		// updated with new selected values
		holder.chkbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					setText(position1);				
										
					for(int i=0;i<parent1.getChildCount();i++) {
						ViewGroup view = (ViewGroup) parent1.getChildAt(i);
						
						for(int j=0;j<view.getChildCount();j++) {
							View view1 = view.getChildAt(j);
							
							if(view1 instanceof ListView) {
								Log.e(TAG, "LV");
							}
							else if(view1 instanceof LinearLayout) {
								Log.e(TAG, "LL");
							}
							else if(view1 instanceof RelativeLayout) {
								Log.e(TAG, "RL");
							}
							else if(view1 instanceof CheckBox) {
								Log.e(TAG, "CB");
								
									((CheckBox) view1).setChecked(DropDownListBaseClass.checkSelected[i]);
//									holder.chkbox.setChecked(DropDownListBaseClass.checkSelected[i]);
//									positionArray.set(i, DropDownListBaseClass.checkSelected[i]);
									DropDownListBaseClass.adapter.notifyDataSetChanged();							
							}
							else if(view1 instanceof TextView) {
								Log.e(TAG, ((TextView)view1).getText().toString());
							}
						}
						
					}					
//				}				
			}
		});

		if (DropDownListBaseClass.checkSelected[position])
			holder.chkbox.setChecked(true);
		else
			holder.chkbox.setChecked(false);
		return convertView;
	}

	/*
	 * Function which updates the selected values display and
	 * information(checkSelected[])
	 */
	private void setText(int position1) {
		
		/**
		 * Check for City
		 */
		/*if(tv_selected==CareerActivity.careerCityFilterTextView ||
				tv_selected==CoachingActivity.coachingCityFilterTextView ||
				tv_selected==CollegeActivity.collegeCityFilterTextView ||
				tv_selected==HobbyClassesActivity.hobbyCityFilterTextView ||
				tv_selected==ProfessionalCourseActivity.professionalCityFilterTextView ||
				tv_selected==SchoolActivity.schoolCityFilterTextView ||
				tv_selected==TutorActivity.tutorCityFilterTextView ||
				tv_selected==WorkShopAndEventActivity.workshopCityFilterTextView ||
				tv_selected==ShopActivity.shopCityFilterTextView) {
			Log.e(TAG, "City");
			if(selectedCount==1 && !DropDownListBaseClass.checkSelected[position1] == true) {
				Log.e(TAG, "selectedCount > 0");
				holder.chkbox.setChecked(false);
				Toast.makeText(context,
						context.getString(R.string.please_select_only_one_city),
						Toast.LENGTH_LONG).show();
				return;
			}
		}*/
		
		/**
		 * Check for Hobby->category
		 */
		/*if(context==HobbyClassesActivity.activity && tv_selected==HobbyClassesActivity.hobbyCateogory1FilterTextView) {
			Log.e(TAG, "Hobby->Category");
			if(selectedCount==1 && !DropDownListBaseClass.checkSelected[position1] == true) {
				Log.e(TAG, "selectedCount > 0");
				holder.chkbox.setChecked(false);
				Toast.makeText(context,
						context.getString(R.string.select_only_one_category),
						Toast.LENGTH_LONG).show();
				return;
			}
		}*/
		
		/**
		 * Check for School->Class
		 */
		/*if(context==SchoolActivity.activity && tv_selected==SchoolActivity.schoolAgeFilterTextView) {
			Log.e(TAG, "School->Class");
			if(selectedCount==1 && !DropDownListBaseClass.checkSelected[position1] == true) {
				Log.e(TAG, "selectedCount > 0");
				holder.chkbox.setChecked(false);
				Toast.makeText(context,
						context.getString(R.string.select_only_one_class),
						Toast.LENGTH_LONG).show();
				return;
			}
		}*/
		
		if (!DropDownListBaseClass.checkSelected[position1]) {
			DropDownListBaseClass.checkSelected[position1] = true;
			selectedCount++;
			if((selectedCount == DropDownListBaseClass.checkSelected.length-1) &&
					mListItems.get(0).equalsIgnoreCase(context.getString(R.string.select_all)) &&
					!DropDownListBaseClass.checkSelected[0]) {
				DropDownListBaseClass.checkSelected[0] = true;
				selectedCount++;
			}
		} else {
			DropDownListBaseClass.checkSelected[position1] = false;
			selectedCount--;
			if(mListItems.get(0).equalsIgnoreCase(context.getString(R.string.select_all)) &&
					DropDownListBaseClass.checkSelected[0]) {
				DropDownListBaseClass.checkSelected[0] = false;
				selectedCount--;
			}			
		}
		
		if(mListItems.get(position1).equalsIgnoreCase(context.getString(R.string.select_all)))
			setSelectAll(position1);

		if (selectedCount == 0) {
			mSelectedItems.setText(R.string.select_string);
		} else if (selectedCount == 1) {
			for (int i = 0; i < DropDownListBaseClass.checkSelected.length; i++) {
				if (DropDownListBaseClass.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected);
			setSelected(firstSelected);
		} else if (selectedCount > 1) {
			for (int i = 0; i < DropDownListBaseClass.checkSelected.length; i++) {
				if (DropDownListBaseClass.checkSelected[i] == true) {
					firstSelected = mListItems.get(i);
					break;
				}
			}
			mSelectedItems.setText(firstSelected + " & more");
			setSelected(firstSelected + " & more");
		}
	}
	
	private void setSelectAll(int position1) {
		selectedCount = 0;
			
		for(int i=0; i<DropDownListBaseClass.checkSelected.length; i++) {
			
				DropDownListBaseClass.checkSelected[i] = DropDownListBaseClass.checkSelected[position1];
			if(DropDownListBaseClass.checkSelected[i])
				selectedCount++;
		}
	}

	private class ViewHolder {
		TextView tv;
		CheckBox chkbox;
	}
}

package co.in.where2learn_new.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.bean.SearchBean;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.MatchDatesUtil;
import co.in.where2learn_new.util.StringUtil;

public class SearchAdapter extends BaseAdapter{

	private String TAG = getClass().getName();

	private ArrayList<SearchListItemBean> itemList;

	public Activity context;
	public LayoutInflater inflater;

	private static String myDate = "";
	private MatchDatesUtil matchDatesUtil;

	public SearchAdapter(Activity context,
			ArrayList<SearchListItemBean> itemList) {
		super();

		this.context = context;
		this.itemList = itemList;

		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		matchDatesUtil = new MatchDatesUtil();
		myDate = matchDatesUtil.setDateFill();
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public static class ViewHolder {


		RelativeLayout rlContainer;
		ImageView imgViewLogo;
		TextView txtViewCategory;
		TextView txtViewCity;
		TextView txtViewTitle;
		TextView txtViewAddress;
		TextView txtViewPhone;
		String classified_id;
		TextView txtViewDateAndTime;
		TextView txtViewDisabledMessage;

	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_search, null);

			holder.rlContainer = (RelativeLayout) convertView
					.findViewById(R.id.itemSearch_rl_parent);
			holder.imgViewLogo = (ImageView) convertView
					.findViewById(R.id.itemSearch_iv_icon);
			holder.txtViewCategory = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_category);
			holder.txtViewCity = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_city);			
			holder.txtViewTitle = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_title);
			holder.txtViewAddress = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_address);
			holder.txtViewPhone = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_phone);
			holder.txtViewDateAndTime = (TextView) convertView
					.findViewById(R.id.itemSearch_tv_Date);
			holder.txtViewDisabledMessage = (TextView) convertView
					.findViewById(R.id.itemSearch_txtViewDisabledMessage);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		SearchListItemBean bean = itemList.get(position);
		
		String phone = bean.getTxtViewPhoneNo();
		if(((phone.charAt(phone.length()-1)) == ',') || ((phone.charAt(0)) == ','))
			phone = phone.replace(",", "");
		
//		holder.imgViewLogo.setImageResource(bean.getImgViewIcon());
		holder.txtViewCategory.setText(bean.getTxtViewCategory());
		holder.txtViewCity.setText(bean.getTxtViewCity());
		holder.txtViewTitle.setText(bean.getTxtViewTitle());
		holder.txtViewAddress.setText(StringUtil.shrinkString(bean.getTxtViewDescrption(), AppConfig.MAX_STRING_DESCRIPTION_LENGTH));
		holder.txtViewPhone.setText(phone);
		holder.classified_id = bean.getClassified_id();
		
		if(holder.txtViewAddress.getText().toString().length()==0)
			holder.txtViewAddress.setVisibility(View.GONE);
		else if(holder.txtViewAddress.getText().toString().length()>0)
			holder.txtViewAddress.setVisibility(View.VISIBLE);			
		
		if(bean.getStartDateAndTime()!=null && bean.getEndDateAndTime()!=null)
		holder.txtViewDateAndTime.setText("Start Date: " + bean.getStartDateAndTime() +
				"\nEnd Date  : " + bean.getEndDateAndTime());
		
		holder.txtViewTitle.setTextColor(Color.parseColor("#000000"));


 /******* Only 4 Workshops *******/
		if(bean.getTxtViewCategory().equalsIgnoreCase("Workshops")) {
			holder.txtViewDateAndTime.setVisibility(View.VISIBLE);

			if(bean.getStartDateAndTime()!=null && bean.getEndDateAndTime()!=null)
				holder.txtViewDateAndTime.setText("Start Date: " + bean.getStartDateAndTime() +
						"\nEnd Date  : " + bean.getEndDateAndTime());
		/* Compare Date and act accordingly */
			String date = bean.getEndDateAndTime();
			Log.e(TAG,"Real Date2: " + date);
			SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat outputFormatter = new SimpleDateFormat(AppConfig.PROFILE_DATE_FORMAT);
			Date date1;
			try {
				date1 = inputFormatter.parse(date);
				date = outputFormatter.format(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Log.e(TAG, "Real Date1: " + myDate + "\nReal Date2: " + date);

			if(!matchDatesUtil.compareDates(myDate,date)) {
				holder.rlContainer.setBackgroundResource(R.drawable.old_paper_list_bg_11);
				holder.rlContainer.setAlpha(0.5f);
//			holder.rlContainer.setBackgroundColor(context.getResources().getColor(R.color.client_cyan));
				holder.txtViewDisabledMessage.setVisibility(View.VISIBLE);
			}
			else {
				holder.rlContainer.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
				holder.rlContainer.setAlpha(1.0f);
				holder.txtViewDisabledMessage.setVisibility(View.GONE);
			}
		}
		else {
			holder.txtViewDateAndTime.setVisibility(View.GONE);
		}
		
		/*if(context==CareerlListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#1999CE"));
		}
		else if(context==CoachinglListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#CDCC64"));
		}
		else if(context==CollegeListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#15B0B0"));
		}
		else if(context==HobbyListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#F3797A"));
		}
		else if(context==ProfessionalCourseslListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#F8A321"));
		}
		else if(context==SchoolListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#EB3A42"));
		}
		else if(context==TuitionListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#F26B22"));
		}
		else if(context==WorkShopListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#61C195"));
		}
		else if(context==ShopListActivity.activity) {
			holder.txtViewTitle.setTextColor(Color.parseColor("#7AC356"));
		}*/

		return convertView;
	}

}

package co.in.where2learn_new.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.bean.TalkListItemBean;

public class TalkListListViewCustomAdapter extends BaseAdapter {

	private ArrayList<TalkListItemBean> itemList;

	public Activity context;
	public LayoutInflater inflater;

	public TalkListListViewCustomAdapter(Activity context,
			ArrayList<TalkListItemBean> itemList) {
		super();

		this.context = context;
		this.itemList = itemList;

		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

		ImageView imgViewLogo;
		TextView txtViewName;
		TextView txtViewDateAndTime;
		TextView txtViewMessage;
		String like;

	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.talk_list_items, null);

			holder.imgViewLogo = (ImageView) convertView
					.findViewById(R.id.imgViewIcon);
			holder.txtViewName = (TextView) convertView
					.findViewById(R.id.txtViewName);
			holder.txtViewDateAndTime = (TextView) convertView
					.findViewById(R.id.txtViewDateAndTime);
			holder.txtViewMessage = (TextView) convertView
					.findViewById(R.id.txtViewMessage);

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		TalkListItemBean bean = (TalkListItemBean) itemList.get(position);

//		holder.imgViewLogo.setImageResource(bean.getImgViewIcon());
		holder.txtViewName.setText(bean.getName());
		holder.txtViewDateAndTime.setText(bean.getDateAndTime());
		holder.txtViewMessage.setText(bean.getMessage());
		holder.like = bean.getLike();

		return convertView;
	}

}

package co.in.where2learn_new.navigation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.adapter.NotificationListListViewCustomAdapter;
import co.in.where2learn_new.bean.NotificationListItemBean;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.NotificationAndNewsIntentUtil;

public class NewsActivity extends Activity {

	private Activity activity;

	private ListView listView1;
	private ListAdapter adapter;

	private OnItemClickListener listView1Listener;
	private ArrayList<NotificationListItemBean> itemList;

	private ImageView backImageView;

	private View.OnClickListener backOnClickListener;

	private NotificationAndNewsIntentUtil notificationAndNewsIntentUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		activity = this;

		getActionBar().hide();
		changeFooter();

		initListener();
		initComponent();

	}

	private void initListener() {

		listView1Listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {

				NotificationListItemBean itemBean = (NotificationListItemBean) adapterView
						.getItemAtPosition(position);

				notificationAndNewsIntentUtil.intentToDetailsPage(itemBean);

			}
		};

		backOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

	}

	private void initComponent() {

		itemList = AppConfig.NotificationOrNewsItemList;
		notificationAndNewsIntentUtil = new NotificationAndNewsIntentUtil(
				activity);

		listView1 = (ListView) findViewById(R.id.activity_news_listView1);
		listView1.setScrollbarFadingEnabled(false);
		
		backImageView = (ImageView) findViewById(R.id.news_backImageView);

		adapter = new NotificationListListViewCustomAdapter(activity, itemList);		
		
		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listView1Listener);
		backImageView.setOnClickListener(backOnClickListener);
		

		((RelativeLayout)findViewById(R.id.footer_rl_location)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.location)).setVisibility(View.GONE);

		checkNoItemCondition();

	}

	private void checkNoItemCondition() {

		if (itemList.size() == 0) {

			Toast.makeText(activity,
					activity.getString(R.string.no_data_found),
					Toast.LENGTH_LONG).show();

			onBackPressed();
		}

	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
}

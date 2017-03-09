package co.in.where2learn_new.navigation;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.adapter.TalkListListViewCustomAdapter;
import co.in.where2learn_new.bean.TalkListItemBean;
import co.in.where2learn_new.businesslogic.MainActivityBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.CheckInternet;

public class TalksActivity extends Activity {

	private Activity activity;

	private ListView listView1;
	private ListAdapter adapter;

	private OnItemClickListener talk_listView1Listener;
	private ArrayList<TalkListItemBean> itemList;

	public static EditText messageEditText;
	private ImageView sendImageView;

	private ImageView talk_backImageView;

	private View.OnClickListener backOnClickListener;
	private View.OnClickListener sendOnClickListener;

	private MainActivityBusinessLogic mainActivityBusinessLogic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_talks);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		talk_listView1Listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		};

		sendOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
				{
					String messageString = getMessageString();
					mainActivityBusinessLogic.invokeSendTalkMessage(messageString);
					initListView();
				}
				else
				{
					Toast.makeText(activity,
							activity.getString(R.string.no_internet_found),
							Toast.LENGTH_LONG).show();
				}
			}
		};

		backOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

	}

	protected String getMessageString() {

		return messageEditText.getText().toString();
	}

	private void initComponent() {

		mainActivityBusinessLogic = new MainActivityBusinessLogic(activity);

		itemList = AppConfig.TalkItemList;

		listView1 = (ListView) findViewById(R.id.activity_talks_listView1);
		listView1.setScrollbarFadingEnabled(false);

		messageEditText = (EditText) findViewById(R.id.activity_talk_messageEditText);
		sendImageView = (ImageView) findViewById(R.id.activity_talk_sendImageView);

		talk_backImageView = (ImageView) findViewById(R.id.talk_backImageView);

		initListView();

		listView1.setOnItemClickListener(talk_listView1Listener);
		sendImageView.setOnClickListener(sendOnClickListener);
		talk_backImageView.setOnClickListener(backOnClickListener);
		

		((RelativeLayout)findViewById(R.id.footer_rl_location)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.location)).setVisibility(View.GONE);

		checkNoItemCondition();

	}

	private void checkNoItemCondition() {

		if (itemList.size() == 0) {

			Toast.makeText(activity,
					activity.getString(R.string.no_data_found),
					Toast.LENGTH_LONG).show();

//			onBackPressed();
		}

	}

	private void initListView() {

		adapter = new TalkListListViewCustomAdapter(activity, itemList);
		listView1.setAdapter(adapter);

	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
}

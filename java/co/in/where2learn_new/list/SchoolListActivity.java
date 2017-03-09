package co.in.where2learn_new.list;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import co.in.where2learn_new.R;
import co.in.where2learn_new.adapter.SearchListListViewCustomAdapter;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.businesslogic.FilterListBusinessLogic;
import co.in.where2learn_new.businesslogic.FinalDetailsBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.SuggestionDialog;

public class SchoolListActivity extends Activity {

	protected String TAG = this.getClass().getName();
	public static Activity activity;
	
	private TextView school_list_title_name;
	
	private TextView load_moreTextView;
	
	private TextView filters_selected;
	
	private TextView listings_count;

	private SchoolListActivity schoolListActivity;

	private ListView activity_search_list_listView1;
	private ListAdapter adapter;

	private OnItemClickListener search_listView1Listener;
	private AbsListView.OnScrollListener search_list_listView1OnScrollListener;
	private ArrayList<SearchListItemBean> itemList;

	private FinalDetailsBusinessLogic finalDetailsBusinessLogic;
	private ImageView search_list_searchImageView;

	private ImageView search_list_title_nameImageView;

	private View.OnClickListener load_moreOnClickListener;
	private View.OnClickListener search_list_searchOnClickListener;

	private FilterListBusinessLogic filterListBusinessLogic;

	private int preLast = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_list);

		activity = this;
		schoolListActivity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();

	}

	private void initListener() {

		load_moreOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				loadNext();

			}
		};

		search_list_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		search_listView1Listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view,
					int position, long id) {

				SearchListItemBean bean = (SearchListItemBean) adapterview
						.getItemAtPosition(position);

				finalDetailsBusinessLogic.invokeSchoolDetailsPage(bean);

			}
		};

		search_list_listView1OnScrollListener = new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

					final int lastItem = firstVisibleItem + visibleItemCount;
					if (lastItem == totalItemCount) {
						if (preLast != lastItem) { // to avoid multiple calls
													// for last item
							Log.i(TAG, "Last");
							preLast = lastItem;
							loadNext();
						}
					}
			}

		};

	}

	protected void loadNext() {

		filterListBusinessLogic.callNextSchoolRange(schoolListActivity);

	}

	@SuppressLint("NewApi")
	private void initComponent() {

		finalDetailsBusinessLogic = new FinalDetailsBusinessLogic(activity);
		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		search_list_searchImageView = (ImageView) findViewById(R.id.search_list_searchImageView);
		search_list_title_nameImageView = (ImageView) findViewById(R.id.search_list_title_nameImageView);
		load_moreTextView = (TextView) findViewById(R.id.activity_search_load_moreTextView);

		/*search_list_title_nameImageView.setImageDrawable(activity
				.getDrawable(R.drawable.icon_school_img));*/
		school_list_title_name = (TextView) findViewById(R.id.search_list_title_name);
		
		filters_selected = (TextView) findViewById(R.id.searchList_tv_filtersSelected);
		filters_selected.setMovementMethod(new ScrollingMovementMethod());
		filters_selected.setSingleLine();		
		filters_selected.setText(Html.fromHtml(AppConfig.FiltersSelected));
		
		listings_count = (TextView) findViewById(R.id.searchList_tv_count);
		listings_count.setText(activity.getString(R.string.school_list_available) + " - " + AppConfig.COUNT);

		school_list_title_name.setText(activity
				.getString(R.string.school_list_classes_name));

		activity_search_list_listView1 = (ListView) findViewById(R.id.activity_search_list_listView1);
		activity_search_list_listView1.setScrollbarFadingEnabled(false);
		initListView();

		load_moreTextView.setOnClickListener(load_moreOnClickListener);

		search_list_searchImageView
				.setOnClickListener(search_list_searchOnClickListener);

		activity_search_list_listView1
				.setOnItemClickListener(search_listView1Listener);
		activity_search_list_listView1
				.setOnScrollListener(search_list_listView1OnScrollListener);
		
		((RelativeLayout)findViewById(R.id.footer_rl_location)).setVisibility(View.GONE);
		((TextView)findViewById(R.id.location)).setVisibility(View.GONE);

		suggestDialog();
	}

	public void initListView() {
		itemList = AppConfig.SearchedItemList;
		adapter = new SearchListListViewCustomAdapter(activity, itemList);
		activity_search_list_listView1.setAdapter(adapter);
		
		activity_search_list_listView1.setSelection(preLast);

	}

	private void suggestDialog() {

		SuggestionDialog dialog = new SuggestionDialog(activity);
		dialog.showDialog(itemList);

	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		startActivity(new Intent(activity, SchoolActivity.class));
		finish();
	}

}

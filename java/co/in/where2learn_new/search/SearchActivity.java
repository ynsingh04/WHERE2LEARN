package co.in.where2learn_new.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import co.in.where2learn_new.R;
import co.in.where2learn_new.adapter.SearchAdapter;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.businesslogic.FilterListBusinessLogic;
import co.in.where2learn_new.businesslogic.FinalDetailsBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.CheckInternet;
import co.in.where2learn_new.util.MatchDatesUtil;
import co.in.where2learn_new.util.SuggestionDialog;

public class SearchActivity extends Activity{
	
	private String TAG = getClass().getName();
	
	private Activity activity;
	
	private ListView lv;
	private Button go;
	private ImageView back_button;
	
	private EditText et_searchText;
	private Spinner sp_selectedDomain;
	private Spinner sp_selectedCity;
	
	private View.OnClickListener go_listener;
	
	private ArrayList<SearchListItemBean> itemList;
	private ListAdapter adapter;
	private int preLast = 0;
	
	private String searchText = "";
	private String selectedDomain;
	private String selectedCity;
	
	private FilterListBusinessLogic filterListBusinessLogic;
	
	private AbsListView.OnScrollListener search_list_listView1OnScrollListener;
	
	private OnItemClickListener search_listView1Listener;
	
	private View.OnClickListener back_listener;
	
	public static SearchActivity searchListActivity;
	
	private SuggestionDialog dialog;
	
	private FinalDetailsBusinessLogic finalDetailsBusinessLogic;
	
	public static int COUNT = 0;
	
	private ArrayList<String> cityFilter;

	private static String myDate = "";
	private MatchDatesUtil matchDatesUtil;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		activity = this;
		searchListActivity = this;
		
		getActionBar().hide();	
		
		initListener();
		
		initComponent();
		
		setCities();
	}
	
	/* Initialize components */
	private void initComponent() {		
		filterListBusinessLogic = new FilterListBusinessLogic(activity);
		finalDetailsBusinessLogic = new FinalDetailsBusinessLogic(activity);
		
		lv = (ListView) findViewById(R.id.search_lv_searchList);
		go = (Button) findViewById(R.id.search_btn_go);
		back_button = (ImageView) findViewById(R.id.search_iv_back);
		
		et_searchText = (EditText) findViewById(R.id.search_et_searchText);
		sp_selectedDomain = (Spinner) findViewById(R.id.search_sp_selectDomain);
		sp_selectedCity = (Spinner) findViewById(R.id.search_sp_selectCity);
		
		dialog = new SuggestionDialog(activity);
		
		go.setOnClickListener(go_listener);
		back_button.setOnClickListener(back_listener);
		lv.setOnItemClickListener(search_listView1Listener);
		lv.setOnScrollListener(search_list_listView1OnScrollListener);

		matchDatesUtil = new MatchDatesUtil();
		myDate = matchDatesUtil.setDateFill();
		
	}
	
	/* Initialize listeners */
	private void initListener() {
		
		/* Go button listener */
		go_listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setDataToSend();
				hideSoftKeyboard();
				
				if(CheckInternet.getConnectivityStatus(activity)==CheckInternet.TYPE_NOT_CONNECTED) {
					dialog.initAlertDialog(activity.getString(R.string.no_internet_found));
					return;
				}
				
				if(searchText.length()==0) {
					dialog.initAlertDialog(activity.getString(R.string.search_enter_text));
					et_searchText.setText("");
				}
				else {
					preLast = 0;
					lv.removeAllViewsInLayout();
					filterListBusinessLogic.invokeSearchFilteredList(searchText,
							selectedDomain,selectedCity);
				}				
			}
		};
		
		back_listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		};
		
		search_listView1Listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view,
					int position, long id) {

				SearchListItemBean bean = (SearchListItemBean) adapterview
						.getItemAtPosition(position);


				if(selectedDomain.equalsIgnoreCase("Workshops")) {
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

					if(matchDatesUtil.compareDates(myDate,date)) {
						finalDetailsBusinessLogic.invokeCareerDetailsPage(bean);
					}
				}
				else {
					finalDetailsBusinessLogic.invokeCareerDetailsPage(bean);
				}
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
				if (lastItem == totalItemCount && lastItem<COUNT) {
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

		filterListBusinessLogic.callNextSearchRange(searchListActivity);

	}
	
	public void initListView() {

		itemList = AppConfig.SearchedItemList;
		adapter = new SearchAdapter(activity, itemList);
		lv.setAdapter(adapter);
		
		lv.setSelection(preLast);

	}
	
	private void setDataToSend() {
		searchText = et_searchText.getText().toString().trim();
		selectedDomain = sp_selectedDomain.getSelectedItem().toString().trim();
		selectedCity = sp_selectedCity.getSelectedItem().toString().trim();
		
		if(selectedDomain.equals("Select Category (Optional)")) {
			selectedDomain = "";
		}
		else if(selectedDomain.equals("Hobby Classes")) {
			selectedDomain = "Hobby";
		}
		else if(selectedDomain.equals("Workshop/Events")) {
			selectedDomain = "Workshops";
		}
		
		if(selectedCity.equals("Select City (Optional)")) {
			selectedCity = "";
		}
	}
	
	private void setCities() {
		cityFilter = new ArrayList<String>();
		cityFilter.add("Select City (Optional)");
		for(String city:AppConfig.CityFilterModel.getCityFilter()) {
			cityFilter.add(city);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, cityFilter);
		sp_selectedCity.setAdapter(adapter);
		
		adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(activity.getResources().getStringArray(R.array.domain_names)));
		sp_selectedDomain.setAdapter(adapter);
	}
	
	public void hideSoftKeyboard() {
	    if(getCurrentFocus()!=null) {
	        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	    }
	}
}

package co.in.where2learn_new.navigation;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FilterListBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.DropDownListBaseClass;

/**
 * @author chitresh.verma
 *         <p>
 *         This Activity handles the workshop and event.
 *         </p>
 */
public class WorkShopAndEventActivity extends Activity {

	public static Activity activity;
	private FilterListBusinessLogic filterListBusinessLogic;
	private ImageView workshopOKImageView;
	private ImageView workshop_searchImageView;
	private View.OnClickListener workshopOKOnClickListener;
	private View.OnClickListener workshop_searchOKOnClickListener;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedCalendarFilterTextView;

	private ImageView workshopCityFilterImageView;
	private ImageView workshopCalendarMonthFilterImageView;

	public static TextView workshopCityFilterTextView;
	private static TextView workshopCalendarMonthFilterTextView;

	private Spinner workshopCalendarYearFilterSpinner;

	private View.OnClickListener workshopCityFilterOnClickListener;
	private View.OnClickListener workshopCalendarFilterOnClickListener;

	 

	private DropDownListBaseClass dropDownListBaseClass;

	private ArrayAdapter<String> calendarYearFilterSpinnerAdapter;
	private OnItemSelectedListener workshopCalendarYearOnItemSelectedListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_shop_and_event);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		workshop_searchOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		workshopCityFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CityFilterModel!=null)
				{
					if(AppConfig.CityFilterModel.getCityFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CityFilterModel.getCityFilter().size()==0)
						{
							Toast.makeText(
									activity,
									activity.getString(R.string.no_data_found),
									Toast.LENGTH_LONG).show();
							return;
						}
					}
				}
				else
				{
					Toast.makeText(
							activity,
							activity.getString(R.string.no_data_found),
							Toast.LENGTH_LONG).show();
					return;
				}
				/** End Check data presence */

				DropDownListBaseClass.checkSelected = checkSelectedCityFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CityFilterModel.getCityFilter(),
						workshopCityFilterTextView);

			}
		};

		 

		/*** Calendar ***/

		workshopCalendarFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				DropDownListBaseClass.checkSelected = checkSelectedCalendarFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.WorkshopFilterModel.getCalendarMonthFilter(),
						workshopCalendarMonthFilterTextView);

			}
		};

		 

		/**** Calendar **/

		workshopCalendarYearOnItemSelectedListener = new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				TextView selectedText = (TextView) parent.getChildAt(0);
				if (selectedText != null) {
					selectedText.setTextColor(Color.WHITE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		};

		workshopOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String calendarMonthList = getCalendarMonth();
				String calendarYearList = getCalendarYear();
				
				if(cityList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}
				else if(cityList.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(calendarMonthList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_month),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.workshop_calendar_month) + "</b>" + "->" + calendarMonthList
							+ "; <b>" + activity.getString(R.string.workshop_calendar_year) + "</b>" + "->" + calendarYearList;
					
					filterListBusinessLogic.invokeWorkShopFilteredList(cityList,
							calendarMonthList, calendarYearList); 
				}
			}
		};

	}

	protected String getCity() {
		String city = "";

		if(checkSelectedCityFilterTextView == null){
			return "";
		}

		for (int i = 0; i < AppConfig.CityFilterModel.getCityFilter().size(); i++) {
			/*if (checkSelectedCityFilterTextView[i] == true) {
				if (city.length() == 0) {
					city = AppConfig.CityFilterModel.getCityFilter().get(i);
				} else {
					city = "";
					break;
				}*/
			if (checkSelectedCityFilterTextView[i] == true) {
				if (city.length() == 0) {
					city = AppConfig.CityFilterModel.getCityFilter().get(i);
				} else {
					city += "," + AppConfig.CityFilterModel.getCityFilter().get(i);
				}
			}
		}

		return city;
	}

	protected String getCalendarMonth() {
		String month = "";
		
		if(checkSelectedCalendarFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.WorkshopFilterModel
				.getCalendarMonthFilter().size(); i++) {
			if (checkSelectedCalendarFilterTextView[i] == true) {
				if (month.length() == 0) {
					month = AppConfig.WorkshopFilterModel
							.getCalendarMonthFilter().get(i);
				} else {
					month += "," + AppConfig.WorkshopFilterModel
							.getCalendarMonthFilter().get(i);
				}
			}
		}

		return month;
	}

	protected String getCalendarYear() {
		return workshopCalendarYearFilterSpinner.getSelectedItem().toString();
	}

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		buildMonthAndYear();
		initCheckSelection();
		
		((ScrollView) findViewById(R.id.workshop_sv_container)).setScrollbarFadingEnabled(false);

		workshop_searchImageView = (ImageView) findViewById(R.id.workshop_searchImageView);

		workshopCityFilterTextView = (TextView) findViewById(R.id.workshopCityFilterTextView);
		workshopCalendarMonthFilterTextView = (TextView) findViewById(R.id.workshopCalendarMonthFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		workshopCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());
		
		workshopCalendarYearFilterSpinner = (Spinner) findViewById(R.id.workshopCalendarYearFilterSpinner);

		workshopCityFilterImageView = (ImageView) findViewById(R.id.workshopCityFilterImageView);
		workshopCalendarMonthFilterImageView = (ImageView) findViewById(R.id.workshopCalendarMonthFilterImageView);

		initSpinner();
		workshopCalendarYearFilterSpinner
				.setAdapter(calendarYearFilterSpinnerAdapter);
		workshopCalendarYearFilterSpinner
				.setOnItemSelectedListener(workshopCalendarYearOnItemSelectedListener);
 


		workshopCityFilterTextView.setOnClickListener(workshopCityFilterOnClickListener);
		workshopCalendarMonthFilterTextView.setOnClickListener(workshopCalendarFilterOnClickListener);
		
		workshopCityFilterImageView
				.setOnClickListener(workshopCityFilterOnClickListener);
		workshopCalendarMonthFilterImageView
				.setOnClickListener(workshopCalendarFilterOnClickListener);

		workshopOKImageView = (ImageView) findViewById(R.id.workshopOKImageView);

		workshopOKImageView.setOnClickListener(workshopOKOnClickListener);
		workshop_searchImageView
				.setOnClickListener(workshop_searchOKOnClickListener);
		
		workshopCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		workshopCityFilterTextView.setSingleLine();
		workshopCalendarMonthFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		workshopCalendarMonthFilterTextView.setSingleLine();

	}

	private void initSpinner() {

		calendarYearFilterSpinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				AppConfig.WorkshopFilterModel.getCalendarYearFilter());
		calendarYearFilterSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	}

	protected void buildMonthAndYear() {

		ArrayList<String> monthList = AppConfig.WorkshopFilterModel
				.getCalendarMonthFilter();
		monthList.clear();
		/*for (int i = 1; i < 13; i++) {
			monthList.add(i + "");
		}*/
		
		monthList.add("Jan");
		monthList.add("Feb");
		monthList.add("Mar");
		monthList.add("Apr");
		monthList.add("May");
		monthList.add("Jun");
		monthList.add("Jul");
		monthList.add("Aug");
		monthList.add("Sep");
		monthList.add("Oct");
		monthList.add("Nov");
		monthList.add("Dec");

		ArrayList<String> yearList = AppConfig.WorkshopFilterModel
				.getCalendarYearFilter();
		yearList.clear();
		int year = Calendar.getInstance().get(Calendar.YEAR);

		for (int i = 0; i < 2; i++) {
			yearList.add((year + i) + "");
		}

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		checkSelectedCalendarFilterTextView = new boolean[AppConfig.WorkshopFilterModel
				.getCalendarMonthFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCalendarFilterTextView.length; i++) {
			checkSelectedCalendarFilterTextView[i] = false;
		}

	}

	private void changeFooter() {
		ChangeFooter changeFooter = new ChangeFooter(activity);
		changeFooter.change();

	}
	
	/*@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);		
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}
	}*/
	
	public void changeCity(){
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}
	}
	
	public static void setSelections(TextView tv){
		if(tv == workshopCityFilterTextView) {
			if(new WorkShopAndEventActivity().getCity()!="")
			workshopCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new WorkShopAndEventActivity().getCity());
			else
				workshopCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == workshopCalendarMonthFilterTextView) {
			if(new WorkShopAndEventActivity().getCalendarMonth()!="")
			workshopCalendarMonthFilterTextView.setText(activity.getString(R.string.workshop_calendar_month) + " - " + new WorkShopAndEventActivity().getCalendarMonth());
			else
				workshopCalendarMonthFilterTextView.setText(activity.getString(R.string.workshop_calendar_month));
		}
	}
}

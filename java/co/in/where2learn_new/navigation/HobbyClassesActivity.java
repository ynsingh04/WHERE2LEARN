package co.in.where2learn_new.navigation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FilterListBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.model.HobbyFilterCategoryModel;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.DropDownListBaseClass;

public class HobbyClassesActivity extends Activity {

	private String TAG = this.getClass().getName();
	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedAgeFilterTextView;
	public static boolean[] checkSelectedSexFilterTextView;
	public static boolean[] checkSelectedCategory1FilterTextView;
	public static boolean[] checkSelectedCategory2FilterTextView;
	public static boolean[] checkSelectedAreaFilterTextView;
	public static boolean[] checkSelectedTypeFilterTextView;

	public static Activity activity;
	private FilterListBusinessLogic filterListBusinessLogic;
	private ImageView hobbyOKImageView;
	private View.OnClickListener hobbyOKOnClickListener;

	private ImageView hobby_searchImageView;
	private View.OnClickListener hobby_searchOnClickListener;

	private ImageView hobbyCityFilterImageView;
	private ImageView hobbyAgeFilterImageView;
	private ImageView hobbySexFilterImageView;
	private ImageView hobbyCategory1FilterImageView;
	private ImageView hobbyCategory2FilterImageView;
	private ImageView hobbyAreaFilterImageView;
	private ImageView hobbyTypeFilterImageView;

	public static TextView hobbyCityFilterTextView;
	private static TextView hobbyAgeFilterTextView;
	private static TextView hobbySexFilterTextView;
	public static TextView hobbyCateogory1FilterTextView;
	private static TextView hobbyCategory2FilterTextView;
	public static TextView hobbyAreaFilterTextView;
	public static TextView hobbyTypeFilterTextView;

	private View.OnClickListener hobbyCityFilterOnClickListener;
	private View.OnClickListener hobbyAgeFilterOnClickListener;
	private View.OnClickListener hobbySexFilterOnClickListener;
	private View.OnClickListener hobbyCategory1FilterOnClickListener;
	private View.OnClickListener hobbyCategory2FilterOnClickListener;
	private View.OnClickListener hobbyAreaFilterOnClickListener;
	private View.OnClickListener hobbyTypeFilterOnClickListener;

	private DropDownListBaseClass dropDownListBaseClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hobby_classes);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		hobby_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		hobbyCityFilterOnClickListener = new View.OnClickListener() {

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
						hobbyCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new HobbyClassesActivity().getArea()!="")
					hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new HobbyClassesActivity().getArea());
					else
						hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		hobbyAgeFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.HobbyFilterModel!=null)
				{
					if(AppConfig.HobbyFilterModel.getAge()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.HobbyFilterModel.getAge().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedAgeFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.HobbyFilterModel.getAge(),
						hobbyAgeFilterTextView);

			}
		};

		hobbySexFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.HobbyFilterModel!=null)
				{
					if(AppConfig.HobbyFilterModel.getSex()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.HobbyFilterModel.getSex().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedSexFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.HobbyFilterModel.getSex(),
						hobbySexFilterTextView);

			}
		};

		hobbyCategory1FilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.HobbyFilterModel!=null)
				{
					if(AppConfig.HobbyFilterModel.getCategory1()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.HobbyFilterModel.getCategory1().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedCategory1FilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.HobbyFilterModel.getCategory1(),
						hobbyCateogory1FilterTextView);
				
				checkSelectedCategory2FilterTextView = null;
				
				hobbyCategory2FilterTextView.setText(activity.getString(R.string.hobby_category2));

			}
		};

		hobbyCategory2FilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String category = getCategory1();
				if(category.length()==0) {
					Toast.makeText(
							activity,
							activity.getString(R.string.select_a_category),
							Toast.LENGTH_LONG).show();
					return;
				}
				if(category.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_only_one_category),
							Toast.LENGTH_LONG).show();
					return;
				}
				
				initSubCategory();
				
				/** Check data presence */
				if(AppConfig.HobbyFilterModel!=null)
				{
					if(AppConfig.HobbyFilterModel.getCategory2()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_available),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.HobbyFilterModel.getCategory2().size()==0)
						{
							Toast.makeText(
									activity,
									activity.getString(R.string.no_data_available),
									Toast.LENGTH_LONG).show();
							return;
						}
					}
				}
				else
				{
					Toast.makeText(
							activity,
							activity.getString(R.string.no_data_available),
							Toast.LENGTH_LONG).show();
					return;
				}
				/** End Check data presence */

//				initSubCategory();

				DropDownListBaseClass.checkSelected = checkSelectedCategory2FilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.HobbyFilterModel.getCategory2(),
						hobbyCategory2FilterTextView);

			}
		};

		hobbyAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();
				String type = getType();			

				if (city.length() != 0 && !city.contains(",")) {					
					if(type.contains(AppConfig.HomeString))	{
						Toast.makeText(
								activity,
								activity.getString(R.string.area_option_is_not_available_for_home_tutor) + " " + AppConfig.HomeString,
								Toast.LENGTH_LONG).show();
					}
					else {
						filterListBusinessLogic.getAreaList(city,
								hobbyAreaFilterTextView);
					}
				} else if(city.contains(",")){
					Toast.makeText(
							activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(city.length() == 0){
					Toast.makeText(
							activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}

			}
		};
		
		/*** Type ***/

		hobbyTypeFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.HobbyFilterModel!=null)
				{
					if(AppConfig.HobbyFilterModel.getType()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.HobbyFilterModel.getType().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedTypeFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.HobbyFilterModel.getType(),
						hobbyTypeFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		/*** End Type **/

		hobbyOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String typeList = getType();
				String ageList = getAge();
				String sexList = getSex();
				String category1List = getCategory1();
				String category2List = getCategory2();
				String areaList = getArea();
				
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
				else if(category1List.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_category),
							Toast.LENGTH_LONG).show();
				}
				else if(category1List.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_only_one_category),
							Toast.LENGTH_LONG).show();
				}
				else if(category2List.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_sub_category),
							Toast.LENGTH_LONG).show();
				}
				else if(typeList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_type),
							Toast.LENGTH_LONG).show();
				}
				else if(typeList.contains(AppConfig.HomeString) &&
						!areaList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.area_option_is_not_available_for_home_tutor) + " " + AppConfig.HomeString,
							Toast.LENGTH_LONG).show();
				}
				else if(sexList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_sex),
							Toast.LENGTH_LONG).show();
				}
				/*else if(ageList.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_age),
							Toast.LENGTH_LONG).show();
				}*/
				else if(areaList.equalsIgnoreCase("") &&
						!cityList.contains(",") &&
						!typeList.contains(AppConfig.HomeString)) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.hobby_category1) + "</b>" + "->" + category1List
							+ "; <b>" + activity.getString(R.string.hobby_category2) + "</b>" + "->" + category2List
							+ "; <b>" + activity.getString(R.string.tutor_type) + "</b>" + "->" + typeList
							+ "; <b>" + activity.getString(R.string.hobby_sex) + "</b>" + "->" + sexList
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + areaList;
					
					filterListBusinessLogic.invokeHobbyFilteredList(cityList,
							ageList, sexList, category1List, category2List,
							areaList, typeList); 
				}
			}
		};

	}

	protected synchronized void initSubCategory() {

		List<String> masterIDList = new ArrayList<String>();

		for (int i = 0; i < AppConfig.HobbyFilterModel.getCategory1().size(); i++) {
			if (checkSelectedCategory1FilterTextView[i] == true) {
				String masterID = AppConfig.HobbyFilterModel
						.getCategory1Complete().get(i).getMaster_Id();
				masterIDList.add(masterID);
			}
		}

		masterIDList = new ArrayList<String>(new LinkedHashSet<String>(
				masterIDList));

		AppConfig.HobbyFilterModel.getCategory2().clear();
//		AppConfig.HobbyFilterModel.getCategory2().add(activity.getString(R.string.select_all));

		for (int i = 0; i < AppConfig.HobbyFilterModel.getCategory2Complete()
				.size(); i++) {

			HobbyFilterCategoryModel filterCategoryModel = AppConfig.HobbyFilterModel
					.getCategory2Complete().get(i);

			for (Iterator<String> iterator = masterIDList.iterator(); iterator
					.hasNext();) {
				String masterID = (String) iterator.next();

				Log.i(TAG, filterCategoryModel.getSearch_Id() + masterID);

				if (filterCategoryModel.getSearch_Id().equalsIgnoreCase(
						masterID)) {
					AppConfig.HobbyFilterModel.getCategory2().add(
							filterCategoryModel.getValue());
				}

			}

		}

		initCheckBoxSubCategory();

	}

	private void initCheckBoxSubCategory() {

		checkSelectedCategory2FilterTextView = new boolean[AppConfig.HobbyFilterModel
				.getCategory2().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCategory2FilterTextView.length; i++) {
			checkSelectedCategory2FilterTextView[i] = false;
		}

	}
	
	protected String getType() {
		String type = "";

		if(checkSelectedTypeFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.HobbyFilterModel.getType().size(); i++) {
			if (checkSelectedTypeFilterTextView[i] == true) {
				if(type.length() == 0) {
					type = AppConfig.HobbyFilterModel.getType().get(i);
				} else {
					type += "," + AppConfig.HobbyFilterModel.getType().get(i);
				}
			}
		}

		return type;
	}

	protected String getAge() {
		String age = "";
		
		if(checkSelectedAgeFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.HobbyFilterModel.getAge().size(); i++) {
			if (checkSelectedAgeFilterTextView[i] == true) {
				
				if(AppConfig.HobbyFilterModel.getAge().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					age = AppConfig.HobbyFilterModel.getAge().get(i);
					break;
				}
				
				if(age.length() == 0) {
					age = AppConfig.HobbyFilterModel.getAge().get(i);
				} else {
					age += "," + AppConfig.HobbyFilterModel.getAge().get(i);
				}
			}
		}

		return age;
	}

	protected String getSex() {
		String sex = "";
		
		if(checkSelectedSexFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.HobbyFilterModel.getSex().size(); i++) {
			if (checkSelectedSexFilterTextView[i] == true) {
				if(sex.length() == 0) {
					sex = AppConfig.HobbyFilterModel.getSex().get(i);
				} else {
					sex += "," + AppConfig.HobbyFilterModel.getSex().get(i);
				}
			}
		}

		return sex;
	}

	protected String getCategory1() {

		String category1 = "";
		
		if(checkSelectedCategory1FilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.HobbyFilterModel.getCategory1().size(); i++) {
			if (checkSelectedCategory1FilterTextView[i] == true) {
				if(category1.length() == 0) {
					category1 = AppConfig.HobbyFilterModel.getCategory1().get(i);
				} else {
					category1 += "," + AppConfig.HobbyFilterModel.getCategory1().get(i);
				}
			}
		}

		return category1;
	}

	protected String getCategory2() {

		String category2 = "";
		
		if(checkSelectedCategory2FilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.HobbyFilterModel.getCategory2().size(); i++) {
			if (checkSelectedCategory2FilterTextView[i] == true) {
				if(category2.length() == 0) {
					category2 = AppConfig.HobbyFilterModel.getCategory2().get(i);
				} else {
					category2 += "," + AppConfig.HobbyFilterModel.getCategory2().get(i);
				}
			}
		}

		return category2;

	}

	public String getArea() {
		String area = "";
		
		if(checkSelectedAreaFilterTextView!=null){
			Log.e("Area", "not null");
//			Toast.makeText(activity, "Area : not null", Toast.LENGTH_LONG).show();
			for (int i = 0; i < AppConfig.AreaFilterModel.getAreaFilter().size(); i++) {
				Log.e("Area", "has items");
//				Toast.makeText(activity, "Area : " + "has items", Toast.LENGTH_LONG).show();
				if (checkSelectedAreaFilterTextView[i] == true) {
					
					if(AppConfig.AreaFilterModel.getAreaFilter().get(i)
							.equalsIgnoreCase(activity.getString(R.string.select_all))) {
						
						area = AppConfig.AreaFilterModel.getAreaFilter().get(i);
						break;
					}
					
					Log.e("Area", "is true");
//					Toast.makeText(activity, "Area : " + "is true", Toast.LENGTH_LONG).show();
					if(area.length() == 0) {
						area = AppConfig.AreaFilterModel.getAreaFilter().get(i);
					}
					else {
						area += "," + AppConfig.AreaFilterModel.getAreaFilter().get(i);
					}
				}
			}
		}

		return area;
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

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		initCheckSelection();
		
		((ScrollView) findViewById(R.id.hobby_sv_container)).setScrollbarFadingEnabled(false);

		hobby_searchImageView = (ImageView) findViewById(R.id.hobby_searchImageView);

		hobbyCityFilterTextView = (TextView) findViewById(R.id.hobbyCityFilterTextView);
		hobbyAgeFilterTextView = (TextView) findViewById(R.id.hobbyAgeFilterTextView);
		hobbySexFilterTextView = (TextView) findViewById(R.id.hobbySexFilterTextView);
		hobbyCateogory1FilterTextView = (TextView) findViewById(R.id.hobbyCategory1FilterTextView);
		hobbyCategory2FilterTextView = (TextView) findViewById(R.id.hobbyCategory2FilterTextView);
		hobbyAreaFilterTextView = (TextView) findViewById(R.id.hobbyAreaFilterTextView);
		hobbyTypeFilterTextView = (TextView) findViewById(R.id.hobbyTypeFilterTextView);

		hobbyCityFilterImageView = (ImageView) findViewById(R.id.hobbyCityFilterImageView);
		hobbyAgeFilterImageView = (ImageView) findViewById(R.id.hobbyAgeFilterImageView);
		hobbySexFilterImageView = (ImageView) findViewById(R.id.hobbySexFilterImageView);
		hobbyCategory1FilterImageView = (ImageView) findViewById(R.id.hobbyCategory1FilterImageView);
		hobbyCategory2FilterImageView = (ImageView) findViewById(R.id.hobbyCategory2FilterImageView);
		hobbyAreaFilterImageView = (ImageView) findViewById(R.id.hobbyAreaFilterImageView);
		hobbyTypeFilterImageView = (ImageView) findViewById(R.id.hobbyTypeFilterImageView);

		hobby_searchImageView.setOnClickListener(hobby_searchOnClickListener);
		
		hobbyCityFilterTextView.setOnClickListener(hobbyCityFilterOnClickListener);
		hobbyAgeFilterTextView.setOnClickListener(hobbyAgeFilterOnClickListener);
		hobbySexFilterTextView.setOnClickListener(hobbySexFilterOnClickListener);
		hobbyCateogory1FilterTextView.setOnClickListener(hobbyCategory1FilterOnClickListener);
		hobbyCategory2FilterTextView.setOnClickListener(hobbyCategory2FilterOnClickListener);
		hobbyAreaFilterTextView.setOnClickListener(hobbyAreaFilterOnClickListener);
		hobbyTypeFilterTextView.setOnClickListener(hobbyTypeFilterOnClickListener);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		hobbyCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		hobbyCityFilterImageView
				.setOnClickListener(hobbyCityFilterOnClickListener);
		hobbyAgeFilterImageView
				.setOnClickListener(hobbyAgeFilterOnClickListener);
		hobbySexFilterImageView
				.setOnClickListener(hobbySexFilterOnClickListener);
		hobbyCategory1FilterImageView
				.setOnClickListener(hobbyCategory1FilterOnClickListener);
		hobbyCategory2FilterImageView
				.setOnClickListener(hobbyCategory2FilterOnClickListener);
		hobbyAreaFilterImageView
				.setOnClickListener(hobbyAreaFilterOnClickListener);
		hobbyTypeFilterImageView
				.setOnClickListener(hobbyTypeFilterOnClickListener);

		hobbyOKImageView = (ImageView) findViewById(R.id.hobbyOKImageView);

		hobbyOKImageView.setOnClickListener(hobbyOKOnClickListener);
		
		hobbyAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyAreaFilterTextView.setSingleLine();
		hobbyCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyCityFilterTextView.setSingleLine();
		hobbyAgeFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyAgeFilterTextView.setSingleLine();
		hobbySexFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbySexFilterTextView.setSingleLine();
		hobbyCateogory1FilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyCateogory1FilterTextView.setSingleLine();
		hobbyCategory2FilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyCategory2FilterTextView.setSingleLine();
		hobbyTypeFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		hobbyTypeFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];
		checkSelectedAgeFilterTextView = new boolean[AppConfig.HobbyFilterModel
				.getAge().size()];
		checkSelectedSexFilterTextView = new boolean[AppConfig.HobbyFilterModel
				.getSex().size()];
		checkSelectedCategory1FilterTextView = new boolean[AppConfig.HobbyFilterModel
				.getCategory1().size()];
		checkSelectedTypeFilterTextView = new boolean[AppConfig.HobbyFilterModel
		        .getType().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedAgeFilterTextView.length; i++) {
			checkSelectedAgeFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSexFilterTextView.length; i++) {
			checkSelectedSexFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCategory1FilterTextView.length; i++) {
			checkSelectedCategory1FilterTextView[i] = false;
		}
		
		// initialize all values of list to 'unselected' initially
				for (int i = 0; i < checkSelectedTypeFilterTextView.length; i++) {
					checkSelectedTypeFilterTextView[i] = false;
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
		if(tv == hobbyCityFilterTextView) {
			if(new HobbyClassesActivity().getCity()!="")
			hobbyCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new HobbyClassesActivity().getCity());
			else
				hobbyCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == hobbyTypeFilterTextView) {
			checkSelectedTypeFilterTextView = DropDownListBaseClass.checkSelected;
			if(new HobbyClassesActivity().getType()!="")
			hobbyTypeFilterTextView.setText(activity.getString(R.string.tutor_type) + " - " + new HobbyClassesActivity().getType());
			else
				hobbyTypeFilterTextView.setText(activity.getString(R.string.tutor_type));
		}
		else if(tv == hobbyAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new HobbyClassesActivity().getArea()!="")
			hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new HobbyClassesActivity().getArea());
			else
				hobbyAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == hobbyAgeFilterTextView) {
			if(new HobbyClassesActivity().getAge()!="")
			hobbyAgeFilterTextView.setText(activity.getString(R.string.hobby_age) + " - " + new HobbyClassesActivity().getAge());
			else
				hobbyAgeFilterTextView.setText(activity.getString(R.string.hobby_age));
		}
		else if(tv == hobbySexFilterTextView) {
			if(new HobbyClassesActivity().getSex()!="")
			hobbySexFilterTextView.setText(activity.getString(R.string.hobby_sex) + " - " + new HobbyClassesActivity().getSex());
			else
				hobbySexFilterTextView.setText(activity.getString(R.string.hobby_sex));
		}
		else if(tv == hobbyCateogory1FilterTextView) {
			if(new HobbyClassesActivity().getCategory1()!="")
			hobbyCateogory1FilterTextView.setText(activity.getString(R.string.hobby_category1) + " - " + new HobbyClassesActivity().getCategory1());
			else
				hobbyCateogory1FilterTextView.setText(activity.getString(R.string.hobby_category1));
		}
		else if(tv == hobbyCategory2FilterTextView) {
			if(new HobbyClassesActivity().getCategory2()!="")
			hobbyCategory2FilterTextView.setText(activity.getString(R.string.hobby_category2) + " - " + new HobbyClassesActivity().getCategory2());
			else
				hobbyCategory2FilterTextView.setText(activity.getString(R.string.hobby_category2));
		}
	}
}

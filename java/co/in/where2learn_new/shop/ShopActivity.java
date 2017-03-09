package co.in.where2learn_new.shop;

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
import co.in.where2learn_new.navigation.AreaInterface;
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.DropDownListBaseClass;

public class ShopActivity extends AreaInterface {

	public static Activity activity;

	private FilterListBusinessLogic filterListBusinessLogic;

	private ImageView shop_searchImageView;
	private ImageView shopOKImageView;

	private View.OnClickListener shopOKOnClickListener;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView; 
	public static boolean[] checkSelectedCategoryFilterTextView;
	public static boolean[] checkSelectedSubCategoryFilterTextView;

	private ImageView shopCityFilterImageView;
	private ImageView shopAreaFilterImageView;
	private ImageView shopCategoryFilterImageView;
	private ImageView shopSubCategoryFilterImageView;

	public static TextView shopCityFilterTextView;
	public static TextView shopAreaFilterTextView;
	private static TextView shopCategoryFilterTextView;
	private static TextView shopSubCategoryFilterTextView;

	private View.OnClickListener shop_searchOnClickListener;

	private View.OnClickListener shopCityFilterOnClickListener;
	private View.OnClickListener shopAreaFilterOnClickListener;
	private View.OnClickListener shopCategoryFilterOnClickListener;
	private View.OnClickListener shopSubCategoryFilterOnClickListener;


	private DropDownListBaseClass dropDownListBaseClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		shop_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		shopCityFilterOnClickListener = new View.OnClickListener() {

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
						shopCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new ShopActivity().getArea()!="")
					shopAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ShopActivity().getArea());
					else
						shopAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		 

		/* Area * */

		shopAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();

				if (city.length() != 0 && !city.contains(",")) {
					filterListBusinessLogic.getAreaList(city, shopAreaFilterTextView);

					 
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

			};
		};

		 

		/* Category* */

		shopCategoryFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.ShopFilterModel!=null)
				{
					if(AppConfig.ShopFilterModel.getCategoryFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.ShopFilterModel.getCategoryFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedCategoryFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.ShopFilterModel.getCategoryFilter(),
						shopCategoryFilterTextView);

			}
		};
 

		/*** Sub Category ***/

		shopSubCategoryFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.ShopFilterModel!=null)
				{
					if(AppConfig.ShopFilterModel.getSubCategoryFilter()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.ShopFilterModel.getSubCategoryFilter().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedSubCategoryFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.ShopFilterModel.getSubCategoryFilter(),
						shopSubCategoryFilterTextView);

			}
		};

		 

		shopOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String cityList = getCity();
				String areaList = getArea();
				String category = getCategory();
				String subCategory = getSubCategory();
				
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
				else if(areaList.equalsIgnoreCase("") && !cityList.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}
				else if(category.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_category),
							Toast.LENGTH_LONG).show();
				}
				else if(subCategory.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_sub_category),
							Toast.LENGTH_LONG).show();
				}
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + cityList 
							+ "; <b>" + activity.getString(R.string.shop_category) + "</b>" + "->" + category
							+ "; <b>" + activity.getString(R.string.shop_sub_category) + "</b>" + "->" + subCategory
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + areaList;
					
					filterListBusinessLogic.invokeShopFilteredList(cityList,
							areaList, category, subCategory); 
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

	public String getArea() {
		String area = "";

		if(checkSelectedAreaFilterTextView!=null){
			Log.e("Area", "not null");
//			Toast.makeText(activity, "Area : not null", Toast.LENGTH_LONG).show();
			for (int i = 0; i < AppConfig.AreaFilterModel.getAreaFilter().size(); i++) {
				Log.e("Area", "has items");
//				Toast.makeText(activity, "Area : " + "has items", Toast.LENGTH_LONG).show();
				if (checkSelectedAreaFilterTextView[i] == true) {
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

	protected String getCategory() {
		String category = "";
		
		if(checkSelectedCategoryFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.ShopFilterModel.getCategoryFilter()
				.size(); i++) {
			if (checkSelectedCategoryFilterTextView[i] == true) {
				if(category.length() == 0) {
					category = AppConfig.ShopFilterModel.getCategoryFilter().get(i);
				} else {
					category = "," + AppConfig.ShopFilterModel.getCategoryFilter().get(i);
				}
			}
		}

		return category;
	}

	protected String getSubCategory() {
		String subCategory = "";
		
		if(checkSelectedSubCategoryFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.ShopFilterModel.getCategoryFilter()
				.size(); i++) {
			if (checkSelectedSubCategoryFilterTextView[i] == true) {
				if(subCategory.length() == 0) {
					subCategory = AppConfig.ShopFilterModel.getCategoryFilter()
							.get(i);
				} else {
					subCategory = "," + AppConfig.ShopFilterModel.getCategoryFilter()
							.get(i);
				}
			}
		}

		return subCategory;
	}

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		initCheckSelection();
		
		((ScrollView) findViewById(R.id.shop_sv_container)).setScrollbarFadingEnabled(false);

		shop_searchImageView = (ImageView) findViewById(R.id.shop_searchImageView);

		shopCityFilterTextView = (TextView) findViewById(R.id.shopCityFilterTextView);
		shopAreaFilterTextView = (TextView) findViewById(R.id.shopAreaFilterTextView);
		shopCategoryFilterTextView = (TextView) findViewById(R.id.shopCategoryFilterTextView);
		shopSubCategoryFilterTextView = (TextView) findViewById(R.id.shopSubCategoryFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		shopCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		shopCityFilterImageView = (ImageView) findViewById(R.id.shopCityFilterImageView);
		shopAreaFilterImageView = (ImageView) findViewById(R.id.shopAreaFilterImageView);
		shopCategoryFilterImageView = (ImageView) findViewById(R.id.shopCategoryFilterImageView);
		shopSubCategoryFilterImageView = (ImageView) findViewById(R.id.shopSubCategoryFilterImageView);

		shop_searchImageView.setOnClickListener(shop_searchOnClickListener);

		
		shopCityFilterTextView.setOnClickListener(shopCityFilterOnClickListener);
		shopAreaFilterTextView.setOnClickListener(shopAreaFilterOnClickListener);
		shopCategoryFilterTextView.setOnClickListener(shopCategoryFilterOnClickListener);
		shopSubCategoryFilterTextView.setOnClickListener(shopSubCategoryFilterOnClickListener);

		shopCityFilterImageView
				.setOnClickListener(shopCityFilterOnClickListener);
		shopAreaFilterImageView
				.setOnClickListener(shopAreaFilterOnClickListener);
		shopCategoryFilterImageView
				.setOnClickListener(shopCategoryFilterOnClickListener);
		shopSubCategoryFilterImageView
				.setOnClickListener(shopSubCategoryFilterOnClickListener);

		shopOKImageView = (ImageView) findViewById(R.id.shopOKImageView);

		shopOKImageView.setOnClickListener(shopOKOnClickListener);
		
		shopAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		shopAreaFilterTextView.setSingleLine();
		shopCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		shopCityFilterTextView.setSingleLine();
		shopCategoryFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		shopCategoryFilterTextView.setSingleLine();
		shopSubCategoryFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		shopSubCategoryFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		checkSelectedCategoryFilterTextView = new boolean[AppConfig.ShopFilterModel
				.getCategoryFilter().size()];

		checkSelectedSubCategoryFilterTextView = new boolean[AppConfig.ShopFilterModel
				.getSubCategoryFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCategoryFilterTextView.length; i++) {
			checkSelectedCategoryFilterTextView[i] = false;
		}

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSubCategoryFilterTextView.length; i++) {
			checkSelectedSubCategoryFilterTextView[i] = false;
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
		if(tv == shopCityFilterTextView) {
			if(new ShopActivity().getCity()!="")
			shopCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new ShopActivity().getCity());
			else
				shopCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == shopAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new ShopActivity().getArea()!="")
			shopAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new ShopActivity().getArea());
			else
				shopAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == shopCategoryFilterTextView) {
			if(new ShopActivity().getCategory()!="")
			shopCategoryFilterTextView.setText(activity.getString(R.string.shop_category) + " - " + new ShopActivity().getCategory());
			else
				shopCategoryFilterTextView.setText(activity.getString(R.string.shop_category));
		}
		else if(tv == shopSubCategoryFilterTextView) {
			if(new ShopActivity().getSubCategory()!="")
			shopSubCategoryFilterTextView.setText(activity.getString(R.string.shop_sub_category) + " - " + new ShopActivity().getSubCategory());
			else
				shopSubCategoryFilterTextView.setText(activity.getString(R.string.shop_sub_category));
		}
	}
}

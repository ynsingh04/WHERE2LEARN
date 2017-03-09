package co.in.where2learn_new.navigation;

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
import co.in.where2learn_new.util.ChangeFooter;
import co.in.where2learn_new.util.DropDownListBaseClass;

public class CoachingActivity extends Activity {

	public static Activity activity;
 

	// store select/unselect // information // about the // values in the //
	// list
	public static boolean[] checkSelectedCityFilterTextView;
	public static boolean[] checkSelectedAreaFilterTextView;
	public static boolean[] checkSelectedBoardFilterTextView;
	public static boolean[] checkSelectedClassFilterTextView;
	public static boolean[] checkSelectedSubjectFilterTextView;

	private FilterListBusinessLogic filterListBusinessLogic;

	private ImageView coachingCityFilterImageView;
	private ImageView coachingAreaFilterImageView;

	public static TextView coachingCityFilterTextView;
	public static TextView coachingAreaFilterTextView;

	private static TextView coachingBoardFilterTextView;
	private ImageView coachingBoardFilterImageView;

	private static TextView coachingClassFilterTextView;
	private ImageView coachingClassFilterImageView;

	private static TextView coachingSubjectFilterTextView;
	private ImageView coachingSubjectFilterImageView;

	private View.OnClickListener coachingCityFilterOnClickListener;
	private View.OnClickListener coachingAreaFilterOnClickListener;

 
	private View.OnClickListener coachingClassFilterOnClickListener;
 
	private View.OnClickListener coachingBoardFilterOnClickListener;
 
	private View.OnClickListener coachingSubjectFilterOnClickListener;

	private ImageView coachingOKImageView;
	private ImageView coaching_searchImageView;

	private View.OnClickListener coachingOKOnClickListener;
	private View.OnClickListener coaching_searchOnClickListener;
 

	private DropDownListBaseClass dropDownListBaseClass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coaching);

		activity = this;

		initListener();
		initComponent();

		getActionBar().hide();
		changeFooter();
	}

	private void initListener() {

		coaching_searchOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				onBackPressed();

			}
		};

		coachingCityFilterOnClickListener = new View.OnClickListener() {

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
						coachingCityFilterTextView);
				
				checkSelectedAreaFilterTextView = null;
				
				if(new CoachingActivity().getArea()!="")
					coachingAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CoachingActivity().getArea());
					else
						coachingAreaFilterTextView.setText(activity.getString(R.string.school_area));

			}
		};

		coachingAreaFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();

				if (city.length() != 0 && !city.contains(",")) {
					filterListBusinessLogic.getAreaList(city, coachingAreaFilterTextView);

					 
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

		 

		 
		 
		coachingClassFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CoachingFilterModel!=null)
				{
					if(AppConfig.CoachingFilterModel.getClasses()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CoachingFilterModel.getClasses().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedClassFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CoachingFilterModel.getClasses(),
						coachingClassFilterTextView);

			}
		};

		 
		coachingBoardFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CoachingFilterModel!=null)
				{
					if(AppConfig.CoachingFilterModel.getBoard()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CoachingFilterModel.getBoard().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedBoardFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CoachingFilterModel.getBoard(),
						coachingBoardFilterTextView);

			}
		};

	 
		coachingSubjectFilterOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				/** Check data presence */
				if(AppConfig.CoachingFilterModel!=null)
				{
					if(AppConfig.CoachingFilterModel.getSubject()==null)
					{
						Toast.makeText(
								activity,
								activity.getString(R.string.no_data_found),
								Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(AppConfig.CoachingFilterModel.getSubject().size()==0)
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

				DropDownListBaseClass.checkSelected = checkSelectedSubjectFilterTextView;

				dropDownListBaseClass.initiatePopUp(
						AppConfig.CoachingFilterModel.getSubject(),
						coachingSubjectFilterTextView);

			}
		};

		coachingOKOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String city = getCity();
				String area = getArea();
				String classes = getClasses();
				String board = getBoard();
				String subject = getSubjects();
				
				if(city.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_city),
							Toast.LENGTH_LONG).show();
				}
				else if(city.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.please_select_only_one_city),
							Toast.LENGTH_LONG).show();
				}
				else if(subject.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_subject),
							Toast.LENGTH_LONG).show();
				}
				else if(classes.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_class),
							Toast.LENGTH_LONG).show();
				}
				else if(board.equalsIgnoreCase("")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_a_board),
							Toast.LENGTH_LONG).show();
				}
				else if(area.equalsIgnoreCase("") && !city.contains(",")) {
					Toast.makeText(activity,
							activity.getString(R.string.select_an_area),
							Toast.LENGTH_LONG).show();
				}				
				else {
					AppConfig.FiltersSelected = "<b>" + activity.getString(R.string.school_city) + "</b>" + "->" + city
							+ "; <b>" + activity.getString(R.string.coaching_subject) + "</b>" + "->" + subject
							+ "; <b>" + activity.getString(R.string.coaching_class) + "</b>" + "->" + classes
							+ "; <b>" + activity.getString(R.string.school_board) + "</b>" + "->" + board
							+ "; <b>" + activity.getString(R.string.school_area) + "</b>" + "->" + area;
					
					filterListBusinessLogic.invokeCoachingFilteredList(city, area,
							classes, board, subject); 
				}
			}
		};

	}

	protected String getClasses() {
		String classes = "";
		
		if(checkSelectedClassFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.CoachingFilterModel.getClasses().size(); i++) {
			if (checkSelectedClassFilterTextView[i] == true) {
				
				if(AppConfig.CoachingFilterModel.getClasses().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					classes = AppConfig.CoachingFilterModel.getClasses().get(i);
					break;
				}
				
				if(classes.length() == 0) {
					classes = AppConfig.CoachingFilterModel.getClasses().get(i);
				} else{
					classes += "," + AppConfig.CoachingFilterModel.getClasses().get(i);
				}
			}
		}

		return classes;
	}

	protected String getBoard() {
		String board = "";
		
		if(checkSelectedBoardFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.CoachingFilterModel.getBoard().size(); i++) {
			if (checkSelectedBoardFilterTextView[i] == true) {
				
				if(AppConfig.CoachingFilterModel.getBoard().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					board = AppConfig.CoachingFilterModel.getBoard().get(i);
					break;
				}
				
				if(board.length() == 0) {
					board = AppConfig.CoachingFilterModel.getBoard().get(i);
				} else {
					board += "," + AppConfig.CoachingFilterModel.getBoard().get(i);
				}
			}
		}

		return board;
	}

	protected String getSubjects() {
		String subject = "";
		
		if(checkSelectedSubjectFilterTextView == null)
		{
			return "";
		}

		for (int i = 0; i < AppConfig.CoachingFilterModel.getSubject().size(); i++) {
			if (checkSelectedSubjectFilterTextView[i] == true) {
				
				if(AppConfig.CoachingFilterModel.getSubject().get(i)
						.equalsIgnoreCase(activity.getString(R.string.select_all))) {
					
					subject = AppConfig.CoachingFilterModel.getSubject().get(i);
					break;
				}
				
				if(subject.length() == 0) {
					subject = AppConfig.CoachingFilterModel.getSubject().get(i);
				} else {
					subject += "," + AppConfig.CoachingFilterModel.getSubject().get(i);
				}
			}
		}

		return subject;
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

		Log.e("Area", area);
		return area;
	}

	private void initComponent() {

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		dropDownListBaseClass = new DropDownListBaseClass();
		dropDownListBaseClass.setActivity(activity);

		initCheckSelection();
		
		((ScrollView) findViewById(R.id.coaching_sv_container)).setScrollbarFadingEnabled(false);

		coachingCityFilterTextView = (TextView) findViewById(R.id.coachingCityFilterTextView);
		coachingAreaFilterTextView = (TextView) findViewById(R.id.coachingAreaFilterTextView);
		coachingClassFilterTextView = (TextView) findViewById(R.id.coachingClassFilterTextView);
		coachingBoardFilterTextView = (TextView) findViewById(R.id.coachingBoardFilterTextView);
		coachingSubjectFilterTextView = (TextView) findViewById(R.id.coachingSubjectFilterTextView);
		
		if(!AppConfig.SelectedLocation.equalsIgnoreCase(""))
		coachingCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + getCity());

		coachingCityFilterImageView = (ImageView) findViewById(R.id.coachingCityFilterImageView);
		coachingAreaFilterImageView = (ImageView) findViewById(R.id.coachingAreaFilterImageView);
		coachingClassFilterImageView = (ImageView) findViewById(R.id.coachingClassFilterImageView);
		coachingBoardFilterImageView = (ImageView) findViewById(R.id.coachingBoardFilterImageView);
		coachingSubjectFilterImageView = (ImageView) findViewById(R.id.coachingSubjectFilterImageView);

		coaching_searchImageView = (ImageView) findViewById(R.id.coaching_searchImageView);
 
		coachingCityFilterTextView.setOnClickListener(coachingCityFilterOnClickListener);
		coachingAreaFilterTextView.setOnClickListener(coachingAreaFilterOnClickListener);
		coachingClassFilterTextView.setOnClickListener(coachingClassFilterOnClickListener);
		coachingBoardFilterTextView.setOnClickListener(coachingBoardFilterOnClickListener);
		coachingSubjectFilterTextView.setOnClickListener(coachingSubjectFilterOnClickListener);
		

		coachingCityFilterImageView
				.setOnClickListener(coachingCityFilterOnClickListener);
		coachingAreaFilterImageView
				.setOnClickListener(coachingAreaFilterOnClickListener);
		coachingClassFilterImageView
				.setOnClickListener(coachingClassFilterOnClickListener);
		coachingBoardFilterImageView
				.setOnClickListener(coachingBoardFilterOnClickListener);
		coachingSubjectFilterImageView
				.setOnClickListener(coachingSubjectFilterOnClickListener);

		filterListBusinessLogic = new FilterListBusinessLogic(activity);

		coachingOKImageView = (ImageView) findViewById(R.id.coachingOKImageView);

		coachingOKImageView.setOnClickListener(coachingOKOnClickListener);
		coaching_searchImageView
				.setOnClickListener(coaching_searchOnClickListener);
		
		coachingAreaFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		coachingAreaFilterTextView.setSingleLine();
		coachingCityFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		coachingCityFilterTextView.setSingleLine();
		coachingClassFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		coachingClassFilterTextView.setSingleLine();
		coachingBoardFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		coachingBoardFilterTextView.setSingleLine();
		coachingSubjectFilterTextView.setMovementMethod(new ScrollingMovementMethod());
		coachingSubjectFilterTextView.setSingleLine();

	}

	private void initCheckSelection() {

		checkSelectedCityFilterTextView = new boolean[AppConfig.CityFilterModel
				.getCityFilter().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedCityFilterTextView.length; i++) {
			checkSelectedCityFilterTextView[i] = false;
			if(AppConfig.CityFilterModel
					.getCityFilter().get(i).equalsIgnoreCase(AppConfig.SelectedLocation))
					checkSelectedCityFilterTextView[i] = true;
		}

		checkSelectedBoardFilterTextView = new boolean[AppConfig.CoachingFilterModel
				.getBoard().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedBoardFilterTextView.length; i++) {
			checkSelectedBoardFilterTextView[i] = false;
		}

		checkSelectedClassFilterTextView = new boolean[AppConfig.CoachingFilterModel
				.getClasses().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedClassFilterTextView.length; i++) {
			checkSelectedClassFilterTextView[i] = false;
		}
		checkSelectedSubjectFilterTextView = new boolean[AppConfig.CoachingFilterModel
				.getSubject().size()];

		// initialize all values of list to 'unselected' initially
		for (int i = 0; i < checkSelectedSubjectFilterTextView.length; i++) {
			checkSelectedSubjectFilterTextView[i] = false;
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
		if(tv == coachingCityFilterTextView) {
			if(new CoachingActivity().getCity()!="")
			coachingCityFilterTextView.setText(activity.getString(R.string.school_city) + " - " + new CoachingActivity().getCity());
			else
				coachingCityFilterTextView.setText(activity.getString(R.string.school_city));
		}
		else if(tv == coachingAreaFilterTextView) {
			checkSelectedAreaFilterTextView = DropDownListBaseClass.checkSelected;
			if(new CoachingActivity().getArea()!="")
			coachingAreaFilterTextView.setText(activity.getString(R.string.school_area) + " - " + new CoachingActivity().getArea());
			else
				coachingAreaFilterTextView.setText(activity.getString(R.string.school_area));
		}
		else if(tv == coachingClassFilterTextView) {
			if(new CoachingActivity().getClasses()!="")
			coachingClassFilterTextView.setText(activity.getString(R.string.coaching_class) + " - " + new CoachingActivity().getClasses());
			else
				coachingClassFilterTextView.setText(activity.getString(R.string.coaching_class));
		}
		else if(tv == coachingBoardFilterTextView) {
			if(new CoachingActivity().getBoard()!="")
			coachingBoardFilterTextView.setText(activity.getString(R.string.school_board) + " - " + new CoachingActivity().getBoard());
			else
				coachingBoardFilterTextView.setText(activity.getString(R.string.school_board));
		}
		else if(tv == coachingSubjectFilterTextView) {
			if(new CoachingActivity().getSubjects()!="")
			coachingSubjectFilterTextView.setText(activity.getString(R.string.coaching_subject) + " - " + new CoachingActivity().getSubjects());
			else
				coachingSubjectFilterTextView.setText(activity.getString(R.string.coaching_subject));
		}
	}
}

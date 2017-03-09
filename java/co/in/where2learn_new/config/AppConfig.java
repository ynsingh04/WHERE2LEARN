package co.in.where2learn_new.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.in.where2learn_new.bean.NotificationListItemBean;
import co.in.where2learn_new.bean.SearchBean;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.bean.TalkListItemBean;
import co.in.where2learn_new.model.AreaFilterModel;
import co.in.where2learn_new.model.CareerFilterModel;
import co.in.where2learn_new.model.CityFilterModel;
import co.in.where2learn_new.model.CoachingFilterModel;
import co.in.where2learn_new.model.CollegeFilterModel;
import co.in.where2learn_new.model.HobbyFilterModel;
import co.in.where2learn_new.model.ProfessionalFilterModel;
import co.in.where2learn_new.model.SchoolFilterModel;
import co.in.where2learn_new.model.ShopFilterModel;
import co.in.where2learn_new.model.TuitionFilterModel;
import co.in.where2learn_new.model.UserModel;
import co.in.where2learn_new.model.WorkshopFilterModel;

public final class AppConfig {
	

	public static final int MAX_STRING_LENGTH = 15;
	public static final int MAX_STRING_DESCRIPTION_LENGTH = 100;
	public static final String DEFAULT_RATING = "0";
	public static final int LISTRANGE = 50;
	public static int CURRENTRANGE = 1;

	public static final CityFilterModel CityFilterModel = new CityFilterModel();
	public static final AreaFilterModel AreaFilterModel = new AreaFilterModel();
	public static final SchoolFilterModel SchoolFilterModel = new SchoolFilterModel();
	public static final CollegeFilterModel CollegeFilterModel = new CollegeFilterModel();
	public static final TuitionFilterModel TuitionFilterModel = new TuitionFilterModel();
	public static final HobbyFilterModel HobbyFilterModel = new HobbyFilterModel();
	public static final CoachingFilterModel CoachingFilterModel = new CoachingFilterModel();
	public static final ProfessionalFilterModel ProfessionalFilterModel = new ProfessionalFilterModel();
	public static final CareerFilterModel CareerFilterModel = new CareerFilterModel();
	public static final WorkshopFilterModel WorkshopFilterModel = new WorkshopFilterModel();
	public static final ShopFilterModel ShopFilterModel = new ShopFilterModel();

	public static UserModel UserModel;

	public static String SelectedLocation;

	public static final ArrayList<SearchListItemBean> SearchedItemList = new ArrayList<SearchListItemBean>();
	public static final ArrayList<TalkListItemBean> TalkItemList = new ArrayList<TalkListItemBean>();
	public static final ArrayList<NotificationListItemBean> NotificationOrNewsItemList = new ArrayList<NotificationListItemBean>();
	public static final ArrayList<SearchBean> SearchList = new ArrayList<SearchBean>();		//Gaurav

	public static Map<String, String> CLASSIFIEDMODEL = new HashMap<String, String>();

	public static final String SITE_URL = "http://www.where2learn.co.in/";
//	public static final String SITE_URL = "http://demo.where2learn.co.in/";

	public static final String SITE_URL_API = SITE_URL + "API/";

	public static final String URI_USERREGISTRATION = SITE_URL
			+ "API/APICategory/UserRegistration";
	public static final String URI_GETUSERREGDETAILS = SITE_URL
			+ "API/APICategory/GetUserRegDetails";
//	public static final String URI_GETUSERREGDETAILS = SITE_URL
//			+ "API/APICategory/PostUserRegistrationsDetails";

	public static final String URI_ATTRIBUTE = SITE_URL_API
			+ "APICategory/Get/";

	public static final String URI_CITY = SITE_URL_API
			+ "APICategory/GetCityName";
	public static final String URI_AREA = SITE_URL_API
			+ "APICategory/GetAreaName/";

	public static final String URI_POSTSCHOOLSEARCH = SITE_URL_API
			+ "APICategory/SchoolSearch/";
	public static final String URI_POSTCOLLEGESEARCH = SITE_URL_API
			+ "APICategory/CollegeSearch/";
	public static final String URI_POSTTUITIONSEARCH = SITE_URL_API
			+ "APICategory/TutionSearch/";
	public static final String URI_POSTCOACHINGSEARCH = SITE_URL_API
			+ "APICategory/CoachingSearch/";
	public static final String URI_POSTHOBBYSEARCH = SITE_URL_API
			+ "APICategory/HobbySearchTesting/";
	public static final String URI_POSTPROFESSIONALSEARCH = SITE_URL_API
			+ "APICategory/ProfCoursesSearch/";
	public static final String URI_POSTCAREERSEARCH = SITE_URL_API
			+ "APICategory/CareercoachingsSearch/";
	public static final String URI_POSTWORKSHOPSEARCH = SITE_URL_API
			+ "APICategory/WorkshopsSearch/";
	public static final String URI_POSTSHOPSEARCH = SITE_URL_API
			+ "APICategory/ShopSearch/";

	public static final String URI_GETCLASSIFIEDSEARCHS = SITE_URL_API
			+ "APICategory/ClassifiedSearchs";
	public static final String URI_GETFACILITIESSEARCH = SITE_URL_API
			+ "APICategory/FacilitiesSearch";
	public static final String URI_GETEXTERNALLINKSEARCH = SITE_URL_API
			+ "APICategory/ExternallinkSearch";
	public static final String URI_GETIMAGEGALLERIESSEARCH = SITE_URL_API
			+ "APICategory/ImageGalleriesSearch";
	public static final String URI_GETRATINGSEARCH = SITE_URL_API
			+ "APICategory/GetRatingDetails";
	public static final String URI_GETIMPORTANTDATESSEARCH = SITE_URL_API
			+ "APICategory/ImportantDatesSearch";

	public static final String URI_ClassifiedRating = SITE_URL_API
			+ "APICategory/ClassifiedRating/";

	public static final String URI_SaveNotify = SITE_URL_API
			+ "APICategory/SaveNotify/";
	public static final String URI_NotificationDetails = SITE_URL_API
			+ "APICategory/NotificationDetails/";

	public static final String URI_NewsDetails = SITE_URL_API
			+ "APICategory/NewsDetails/";
	

	public static final String URI_TalkAndQueries = SITE_URL_API
			+ "APICategory/TalkAndQueries/";
	public static final String URI_SearchTalkAndQueries = SITE_URL_API
			+ "APICategory/SearchTalkAndQueries/";

	public static final String URISchoolType = "school";
	public static final String URICollegeType = "college";
	public static final String URITutorType = "Tuition";
	public static final String URICoachingType = "coaching";
	public static final String URIHobbyClassesType = "hobby";
	public static final String URIWorkShopType = "workshops";
	public static final String URICareerType = "Career%20coaching";
	public static final String URIProfessionalCourseType = "Professional%20Courses";
	public static final String URIShopType = "shop";

	
	public static String Policy_TnC_text = "";
	public static final String URI_PrivacyPolicyTnC = SITE_URL
			+ "API/APICategory/PrivacyPolicyTnC";
	public static final String PrivacyPolicyColumn = "Privacy Policy";
	public static final String TnCColumn = "Terms and Conditions";
	
	public static final String URI_WriteToUs = SITE_URL
			+ "API/APICategory/WriteToUs";
	
	public static final String URI_ForgotPassword = SITE_URL
			+ "API/APICategory/ForgotPassword";
	
	public static final String URI_SEARCHDATA = SITE_URL_API
			+ "APICategory/SearchAnyData";
	
	public static String VERSION = "";
	
	public static String HomeString = "";
	public static String CenterString = "";
	
	public static String FiltersSelected = "";
	
	public static String EMAIL_AND_COUNT = "";
	
	public static String EMAIL = "";

	/*** Profile date format ****/
	public static final String PROFILE_DATE_FORMAT = "MM/dd/yyyy";
	
	public static String COUNT = "";

	public static String ID = "";

}

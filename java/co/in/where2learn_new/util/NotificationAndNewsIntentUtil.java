package co.in.where2learn_new.util;

import android.app.Activity;
import co.in.where2learn_new.bean.NotificationListItemBean;
import co.in.where2learn_new.bean.SearchListItemBean;
import co.in.where2learn_new.businesslogic.FinalDetailsBusinessLogic;

public class NotificationAndNewsIntentUtil {

	private FinalDetailsBusinessLogic finalDetailsBusinessLogic;

	private Activity activity;

	public NotificationAndNewsIntentUtil(Activity activity) {
		this.activity = activity;
	}

	public void intentToDetailsPage(NotificationListItemBean itemBean) {

		finalDetailsBusinessLogic = new FinalDetailsBusinessLogic(activity);

		SearchListItemBean bean = new SearchListItemBean();
		bean.setClassified_id(itemBean.getClassifiedID());

		if (itemBean.getCategoryName().equalsIgnoreCase("School")) {

			finalDetailsBusinessLogic.invokeSchoolDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("College")) {

			finalDetailsBusinessLogic.invokeCollegeDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("Tution")) {

			finalDetailsBusinessLogic.invokeTuitionDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("Coaching")) {

			finalDetailsBusinessLogic.invokeCoachingDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("Hobby")) {

			finalDetailsBusinessLogic.invokeHobbyDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("Workshops")) {

			finalDetailsBusinessLogic.invokeWorkShopDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase(
				"Career Coaching")) {

			finalDetailsBusinessLogic.invokeCareerDetailsPage(bean);

		} else if (itemBean.getCategoryName().equalsIgnoreCase("Professional Courses")) {

			finalDetailsBusinessLogic.invokeProfessionalDetailsPage(bean);

		}

	}

}

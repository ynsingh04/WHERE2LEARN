package co.in.where2learn_new.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import co.in.where2learn_new.MainActivity;
import co.in.where2learn_new.R;
import co.in.where2learn_new.businesslogic.FooterBusinessLogic;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.database.DataBaseClass;

public class ChangeFooter {

	public static final int NOTIFICATION = 1;
	public static final int LOGIN = 2;

	private Activity activity;

	private int currentTab = 0;

	private FooterBusinessLogic businessLogic;

	private TextView notification;
	private TextView login;
	private TextView location;

	private View.OnClickListener notificationOnClickListener;
	private View.OnClickListener loginOnClickListener;
	private View.OnClickListener locationOnClickListener;

	private DataBaseClass dataBaseClass;

	public ChangeFooter(Activity activity) {

		this.activity = activity;

		initListener();
		initComponent();
	}

	private void initListener() {

		notificationOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeNotification();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();

			}
		};

		loginOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (AppConfig.UserModel == null) {

//					if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
						businessLogic.invokeLogin();
//						else
//							Toast.makeText(activity,
//									activity.getString(R.string.no_internet_found),
//									Toast.LENGTH_LONG).show();

				} else {

					showLogoutDialog();

					/*Toast.makeText(activity,
							activity.getString(R.string.already_login),
							Toast.LENGTH_SHORT).show();*/
				}
			}
		};

		locationOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(CheckInternet.getConnectivityStatus(activity)!=CheckInternet.TYPE_NOT_CONNECTED)
					businessLogic.invokeLocation();
					else
						Toast.makeText(activity,
								activity.getString(R.string.no_internet_found),
								Toast.LENGTH_LONG).show();

			}
		};

	}

	protected void showLogoutDialog() {

		String userid = "";
		if(AppConfig.UserModel.getEmailString().length()>0)
			userid = AppConfig.UserModel.getEmailString();
		else
			userid = AppConfig.UserModel.getPhoneString();
		
		String message = "You are login with " + Html.fromHtml("<b>User ID</b> ")
				+ userid + ". "
				+ activity.getString(R.string.logout_message);

		new AlertDialog.Builder(activity)
				.setTitle(activity.getString(R.string.logout_alert))
				.setMessage(message)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {

								if(AppConfig.UserModel == null) {
									Toast.makeText(activity,
											activity.getString(R.string.logout_successfully),
											Toast.LENGTH_SHORT).show();
									return;
								}

								if(AppConfig.UserModel.getPasswordString()==null) {
									MainActivity.mainActivity.signOut();
									LoginManager.getInstance().logOut();
								}

								dataBaseClass.deleteUserDetails();
								AppConfig.UserModel = null;
//								AppConfig.SelectedLocation = "";
								login.setText(activity
										.getString(R.string.login));
								MainActivity.userIdentificaton.setText("GUEST");

								Toast.makeText(activity,
										activity.getString(R.string.logout_successfully),
										Toast.LENGTH_SHORT).show();

							}
						}).setNegativeButton(android.R.string.no, null).show();

	}

	private void initComponent() {

		businessLogic = new FooterBusinessLogic(activity);

		dataBaseClass = new DataBaseClass(activity);
//		AppConfig.UserModel = dataBaseClass.getUserModel();

		notification = (TextView) activity.findViewById(R.id.notification);
		login = (TextView) activity.findViewById(R.id.login);
		location = (TextView) activity.findViewById(R.id.location);

		if (AppConfig.UserModel != null) {
			login.setText(activity.getString(R.string.logout));
		}

		notification.setOnClickListener(notificationOnClickListener);
		login.setOnClickListener(loginOnClickListener);
		location.setOnClickListener(locationOnClickListener);
	}

	public void change() {

		switch (currentTab) {
		case 0:

			break;
		case NOTIFICATION:

			notification.setOnClickListener(null);
			break;

		case LOGIN:

			login.setOnClickListener(null);
			break;

		default:
			break;
		}

	}

	public int getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}

}

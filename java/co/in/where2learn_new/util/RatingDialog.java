package co.in.where2learn_new.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import co.in.where2learn_new.R;
import co.in.where2learn_new.config.AppConfig;
import co.in.where2learn_new.webservice.SetRatingWebServiceCall;

public class RatingDialog extends Dialog {

	private Context context;

	private Dialog dialog;

	private RatingBar ratingBar;

	private View.OnClickListener okOnClickListener;

	private SetRatingWebServiceCall ratingWebServiceCall;

	protected String classifiedID;
	private Button oKButton;

	public RatingDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		this(context);
	}

	public RatingDialog(Context context, int theme) {
		this(context);
	}

	public RatingDialog(Context context) {
		super(context);

		/* Note: should before the setContextView* */
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dialog_rating);

		this.context = context;
		this.dialog = this;

		initListener();
		initComponent();

	}

	private void initListener() {

		okOnClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (AppConfig.UserModel != null) {

					String ratingNo = getRating();
					String userName = getUserName();

					try {
						ratingWebServiceCall.setRating(ratingNo, userName,
								classifiedID);
					} catch (Exception e) {
						Toast.makeText(
								context,
								context.getString(R.string.error_at_server_end),
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(context,
							context.getString(R.string.please_login),
							Toast.LENGTH_LONG).show();
				}

			}
		};

	}

	protected String getRating() {
		return Math.round(ratingBar.getRating()) + "";
	}

	protected String getUserName() {
		if(AppConfig.UserModel.getEmailString().length()>0)
			return AppConfig.UserModel.getEmailString();
		else
			return AppConfig.UserModel.getPhoneString();
	}

	private void initComponent() {

		ratingWebServiceCall = new SetRatingWebServiceCall(context, dialog);

		ratingBar = (RatingBar) findViewById(R.id.dailog_ratingRatingBar);
		oKButton = (Button) findViewById(R.id.dailog_ratingOKButton);

		oKButton.setOnClickListener(okOnClickListener);
	}

	public String getClassifiedID() {
		return classifiedID;
	}

	public void setClassifiedID(String classifiedID) {
		this.classifiedID = classifiedID;
	}

}

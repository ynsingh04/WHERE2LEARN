package co.in.where2learn_new;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(formKey = "", // will not be used
mailTo = "help@where2learn.co.in",
mode = ReportingInteractionMode.SILENT,
resToastText = R.string.crash_text)

public class Where2LearnApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ACRA.init(this);
	}
	
}

package co.in.where2learn_new.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Where2LearnSharedPrefs {
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;
	private static Where2LearnSharedPrefs where2learnSharedpreferences;

	private Where2LearnSharedPrefs(Context context) {
		this.appSharedPrefs = context.getSharedPreferences("where2learnsharedpref", Context.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}

	public static Where2LearnSharedPrefs getsharedprefInstance(Context con)
	{
		if(where2learnSharedpreferences==null)
			where2learnSharedpreferences=new Where2LearnSharedPrefs(con);
		return where2learnSharedpreferences;
	}

	public SharedPreferences getAppSharedPrefs() {
		return appSharedPrefs;
	}

	public void setAppSharedPrefs(SharedPreferences appSharedPrefs) {
		this.appSharedPrefs = appSharedPrefs;
	}

	public Editor getPrefsEditor() {
		return prefsEditor;
	}

	public void Commit(){
		prefsEditor.commit();
	}
	
	public void setLocation(String location){
		this.prefsEditor = appSharedPrefs.edit();
		  prefsEditor.putString("location", location);  // for default
		prefsEditor.commit();		
	}

	public String getLocation(){		
		return appSharedPrefs.getString("location", "");
	}
}

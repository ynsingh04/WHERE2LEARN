package co.in.where2learn_new.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import co.in.where2learn_new.model.UserModel;

public class DataBaseClass extends SQLiteOpenHelper {

	private static final String TAG = "DataBaseClass";

	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "WHERE2LEARN_DB";

	// Table Names
	private static final String USER_TABLE = "USER_TABLE";

	// Common column names
	private static final String USERID = "USERID";
	private static final String USERNAME = "USERNAME";
	private static final String PASSWORD = "PASSWORD";
	private static final String MOBILE = "MOBILE";

	// Table Create Statements
	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ USER_TABLE + "(" + USERID + " TEXT PRIMARY KEY," + USERNAME
			+ " TEXT," + PASSWORD + " TEXT," + MOBILE + " TEXT" + ")";

	public DataBaseClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 * @param factory
	 *            to use for creating cursor objects, or null for the default
	 * @param version
	 *            number of the database (starting at 1)
	 */
	public DataBaseClass(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

		// creating required tables
		sqLiteDatabase.execSQL(CREATE_TABLE_USER);

		Log.i(TAG, "Database created.");

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,
			int newVersion) {

		// on upgrade drop older tables
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);

		Log.i(TAG, "Database upgraded.");

	}

	/*
	 * Creating a user details
	 */
	public long createUserDetails(UserModel model) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		if(model.getEmailString()==null || model.getEmailString().equalsIgnoreCase("")) {
			values.put(USERID, model.getPhoneString());
		}
		else if(model.getPhoneString()==null || model.getPhoneString().equalsIgnoreCase("")) {
			values.put(USERID, model.getEmailString());
		}
		else {
			values.put(USERID, model.getEmailString());
		}
//		values.put(USERID, model.getEmailString());
		values.put(USERNAME, model.getNameString());
		values.put(PASSWORD, model.getPasswordString());
		values.put(MOBILE, model.getPhoneString());

		// insert row
		long id = db.insert(USER_TABLE, null, values);

		return id;
	}

	/*
	 * getting user details
	 */
	public UserModel getUserModel() {

		UserModel userModel = null;

		String selectQuery = "SELECT  * FROM " + USER_TABLE;

		Log.i(TAG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {

			userModel = new UserModel();
			userModel.setEmailString(c.getString((c.getColumnIndex(USERID))));
			userModel.setNameString(c.getString((c.getColumnIndex(USERNAME))));
			userModel
					.setPasswordString(c.getString((c.getColumnIndex(PASSWORD))));
			userModel.setPhoneString(c.getString((c.getColumnIndex(MOBILE))));
			userModel.setUserID(c.getString(c.getColumnIndex(USERID)));

		}

		return userModel;
	}

	/*
	 * Deleting a user details
	 */
	public int deleteUserDetails() {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(USER_TABLE, "", new String[] {});
	}

}

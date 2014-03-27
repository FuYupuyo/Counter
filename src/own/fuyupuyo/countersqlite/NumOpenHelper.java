package own.fuyupuyo.countersqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NumOpenHelper extends SQLiteOpenHelper {
	private static int DATABASE_VERSION = 3;
	private static String DATABASE_NAME = "Num.db";

	private static String NUM_TABLE_CREATE = "CREATE TABLE num ( num INTEGER PRIMARY KEY )";
	private static String NUM_TABLE_DELETE = "DROP TABLE IF EXISTS num";

	public NumOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(NUM_TABLE_CREATE);
		ContentValues values = new ContentValues();
		values.put("num", 0);
		db.insert("num", null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(NUM_TABLE_DELETE);
		onCreate(db);
	}

}

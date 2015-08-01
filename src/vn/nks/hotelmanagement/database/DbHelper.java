package vn.nks.hotelmanagement.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "HotelManagement";
	public static final int DB_VERSION = 1;	
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Delete table if exists
		db.execSQL("DROP TABLE IF EXISTS Hotel");
		
		// Create table News
		String createTable_Mobile = 
				"CREATE TABLE Hotel (" +
				"HotelId INT PRIMARY KEY, " +
				"Name NVARCHAR(256) NOT NULL, " +
				"Address NVARCHAR(256) NOT NULL," +
				"Long NVARCHAR(256) NOT NULL, " +
				"Lat NVARCHAR(256) NOT NULL)";
		
		// Execute sql statement 		
		db.execSQL(createTable_Mobile);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropTableSql = "DROP TABLE IF EXISTS Hotel";
		db.execSQL(dropTableSql);
		onCreate(db);
	}
}

package vn.nks.hotelmanagement.database;

import java.util.ArrayList;

import vn.nks.hotelmanagement.database.DbHelper;
import vn.nks.hotelmanagement.model.Hotel;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HotelDAO {
	private SQLiteDatabase db;
	
	// Declare constructor 
	public HotelDAO(Context context) {
		DbHelper dbHelper = new DbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	// Insert Methods
	public long insert(Hotel tmpHotel) {
		// Create object containing the pair of value to add data with insert method of the SQLiteDatabase class
		ContentValues values = new ContentValues();
		values.put("Hotelid", tmpHotel.getId());
		values.put("name", tmpHotel.getName());
		values.put("address", tmpHotel.getAddress());
		values.put("long", tmpHotel.getLng());
		values.put("lat", tmpHotel.getLat());
		return db.insert("Hotel", null, values);
	}

	// Update Method
	public int update(Hotel tmpHotel) {
		// Create object containing the pair of value to edit data with update method of the SQLiteDatabase class
		ContentValues values = new ContentValues();
		values.put("Hotelid", tmpHotel.getId());
		values.put("name", tmpHotel.getName());
		values.put("address", tmpHotel.getAddress());
		values.put("long", tmpHotel.getLng());
		values.put("lat", tmpHotel.getLat());
		return db.update("Hotel", values, "HotelId=?", new String[]{String.valueOf(tmpHotel.getId())});
	}

	//Delete Method	
	public int delete(int tmpHotelId) {
		// Delete data with delete method of the SQLiteDatabase class
		return db.delete("Hotel", "HotelId=?", new String[]{String.valueOf(tmpHotelId)});
	}
	
	// The method gets the last ID of Hotel object
	public int getNewId(){
		String sql = "SELECT * FROM Hotel order by HotelId desc";
		ArrayList<Hotel> list = getHotels(sql);
		if(list.isEmpty())
			return 1;
		else{
			Hotel  m = list.get(0);
			return m.getId() + 1;
		}
	}

	// The method gets the list of Hotel
	public ArrayList<Hotel> getHotel() {
		String sql = "SELECT * FROM Hotel";
		return getHotels(sql);
	}
	
	// The method gets the list of Hotel from search 
	public ArrayList<Hotel> searchHotel(String tmpKeyword, String min, String max) {
		String sql = "SELECT * FROM Hotel WHERE Name like '%" + tmpKeyword + "%'";
		return getHotels(sql);
	}
	
	// The method gets one Hotel from search
	public Hotel getHotelById(int tmpHotelId) {
		String sql = "SELECT * FROM Hotel WHERE HotelId=?";
		ArrayList<Hotel> list = getHotels(sql, String.valueOf(tmpHotelId));
		return list.get(0);
	}
	
	// The method gets the list of Hotel from sql statement and arguments
	public ArrayList<Hotel> getHotels(String sql, String...selectionArgs) {
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		Cursor c = db.rawQuery(sql, selectionArgs);
		while (c.moveToNext()){
			Hotel h = new Hotel();
			h.setId(c.getInt(0));
			h.setName(c.getString(1));
			h.setAddress(c.getString(2));
			h.setLng(c.getString(3));
			h.setLat(c.getString(4));
			list.add(h);
		} 
		return list;
	}
}

package vn.nks.hotelmanagement;

import java.util.ArrayList;

import vn.nks.hotelmanagement.database.HotelDAO;
import vn.nks.hotelmanagement.model.Hotel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private GoogleMap map;
	HotelDAO hDAO;
	LatLng tmpPos; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create DAO object
		hDAO = new HotelDAO(this);
		// Get list of hotel
		ArrayList<Hotel> hotels = hDAO.getHotel();
		// Get map object on layout
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		// Display on map with each hotel info
		for (int i = 0; i < hotels.size(); i++) {
			double lat = Double.valueOf(hotels.get(i).getLat());
			double lng = Double.valueOf(hotels.get(i).getLng());
			map.addMarker(new MarkerOptions().position(new LatLng(lat,lng))
					.title(hotels.get(i).getName()));
		}		
		// Move the camera instantly to hamburg with a zoom of 15.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf("10.7745265"),Double.valueOf("106.703683")),14));
		// Zoom in, animating the camera.
		map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
	}

	@Override
	protected void onResume() {	
		super.onResume();
		// Load data to map when user reuse activity
		ArrayList<Hotel> hotels = hDAO.getHotel();
		
		for (int i = 0; i < hotels.size(); i++) {
			double lat = Double.valueOf(hotels.get(i).getLat());
			double lng = Double.valueOf(hotels.get(i).getLng());
			map.addMarker(new MarkerOptions().position(new LatLng(lat,lng))
					.title(hotels.get(i).getName()));
		}
	}
	
	// Show message on Toast object
	public void showMessage(String tmpMessage) {
		Toast.makeText(this, tmpMessage, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_manage) {
			Intent intent = new Intent(this, HotelManageActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

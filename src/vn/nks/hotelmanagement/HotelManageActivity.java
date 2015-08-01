package vn.nks.hotelmanagement;

import java.util.ArrayList;

import vn.nks.hotelmanagement.database.HotelDAO;
import vn.nks.hotelmanagement.model.Hotel;
import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class HotelManageActivity extends ActionBarActivity {

	// Declare variables
	HotelDAO hDAO;
	Button btn;
	ArrayList<Hotel> lstHotel;
	HotelListAdminAdapter adapter;
	ListView lvHotel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_manage);
		// Create HotelDAO object
		hDAO = new HotelDAO(HotelManageActivity.this);
		btn = (Button) findViewById(R.id.btnAdd);
		lvHotel = (ListView) findViewById(R.id.lstHotel);
		loadData();
		btn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Create dialog and show on application
				final Dialog diaAdd = new Dialog(HotelManageActivity.this);
				diaAdd.setTitle("Add new Hotel");
				diaAdd.setContentView(R.layout.activity_view_hotel);
				// Get layout controls on dialog
				Button btn = (Button) diaAdd.findViewById(R.id.btnAdd);
				final EditText edtId = (EditText) diaAdd
						.findViewById(R.id.edtId);
				final EditText edtName = (EditText) diaAdd
						.findViewById(R.id.edtName);
				final EditText edtAddress = (EditText) diaAdd
						.findViewById(R.id.edtAddress);
				final EditText edtLng = (EditText) diaAdd
						.findViewById(R.id.edtLng);
				final EditText edtLat = (EditText) diaAdd
						.findViewById(R.id.edtLat);
				// Use getNewId function for getting new id and load data to
				// EditText
				edtId.setText(String.valueOf(hDAO.getNewId()));
				edtId.setEnabled(false);
				// User click on Button to add new Mobile
				btn.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Declare variables receive data from EditText
						String hId = edtId.getText().toString();
						String hName = edtName.getText().toString();
						String hAddress = edtAddress.getText().toString();
						String hLongitude = edtLng.getText().toString();
						String hLatitude = edtLat.getText().toString();
						// Show message if user don't enter value to EditText
						if (hId.trim().equalsIgnoreCase("")) {
							showMessage("Id is not empty. Please try again.");
						} else if (hName.trim().equalsIgnoreCase("")) {
							showMessage("Name is not empty. Please try again.");
						} else if (hAddress.trim().equalsIgnoreCase("")) {
							showMessage("Address is not empty. Please try again.");
						} else if (hLongitude.trim().equalsIgnoreCase("")) {
							showMessage("Longitude is not empty. Please try again.");
						} else if (hLatitude.trim().equalsIgnoreCase("")) {
							showMessage("Latitude is not empty. Please try again.");
						} else {
							// Create hotel object
							Hotel tmpHotel = new Hotel();
							tmpHotel.setId(Integer.parseInt(edtId.getText()
									.toString()));
							tmpHotel.setName(edtName.getText().toString());
							tmpHotel.setAddress(edtAddress.getText().toString());
							tmpHotel.setLng(edtLng.getText().toString());
							tmpHotel.setLat(edtLat.getText().toString());
							// insert hotel
							hDAO.insert(tmpHotel);
							// Show data to Listview
							loadData();
							// Close dialog
							diaAdd.dismiss();
						}
					}
				});
				// Show dialog
				diaAdd.show();
			}
		});
		
		// User click on Listview to update and delete data
		lvHotel.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				// Get Hotel object from item of Listview
				final Hotel tmpHotel = adapter.getItem(position);
				// Create dialog and show on application
				final Dialog dia = new Dialog(HotelManageActivity.this);
				dia.setContentView(R.layout.activity_view_hotel);
				dia.setTitle("Edit Hotel");
				// Get layout control of dialog
				Button btn1 = (Button) dia.findViewById(R.id.btnAdd);
				Button btn2 = (Button) dia.findViewById(R.id.btnDel);
				final EditText edtId = (EditText) dia.findViewById(R.id.edtId);
				final EditText edtName = (EditText) dia.findViewById(R.id.edtName);
				final EditText edtAddress = (EditText) dia.findViewById(R.id.edtAddress);
				final EditText edtLng = (EditText) dia.findViewById(R.id.edtLng);
				final EditText edtLat = (EditText) dia.findViewById(R.id.edtLat);
				// Set content and visibility of Button
				btn1.setText("Update");
				btn2.setVisibility(View.VISIBLE);
				// Show data of Hotel to editText
				edtId.setText(String.valueOf(tmpHotel.getId()));
				edtId.setEnabled(false);
				edtName.setText(tmpHotel.getName());
				edtAddress.setText(tmpHotel.getAddress());
				edtLng.setText(tmpHotel.getLng());
				edtLat.setText(tmpHotel.getLat());
				// User click on Button to update info of Hotel
				btn1.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Declare variables receive data from EditText
						String hId = edtId.getText().toString();
						String hName = edtName.getText().toString();
						String hAddress = edtAddress.getText().toString();
						String hLongitude = edtLng.getText().toString();
						String hLatitude = edtLat.getText().toString();
						// Show message if user don't enter value to EditText
						if (hId.trim().equalsIgnoreCase("")) {
							showMessage("Id is not empty. Please try again.");
						} else if (hName.trim().equalsIgnoreCase("")) {
							showMessage("Name is not empty. Please try again.");
						} else if (hAddress.trim().equalsIgnoreCase("")) {
							showMessage("Address is not empty. Please try again.");
						} else if (hLongitude.trim().equalsIgnoreCase("")) {
							showMessage("Longitude is not empty. Please try again.");
						} else if (hLatitude.trim().equalsIgnoreCase("")) {
							showMessage("Latitude is not empty. Please try again.");
						} else {
							// Create hotel object
							Hotel tmpHotel = new Hotel();
							tmpHotel.setId(Integer.parseInt(edtId.getText()
									.toString()));
							tmpHotel.setName(edtName.getText().toString());
							tmpHotel.setAddress(edtAddress.getText().toString());
							tmpHotel.setLng(edtLng.getText().toString());
							tmpHotel.setLat(edtLat.getText().toString());
							// Update info
							hDAO.update(tmpHotel);
							// Show data to Listview
							loadData();
							// Close dialog
							dia.dismiss();
						}
					}
				});
				// User click on Button to delete info of Mobile
				btn2.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Delete info
						hDAO.delete(tmpHotel.getId());
						// Show data to Listview
						loadData();
						// Close dialog
						dia.dismiss();
					}
				});
				// Show dialog
				dia.show();
				return true;
			}
		});
	}

	// Load data to Listview
	public void loadData() {
		lstHotel = hDAO.getHotel();
		adapter = new HotelListAdminAdapter(this, lstHotel);
		lvHotel.setAdapter(adapter);
	}

	// Show message on Toast object
	public void showMessage(String tmpMessage) {
		Toast.makeText(this, tmpMessage, Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hotel_manage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package vn.nks.hotelmanagement;

import java.util.List;

import vn.nks.hotelmanagement.model.Hotel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HotelListAdminAdapter extends ArrayAdapter<Hotel>{

	public HotelListAdminAdapter(Context context, List<Hotel> info) {
		super(context, R.layout.hotel_custom_admin_item, info);		
	}
	
	public View getView(int position, View view, ViewGroup parent){
		Hotel info = getItem(position);
		
		LayoutInflater inflater =((Activity) getContext()).getLayoutInflater();
		View row = inflater.inflate(R.layout.hotel_custom_admin_item, null, true);
	
		TextView txtName = (TextView) row.findViewById(R.id.txtName);
		TextView txtAddress = (TextView) row.findViewById(R.id.txtAddress);
				
		txtName.setText(info.getName());	
		txtAddress.setText(info.getAddress());
		
		return row;
	}
}

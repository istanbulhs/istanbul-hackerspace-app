package org.istanbulhs.istanbulhackerspaceapp;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HackerspaceMapFragment extends SupportMapFragment {

	private LatLng hsLocation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		hsLocation = new LatLng(40.993498, 29.042059);
		CameraPosition camPos = new CameraPosition.Builder()
				.target(hsLocation)
				.zoom(16)
				.build();
		
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
		getMap().moveCamera(update);
		
		MarkerOptions mrkr = new MarkerOptions();
		mrkr.position(hsLocation);
		getMap().addMarker(mrkr);
		
		getMap().setMyLocationEnabled(true);
		
		return v;
	}

}

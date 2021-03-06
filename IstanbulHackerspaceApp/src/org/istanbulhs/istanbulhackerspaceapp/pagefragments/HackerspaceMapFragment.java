package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import org.istanbulhs.istanbulhackerspaceapp.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HackerspaceMapFragment extends SupportMapFragment {

	private LatLng hsLocation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		hsLocation = new LatLng(40.993498, 29.042059);
		if (getActivity() != null) {
			int checkGooglePlayServices = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		    
			if (checkGooglePlayServices != ConnectionResult.SUCCESS) {
		    // google play services is missing!!!!
		    /* Returns status code indicating whether there was an error. 
		    Can be one of following in ConnectionResult: SUCCESS, SERVICE_MISSING, SERVICE_VERSION_UPDATE_REQUIRED, SERVICE_DISABLED, SERVICE_INVALID.
		    */
				Log.e("hs", "play services not available");
				Log.e("hs", String.valueOf(checkGooglePlayServices));
				
		    } else {
	    
				CameraPosition camPos = new CameraPosition.Builder()
						.target(hsLocation)
						.zoom(16)
						.build();
				
				CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);
				getMap().moveCamera(update);
				
				MarkerOptions mrkr = new MarkerOptions();
				mrkr.position(hsLocation)
					.title("İstanbul HackerSpace")
					.snippet("Click to call - Tel : +90 216 418 04 17")
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
				
				getMap().addMarker(mrkr);
				
				getMap().setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			        public void onInfoWindowClick(final Marker marker) {
		        		Intent callIntent = new Intent(Intent.ACTION_CALL);
				        callIntent.setData(Uri.parse("tel:+90 216 418 04 17"));
				        startActivity(callIntent);
			        }
				});
			
				
				getMap().setMyLocationEnabled(true);
			}
		}
		return v;
	}

}

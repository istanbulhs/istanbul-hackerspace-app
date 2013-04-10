package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import org.istanbulhs.istanbulhackerspaceapp.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class AboutFragment extends Fragment {

	public AboutFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_about, container, false);
		WebView webView = (WebView)view.findViewById(R.id.about_web_view);
		
		Resources resource = getResources();
		String aboutHackerspaceString = resource.getString(R.string.about_hackerspace);
		
		webView.getSettings().setBuiltInZoomControls(true);
	    webView.getSettings().setDisplayZoomControls(false);		
	    webView.loadData(aboutHackerspaceString, "text/html; charset=UTF-8", "UTF-8");
	    
		return view;
	}
	
	
}

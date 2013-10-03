package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import org.istanbulhs.istanbulhackerspaceapp.R;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

		View view = inflater.inflate(R.layout.fragment_about, container, false);
		WebView webView = (WebView)view.findViewById(R.id.about_web_view);
		
		Resources resource = getResources();
		String aboutHackerspaceString = resource.getString(R.string.about_hackerspace);
		
		webView.getSettings().setBuiltInZoomControls(true);
	   	webView.getSettings().setDefaultTextEncodingName("utf-8");

	   	webView.loadDataWithBaseURL(null, aboutHackerspaceString, "text/html", "utf-8", null);
	    
		return view;
	}
	
	
}

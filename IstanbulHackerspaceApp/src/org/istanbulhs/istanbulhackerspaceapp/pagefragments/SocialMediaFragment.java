package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import org.istanbulhs.istanbulhackerspaceapp.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SocialMediaFragment extends Fragment {

	private View mfv;
	private ImageButton twitter;
	private ImageButton facebook;
	private ImageButton irc;
	private ImageButton gplus;
	
	public SocialMediaFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		mfv = inflater.inflate(R.layout.fragment_social_media, container, false);
		
		twitter = (ImageButton) mfv.findViewById(R.id.btn_twitter);
		twitter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=istanbulhs"));
					startActivity(intent);
				} catch (Exception e) {
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/istanbulhs"));
					startActivity(intent);
				}

			}
		});
		
		facebook = (ImageButton) mfv.findViewById(R.id.btn_facebook);
		facebook.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com/istanbulhs"));
				startActivity(intent);

			}
		});
		
		irc = (ImageButton) mfv.findViewById(R.id.btn_irc);
		irc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://webchat.freenode.net/?randomnick=1&channels=istanbulhs"));
				startActivity(intent);

			}
		});
		
		gplus = (ImageButton) mfv.findViewById(R.id.btn_gplus);
		gplus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/115379238585648089705/posts"));
				startActivity(intent);

			}
		});
		
		return mfv;
		
	}

}

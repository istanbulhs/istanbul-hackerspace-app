package org.istanbulhs.istanbulhackerspaceapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.pagefragments.AboutFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.BlogListFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.HackerspaceMapFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.SocialMediaFragment;
import org.istanbulhs.istanbulhackerspaceapp.util.OSFW;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;



import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity {

	private Fragment content;
	private List<Fragment> fragmentList;
	private OSFW osfw;
	private String onlineDevice;
	private Connection conn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// defining classes
		osfw = new OSFW();
		
		// set the Above View
		if (savedInstanceState != null) {
			content = getSupportFragmentManager().getFragment(savedInstanceState, "mainContent");
		}
		
		this.initFragments();
		
		if (content == null) {
			content = this.fragmentList.get(0);	
		}
			
		// set the Above View
		setContentView(R.layout.frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.frame, content)
		.commit();

		
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		SlidingMenuListFragment menuFragment = new SlidingMenuListFragment();

		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		t.add(R.id.menu_frame, menuFragment);
		t.commit();
		
		// customize the SlidingMenu
		this.setSlidingActionBarEnabled(true);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
		getSlidingMenu().setShadowDrawable(R.drawable.shadow);
		getSlidingMenu().setBehindOffsetRes(R.dimen.actionbar_home_width);
		getSlidingMenu().setBehindScrollScale(0.25f);
		
		// customize the ActionBar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);
		
	}
	
	private class Connection extends AsyncTask<URL, Integer, Long> {
	     protected Long doInBackground(URL... urls) {
	        
	    	onlineDevice = osfw.Http("http://sekai.istanbulhs.org:8081/kac_cihaz");
			if (onlineDevice==null) {
				onlineDevice = osfw.Http("http://192.168.1.128:8081/kac_cihaz");
			}
	    	return null;
	        
	     }

	     protected void onProgressUpdate(Integer... progress) {
	         setProgress(progress[0]);
	     }

	     protected void onPostExecute(Long result) {
	    	 
	    	 if (onlineDevice!=null) {
		    	 if (Integer.parseInt(onlineDevice)==0) {
		    		 osfw.Message("Mekan", "Mekan şu an kapalı.", "Tamam", MainActivity.this);
		    	 } else if (Integer.parseInt(onlineDevice)>0) {
		    		 osfw.Message("Mekan", "Mekan şu an açık ve " + onlineDevice + " cihaz bağlı.", "Tamam", MainActivity.this);
		    	 } else {
		    		 osfw.Message("Mekan", "Beklenmeyen bir hata oluştu! Err:1", "Tamam", MainActivity.this);
		    	 }
	    	 } else {
	    		 osfw.Message("Mekan", "Beklenmeyen bir hata oluştu! Err:2", "Tamam", MainActivity.this);
	    	 }
	    	 
	     }
	 }
	
	private void initFragments() {
		this.fragmentList = new ArrayList<Fragment>(4);
		this.fragmentList.add(new BlogListFragment());
		this.fragmentList.add(new AboutFragment());
		this.fragmentList.add(new HackerspaceMapFragment());
		this.fragmentList.add(new SocialMediaFragment());	
	}
	
	public void switchContent(int position) {
		content = this.fragmentList.get(position);
		
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.frame, content)
			.commit();
		
		getSlidingMenu().showContent();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mainContent", content);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;
		case R.id.action_online:
			conn = new Connection();
			conn.execute();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

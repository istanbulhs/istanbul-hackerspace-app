package org.istanbulhs.istanbulhackerspaceapp;

import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.pagefragments.AboutFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.BlogListFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.HackerspaceMapFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.SocialMediaFragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	private Fragment content;
	
	private List<Fragment> fragmentList;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
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
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayHomeAsUpEnabled(true);
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
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}

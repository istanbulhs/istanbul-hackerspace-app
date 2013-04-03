package org.istanbulhs.istanbulhackerspaceapp;

import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.pagefragments.BlogListFragment;
import org.istanbulhs.istanbulhackerspaceapp.pagefragments.HackerspaceMapFragment;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity {

	private ViewPager viewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set the Above View
		setContentView(R.layout.pager);
		
		//Create fragments
		List<Fragment> fragmentList = this.initializeFragments();
		List<String> titleList = this.initializeFragmentTitles(); 
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), viewPager);
		adapter.addFragments(fragmentList);
		
		// set the Behind View
		setBehindContentView(R.layout.frame);
		SlidingMenuListFragment menuFragment = new SlidingMenuListFragment();
		menuFragment.setMenuItems(fragmentList, titleList);

		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		t.add(R.id.frame, menuFragment);
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
	
	
	private List<Fragment> initializeFragments() {
		
		List<Fragment> fragmentList = new ArrayList<Fragment>(5);
		
		fragmentList.add(new BlogListFragment());
		fragmentList.add(new HackerspaceMapFragment());
		
		return fragmentList;
	}
	
	private List<String> initializeFragmentTitles() {
		
		List<String> titleList = new ArrayList<String>(5);
		titleList.add("Anasayfa");
		titleList.add("Neredeyiz?");
		
		return titleList;
	}
	
	public void switchContent(int position) {
		
		viewPager.setCurrentItem(position);
		getSlidingMenu().showContent();
	}
	
	public class PagerAdapter extends FragmentPagerAdapter  {

		private List<Fragment> mFragments = new ArrayList<Fragment>();
		private ViewPager mPager;

		public PagerAdapter(FragmentManager fm, ViewPager vp) {
			super(fm);
			mPager = vp;
			mPager.setAdapter(this);
		}
		
		public void addFragments(List<Fragment> fragments) {
			mFragments.addAll(fragments);
		}
		
		
		@Override
		public Fragment getItem(int position) {
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			return mFragments.size();
		}

		public void onPageScrollStateChanged(int arg0) { }
		public void onPageScrolled(int arg0, float arg1, int arg2) { }

	
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

package org.istanbulhs.istanbulhackerspaceapp;

import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.screenfragments.BlogListFragment;

import com.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pager);
		
		List<Fragment> fragmentList = this.initializeFragments();
		
		ViewPager vp = (ViewPager) findViewById(R.id.pager);
		PagerAdapter adapter = new PagerAdapter(getFragmentManager(), vp);
		adapter.addFragments(fragmentList);
	
		// set the Behind View
		setBehindContentView(R.layout.frame);
		
		SlidingMenuListFragment menuFragment = new SlidingMenuListFragment();
		menuFragment.setMenuItems(fragmentList);
		
		FragmentTransaction t = this.getFragmentManager().beginTransaction();
		t.add(R.id.frame, menuFragment);
		t.commit();

		// customize the SlidingMenu
		this.setSlidingActionBarEnabled(true);
		getSlidingMenu().setShadowWidthRes(R.dimen.shadow_width);
		getSlidingMenu().setShadowDrawable(R.drawable.shadow);
		getSlidingMenu().setBehindOffsetRes(R.dimen.actionbar_home_width);
		getSlidingMenu().setBehindScrollScale(0.25f);
		
		// customize the ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}
	
	
	private List<Fragment> initializeFragments() {
		
		List<Fragment> fragmentList = new ArrayList<Fragment>(5);
		
		fragmentList.add(new BlogListFragment());
		fragmentList.add(new HackerspaceMapFragment());
		
		return fragmentList;
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

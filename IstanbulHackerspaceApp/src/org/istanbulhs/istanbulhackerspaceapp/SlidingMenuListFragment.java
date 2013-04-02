package org.istanbulhs.istanbulhackerspaceapp;

import java.util.ArrayList;
import java.util.List;


import com.slidingmenu.lib.app.SlidingFragmentActivity;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SlidingMenuListFragment extends ListFragment {

	private List<Fragment> fragmentList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		SlidingMenuAdapter adapter = new SlidingMenuAdapter(getActivity());
		
		if (fragmentList != null) {
			for (Fragment fragment : this.fragmentList) {
				adapter.add(new MenuItem(fragment));
			}
		}
		
		setListAdapter(adapter);
	}
	
	public void setMenuItems(List<Fragment> fragments) {
		this.fragmentList = fragments;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		SlidingFragmentActivity main =	(SlidingFragmentActivity)getActivity();
		main.toggle();
		
		super.onListItemClick(l, v, position, id);
	}



	private class MenuItem {
		public Fragment frag;
		public String title;
		public int iconRes;
		
		public MenuItem(Fragment fram, String title, int iconRes) {
			this.frag = frag; 
			this.title = title; 
			this.iconRes = iconRes;
		}
		
		public MenuItem(Fragment fragment) {
			this.frag = fragment;
			this.title = fragment.getTag();
			this.iconRes = android.R.drawable.btn_star;
		}
	}

	public class SlidingMenuAdapter extends ArrayAdapter<MenuItem> {

		private List<MenuItem> menuItemList;
		
		public SlidingMenuAdapter(Context context) {
			super(context, 0);
			this.menuItemList = new ArrayList<SlidingMenuListFragment.MenuItem>(10);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			MenuItem menuItem = this.menuItemList.get(position);
			title.setText(menuItem.title);
			
			return convertView;
		}
		
		
		
		@Override
		public void add(MenuItem object) {
			this.menuItemList.add(object);
			super.add(object);
		}
	}
}

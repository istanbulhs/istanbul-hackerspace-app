package org.istanbulhs.istanbulhackerspaceapp;

import java.util.ArrayList;
import java.util.List;

import com.slidingmenu.lib.app.SlidingFragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SlidingMenuListFragment extends ListFragment {

	private List<MenuItem> menuItemtList;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		SlidingMenuAdapter adapter = new SlidingMenuAdapter(getActivity(), menuItemtList);		
		setListAdapter(adapter);
	}
	
	public void setMenuItems(List<Fragment> fragments, List<String> titleList) {
		List<MenuItem> list = new ArrayList<SlidingMenuListFragment.MenuItem>(fragments.size());
		
		for (int i=0; i < fragments.size(); i++) {
			MenuItem menuItem = new MenuItem(fragments.get(i), titleList.get(i));
			list.add(menuItem);
		} 
		
		this.menuItemtList = list;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		MainActivity main =	(MainActivity)getActivity();
		main.switchContent(position);
		
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
		
		public MenuItem(Fragment fram, String title) {
			this(fram, title, android.R.drawable.btn_star);
		}
		
		public MenuItem(Fragment fragment) {
			this(fragment, fragment.getTag());
		}
	}

	public class SlidingMenuAdapter extends ArrayAdapter<MenuItem> {

		private List<MenuItem> menuItemList;
		
		public SlidingMenuAdapter(Context context, List<MenuItem> menuItemList) {
			super(context, 0, menuItemList);
			
			this.menuItemList = menuItemList;
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
		
	}
}

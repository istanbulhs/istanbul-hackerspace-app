package org.istanbulhs.istanbulhackerspaceapp.screenfragments;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.istanbulhs.istanbulhackerspaceapp.R;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//mustafa
import org.apache.http.conn.HttpHostConnectException;
import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;
import org.xmlrpc.android.XMLRPCFault;
import org.xmlrpc.android.XMLRPCSerializable;


public class BlogListFragment extends ListFragment {

	private XMLRPCClient client;
	private URI uri;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*
		SampleAdapter adapter = new SampleAdapter(getActivity());
		for (int i = 0; i < 20; i++) {
			adapter.add(new SampleItem(new Fragment(), "Başlık", android.R.drawable.btn_star));
		}
		setListAdapter(adapter);
		*/
		
		uri = URI.create("http://istanbulhs.org/xmlrpc.php");
		client = new XMLRPCClient(uri);
		
		try {
			Object obj = client.call("getPost", 1); 
			Log.i("hs", obj.getClass().getName());
		}
		catch (XMLRPCException e) {
			// TODO: handle exception
			Log.i("hs", "HATA 1");
		}
		
	}

	/*
	private class SampleItem {
		public Fragment frag;
		public String tag;
		public int iconRes;
		public SampleItem(Fragment fram, String tag, int iconRes) {
			this.frag = frag; 
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		private List<SampleItem> list;
		
		
		public SampleAdapter(Context context) {
			super(context, 0);
			
			this.list = new ArrayList<BlogListFragment.SampleItem>(10);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			
			SampleItem item = this.list.get(position);
			title.setText(item.tag);
			return convertView;
		}
		
		@Override
		public void add(SampleItem object) {
			this.list.add(object);
			
			super.add(object);
		}

	}
	*/
	
	
}

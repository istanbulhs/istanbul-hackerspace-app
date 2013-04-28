package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import java.net.URI;

import org.istanbulhs.istanbulhackerspaceapp.R;
import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BlogListFragment extends ListFragment {

	private XMLRPCClient client;
	private URI uri;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Resources resources = getResources();
		String username = resources.getString(R.string.blog_username);
		String password = resources.getString(R.string.blog_password);
		
		RetrieveBlogListAsyncTask task = new RetrieveBlogListAsyncTask();
		task.execute(username, password);
		
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
		
		
		//try {
			//Object obj = client.call("getPost", 1); 
			//Log.i("hs", obj.getClass().getName());
		//}
		//catch (XMLRPCException e) {
			// TODO: handle exception
		//	Log.i("hs", "HATA 1");
		//}
		
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
	
	private class RetrieveBlogListAsyncTask extends AsyncTask<String, Void, String> {
		
		@Override
		protected void onPreExecute() {
			uri = URI.create("http://istanbulhs.org/xmlrpc.php");
			client = new XMLRPCClient(uri);
			
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... params) {
			String username = params[0];
			String password = params[1];
			try {
				Object obj = client.call("wp.getPosts", 1, username, password); 
				Log.i("hs", obj.getClass().getName());
			}
			catch (XMLRPCException e) {
				// TODO: handle exception
				Log.e("hs", e.getMessage());
			}

			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}
}

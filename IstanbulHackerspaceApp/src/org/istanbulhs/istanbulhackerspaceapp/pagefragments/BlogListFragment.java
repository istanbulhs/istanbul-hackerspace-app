package org.istanbulhs.istanbulhackerspaceapp.pagefragments;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.istanbulhs.istanbulhackerspaceapp.R;
import org.istanbulhs.istanbulhackerspaceapp.data.RssEntity;
import org.istanbulhs.istanbulhackerspaceapp.parser.WordpressRssParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class BlogListFragment extends Fragment {

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		RetrieveBlogListAsyncTask task = new RetrieveBlogListAsyncTask();
		task.execute();
		
		return inflater.inflate(R.layout.fragment_blog_list, null);
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
	
	private class RetrieveBlogListAsyncTask extends AsyncTask<Void, Void, String> {
		
		@Override
		protected String doInBackground(Void... params) {
			try {
	            return loadXmlFromNetwork("http://istanbulhs.org/feed/");
	        } catch (IOException e) {
	            return getResources().getString(R.string.connection_error);
	        } catch (XmlPullParserException e) {
	            return getResources().getString(R.string.xml_error);
	        }
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.i("hs", result);
			
			if (result != null) {
		        Activity mainActivity = getActivity();
		        
		        if (mainActivity != null) {
			        WebView myWebView = (WebView) mainActivity.findViewById(R.id.bloglist_web_view);
			        
			        if (myWebView != null) {
			        	WebSettings settings = myWebView.getSettings();
			        	settings.setDefaultTextEncodingName("utf-8");
			        	myWebView.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
			        }
		        }
			} else {
				//TODO: Veri getirilemedi gibi bir hata penceresi goster.. Tekrar dene butonu olsun vs
			}

			super.onPostExecute(result);
		}
		
		private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
		    InputStream stream = null;
		    WordpressRssParser wordpressRssParser = new WordpressRssParser();
		    List<RssEntity> entries = null;
		    
		    Calendar rightNow = Calendar.getInstance(); 
		    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd h:mmaa", Locale.US);
		           
		    StringBuilder htmlString = new StringBuilder();
		    htmlString.append("<h3>" + getResources().getString(R.string.page_title) + "</h3>");
		    htmlString.append("<em>" + getResources().getString(R.string.updated) + " " + 
		            formatter.format(rightNow.getTime()) + "</em>");
		        
		    try {
		        stream = downloadUrl(urlString);   
		        
		        entries = wordpressRssParser.parse(stream);
		    } finally {
		        if (stream != null) {
		            stream.close();
		        } 
		     }
		    
		    for (RssEntity entry : entries) {       
		        htmlString.append("<p><a href='");
		        htmlString.append(entry.getLink());
		        htmlString.append("'>" + entry.getTitle()+ "</a></p>");
		        htmlString.append(entry.getDescription());
		    }
		    
		    return htmlString.toString();
		}

		// Given a string representation of a URL, sets up a connection and gets
		// an input stream.
		private InputStream downloadUrl(String urlString) throws IOException {
		    URL url = new URL(urlString);
		    
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setReadTimeout(10000 /* milliseconds */);
		    conn.setConnectTimeout(15000 /* milliseconds */);
		    conn.setRequestMethod("GET");
		    conn.setDoInput(true);
		    // Starts the query
		    conn.connect();
		    
		    return conn.getInputStream();
		}

	}
}

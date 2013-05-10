/*
 * 
 * 	OSFW for Android v1.0
 * 
 * 	OSFW is a library which includes open source
 * 	frameworks for various programming languages.
 * 	OSFW is free and will always be free.
 * 
 * 	Web: http://osfw.org
 * 	Twitter: @osfworg
 * 
 */

package org.istanbulhs.istanbulhackerspaceapp.util;

import java.io.IOException;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class OSFW {

	public void Message(String Title, String Message, String ButtonLabel, Context context) {
		
		AlertDialog.Builder adb = new AlertDialog.Builder(context);
		adb.setTitle(Title);
		adb.setMessage(Message);
		adb.setCancelable(false);
		adb.setPositiveButton(ButtonLabel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog adb2 = adb.create();
		adb2.show();
		
	}
	
	public void Toast(String Message, String Length, Context context) {
		
		if (Length.equals("long")) {
			Toast.makeText(context, Message, Toast.LENGTH_LONG).show();
		} else if (Length.equals("short")) {
			Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
		}
		
	}
	
	public int Random(int Minimum, int Maximum) {
		
		int number = 0;
		Random random;
		random = new Random();
		number = random.nextInt((Maximum-Minimum)) + Minimum;
		return number;
		
	}
	
	public void ListView(ListView lv, final Context context, final String[] StringArray, String ClickMethod) {
		
		lv.setTextFilterEnabled(true);
		lv.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, StringArray));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				
				Toast.makeText(context, StringArray[pos], Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	
	public String Http(String URL) {
		
		String response_str = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(URL);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			response_str = client.execute(request, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response_str;
		
	}
	
}

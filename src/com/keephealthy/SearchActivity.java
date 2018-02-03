package com.keephealthy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SearchActivity extends Activity {
	private TextView answer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
		        .detectDiskReads().detectDiskWrites().detectNetwork()  
		        .penaltyLog().build());  
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
		        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()  
		        .penaltyLog().penaltyDeath().build());  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		tulingjiqiren();
	}
	protected void tulingjiqiren() {
		Intent intent=getIntent();
		String qu = intent.getStringExtra("question");
		try {
			qu = URLEncoder.encode(qu, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String strurl = "http://www.tuling123.com/openapi/api?key=328971e65c114a05b8cfcd20d476b0f1&info=+" + qu;
		URL url = null;
		try {
			url = new URL(strurl);
			HttpURLConnection connection = null;
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
			connection.connect();
			InputStream inStream = connection.getInputStream();
			byte[] buf = new byte[1024];
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int n = 0;
			while ((n = inStream.read(buf)) != -1) {
				outStream.write(buf, 0, n);
			}
			inStream.close();
			outStream.close();
			String res = outStream.toString();
			res = res.substring(23, res.length() - 2);
			answer= (TextView) findViewById(R.id.answer);
			answer.setText(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

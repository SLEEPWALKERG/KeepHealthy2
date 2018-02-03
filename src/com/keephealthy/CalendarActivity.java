package com.keephealthy;

import com.keephealthy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RatingBar;
import android.widget.TextView;

public class CalendarActivity extends Activity {
	private TextView btnBackToMainFromCalendar;
	private RatingBar rbShenTi;
	private RatingBar rbXinQing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_calendar);
		//返回主界面
		backToMainActivity();
	}
    /**
     * 返回主界面
     */
    private void backToMainActivity() {
		// TODO 自动生成的方法存根
    	btnBackToMainFromCalendar=(TextView) findViewById(R.id.btnBackToMainFromCalendar);
    	btnBackToMainFromCalendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				startActivity(new Intent(CalendarActivity.this,MainActivity.class));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calendar, menu);
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

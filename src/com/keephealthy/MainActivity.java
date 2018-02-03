package com.keephealthy;

import com.keephealthy.ChartActivity;
import com.keephealthy.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.test.InstrumentationTestCase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
	//����
	private EditText searchContent;
	private ImageButton btnSearch;
	private TextView btnHospital;
	private TextView btnCalendar;
	private TextView btnChart;
	private TextView btnExercise;
	
	private TextView btnBackToMainActivity;
	
	/*public static String question() {
		return searchContent.getText().toString();
	}*/
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		//��������
		initSearch();
		//����ҽԺģ��
		searchNearHospital();
		//��������
		openCalendar();
		//��������
		openChart();
		
		
		
		
	}
	/**
	 * ��������
	 */
	private void openChart() {
		// TODO �Զ����ɵķ������
		btnChart=(TextView) findViewById(R.id.btnChart);
		btnChart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,ChartActivity.class));
				
			}
		});
		
	}
	/**
	 * ��������ģ��
	 */
	private void openCalendar() {
		// TODO �Զ����ɵķ������
		btnCalendar=(TextView) findViewById(R.id.btnCalendar);
		btnCalendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				startActivity(new Intent(MainActivity.this,CalendarActivity.class));
				
			}
		});
		
		
	}

	/**
	 * ����ҽԺ��
	 */
	private void searchNearHospital() {
		// ������ҽԺͼ�����ü���
		btnHospital=(TextView) findViewById(R.id.btnHospital);
		btnHospital.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//�������������������ҳ�ٶȵ�ͼ ���ڸ�Ϊapi
				Uri uri = Uri.parse("https://map.baidu.com/mobile/webapp/search/search/qt=s&wd=ҽԺ&c=289&searchFlag=bigBox&version=5&exptype=dep&src_from=webapp_all_bigbox&src=0&nb_x=13515414.57&nb_y=3640944.02&center_rank=1/vt=map"); 
				Intent it   = new Intent(Intent.ACTION_VIEW,uri); 
				startActivity(it); 
				
				
			}
		});
	}
	
	/**
	 * ��������
	 */
 
	private void initSearch() {
		// 设置监听
		btnSearch=(ImageButton) findViewById(R.id.btnSearch);
		btnSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				searchContent=(EditText) findViewById(R.id.searchContent);
				String que = searchContent.getText().toString();
				Intent intent =new Intent(MainActivity.this,SearchActivity.class);
				intent.putExtra("question", que);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.btnMenuAbout:
			//toast��
			//Toast.makeText(MainActivity.this, "�����޵��ſ��С�飬��Ա�й˹�Ƥ����˼����������", Toast.LENGTH_LONG).show();
			//break;
			//default:
			//	break;
			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 builder.setTitle("�����Ŷ���Ϣ");
			 builder.setMessage("����ʦ����ѧ\nָ����ʦ: ����\n��Ա:\n�������ѧ�뼼��17��������\n�������ѧ�뼼��17������\n������ѧ16�꼶��˼");
			 builder.setPositiveButton("ȷ��", null);
			 AlertDialog alertDialog = builder.create(); 
			 // ��ʾ�Ի���
			 alertDialog.show();  

		}
		return super.onOptionsItemSelected(item);
	}
}

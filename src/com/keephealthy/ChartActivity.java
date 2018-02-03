package com.keephealthy;

import java.util.ArrayList;
import java.util.List;
import com.keephealthy.R;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;


public class ChartActivity extends Activity {
	
	private TextView btnBackToMainFromChart;
	
	private LineChartView lineChart;
	
		
	
	String[] date = {"5-23","5-22","6-22","5-23","5-22","2-22","5-22","4-22","9-22","10-22","11-22","12-22","1-22","6-22","5-23","5-22","2-22","5-22","4-22","9-22","10-22","11-22","12-22","4-22","9-22","10-22","11-22","zxc"};//X��ı�ע
	int[] score= {10,9,8,10,6,8,2,6};//ͼ�������
	private List<PointValue> mPointValues = new ArrayList<PointValue>();
	private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chart);
        
        backToMainFromChart();
        
        lineChart = (LineChartView)findViewById(R.id.line_chart);
        getAxisXLables();//��ȡx��ı�ע
        getAxisPoints();//��ȡ�����
        initLineChart();//��ʼ��
        
        
        
       
        
    
	}
	
	
    
    private void backToMainFromChart() {
		// TODO �Զ����������
    	btnBackToMainFromChart=(TextView) findViewById(R.id.btnBackToMainFromChart);
    	btnBackToMainFromChart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				startActivity(new Intent(ChartActivity.this,MainActivity.class));
			}
		});
	}

	/**
     * ��ʼ��LineChart��һЩ����
     */
    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //���ߵ���ɫ
	    List<Line> lines = new ArrayList<Line>();    
	    line.setShape(ValueShape.CIRCLE);//����ͼ��ÿ�����ݵ����״  ������Բ�� �������� ��ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE��
	    line.setCubic(false);//�����Ƿ�ƽ��
//	    line.setStrokeWidth(3);//�����Ĵ�ϸ��Ĭ����3
		line.setFilled(false);//�Ƿ�������ߵ����
		line.setHasLabels(true);//���ߵ����������Ƿ���ϱ�ע
//		line.setHasLabelsOnlyForSelected(true);//�������������ʾ���ݣ����������line.setHasLabels(true);����Ч��
		line.setHasLines(true);//�Ƿ���ֱ����ʾ�����Ϊfalse ��û������ֻ�е���ʾ	
		line.setHasPoints(true);//�Ƿ���ʾԲ�� ���Ϊfalse ��û��ԭ��ֻ�е���ʾ	
	    lines.add(line);  
	    LineChartData data = new LineChartData();  
	    data.setLines(lines);  
	      
	    //������  
	    Axis axisX = new Axis(); //X��  
	    axisX.setHasTiltedLabels(true);  //X������������������б����ʾ����ֱ�ģ�true��б����ʾ 
//	    axisX.setTextColor(Color.WHITE);  //����������ɫ
	    axisX.setTextColor(Color.parseColor("#D6D6D9"));//��ɫ
	    
//	    axisX.setName("δ�����������");  //�������
	    axisX.setTextSize(11);//���������С
	    axisX.setMaxLabelChars(7); //��༸��X�����꣬��˼�������������X�������ݵĸ���7<=x<=mAxisValues.length
	    axisX.setValues(mAxisXValues);  //���X�����������
	    data.setAxisXBottom(axisX); //x ���ڵײ�     
//	    data.setAxisXTop(axisX);  //x ���ڶ���
	    axisX.setHasLines(true); //x ��ָ���
	    
	    
	    Axis axisY = new Axis();  //Y��  
	    axisY.setName("����״��");//y���ע
	    axisY.setTextSize(11);//���������С
	    data.setAxisYLeft(axisY);  //Y�����������
      //data.setAxisYRight(axisY);  //y���������ұ�
	  //������Ϊ���ԣ�֧�����š������Լ�ƽ��  
	    lineChart.setInteractive(true); 
	    lineChart.setZoomType(ZoomType.HORIZONTAL);  //�������ͣ�ˮƽ
	    lineChart.setMaxZoom((float) 3);//���ű���
	    lineChart.setLineChartData(data);  
	    lineChart.setVisibility(View.VISIBLE);
	    /**ע�������7��10ֻ�Ǵ���һ������ȥ��ȶ���
	     * ���������Ӻ����࣡��������http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2��;
	     * ���漸���������X�����ݵ���ʾ������x��0-7�����ݣ��������ݵ����С�ڣ�29����ʱ����С������hellochartĬ�ϵ���������ʾ�������ݵ�������ڣ�29����ʱ��
	     * ��������axisX.setMaxLabelChars(int count)��仰,����Զ�����X��������ʾ�ľ������ʵ����ݸ�����
	     * ������axisX.setMaxLabelChars(int count)��仰,
	     * 33�����ݵ���ԣ��� axisX.setMaxLabelChars(10);�����10����v.right= 7; �����7����
	                     �տ�ʼX����ʾ7�����ݣ�Ȼ�����ŵ�ʱ��X��ĸ����ᱣ֤����7С��10
	  	         ��С��v.right= 7;�е�7,�����Ҹо��������䶼����ʧЧ�˵����� - -!
	     * ����Y���Ǹ������ݵĴ�С�Զ�����Y������
	     * ����������� v.right= 7; ��仰����ͼ��տ�ʼ�ͻᾡ���ܵ���ʾ�������ݣ�������̫��
	     */
	    Viewport v = new Viewport(lineChart.getMaximumViewport()); 
		  v.left = 0; 
		  v.right= 7; 
		  lineChart.setCurrentViewport(v);
	    }
	    
	    /**
	     * X �����ʾ
	     */
	    private void getAxisXLables(){
	        for (int i = 0; i < date.length; i++) {    
	        	mAxisXValues.add(new AxisValue(i).setLabel(date[i]));    
	        }    	
	    }
	    /**
	     * ͼ���ÿ�������ʾ
	     */
	    private void getAxisPoints(){
	        for (int i = 0; i < score.length; i++) {    
	        	mPointValues.add(new PointValue(i, score[i]));      
	        }    	
	    }
}

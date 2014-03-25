package com.cai.reader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.cai.reader.util.ScrawSurfaceView;

public class ScrawlGameActivity extends Activity {


    private ScrawSurfaceView surfaceView;
    private GestureDetector detector;
    private  Canvas canvas;
 private Paint paint;
	private void initView(){
		surfaceView=new ScrawSurfaceView(this);
		 canvas=new Canvas();
		paint=new Paint();
		detector=new GestureDetector(new MyGestureListener());
		
		
		
	}
  
	
	
   private class MyGestureListener extends SimpleOnGestureListener{
	   @Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		   if(surfaceView!=null&&canvas!=null){
			   canvas.drawColor(Color.WHITE);
			   //e1是先 e2是后
			   paint.setColor(Color.WHITE);
		   canvas.drawLine(e1.getX(), e1.getY(), e2.getX(), e2.getY(), paint);
			surfaceView.draw(canvas);
			Log.i("info", "1:"+e1.getX()+",2:"+e2.getX()+",3:"+e1.getY()+",4:"+e2.getY());
		   }
		return super.onFling(e1, e2, velocityX, velocityY);
	}
   };
	
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       initView();
	       setContentView(surfaceView);
	     
	    }
 @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
 }
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
	onTouchEvent(ev);
	return super.dispatchTouchEvent(ev);
}
}
package com.naver.cafe.android.simple;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.cafe.android.IChing;
import com.naver.cafe.android.IChingElement;
import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;
import com.naver.cafe.android.SimpleFortuneTellResult;

public class SimpleFortuneTell3D1 extends ShareActivity implements SensorListener {
	
	IChingElement iching = new IChingElement();
	
	boolean animationFlag = true;
	boolean veryFirstFlag = true;
	int currentHyo = 0;
	String[] strKorOrder = {"첫","두","세","네","다섯","여섯"};
    AnimationDrawable animation;
    int resultNum = 0;
    
	private SensorManager sensorMgr;
	private long lastUpdate = -1;
	private long lastPickup = -1;
	private float x_new, y_new, z_new;
	private float x_old, y_old, z_old;
	private static final int SHAKE_THRESHOLD = 900;

/////////////////////////////	
	private GLSurfaceView surface;
	private GlRenderer renderer;
	
	private int move = 0;
	private float turn = 0;
	private boolean paused = false;

	private float touchTurn = 0;
	private float touchTurnUp = 0;

	private float xpos = -1;
	private float ypos = -1;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.simple_iching_layout);
        setBtn(R.layout.simple_iching_layout);

        //surface = (GLSurfaceView) findViewById(R.id.img);
		//mGLView = new GLSurfaceView(getApplication());
		surface = new GLSurfaceView(getApplication());

//        surface.setEGLConfigChooser(new GLSurfaceView.EGLConfigChooser() {
//			public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
//				// Ensure that we get a 16bit framebuffer. Otherwise, we'll fall
//				// back to Pixelflinger on some device (read: Samsung I7500)
//				int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
//				EGLConfig[] configs = new EGLConfig[1];
//				int[] result = new int[1];
//				egl.eglChooseConfig(display, attributes, configs, 1, result);
//				return configs[0];
//			}
//		});
		
        surface.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        surface.getHolder().setFormat(PixelFormat.RGBA_8888);
        
        surface.setZOrderOnTop(true); 
        
		renderer = new GlRenderer(this);
		renderer.setSleepTime(500);
		surface.setRenderer(renderer);

	   initAll();	
	   startAnimation();
/*        
        animation = new AnimationDrawable();
        initAll();
        animation.setOneShot(false);
        setAniData(animation);
         
        ImageView imageAnim =  (ImageView) findViewById(R.id.img);
        
        imageAnim.setBackgroundDrawable(animation);
*/        
        if (veryFirstFlag) {
            Context ctx = getApplicationContext();
        	showStartDialog(ctx);
        	veryFirstFlag = false;
        }
        
//        // run the start() method later on the UI thread
//        imageAnim.post(new Starter());
//        
//        imageAnim.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                pickHyo();
//            }
//        });
        
        ImageButton imgbtn =  (ImageButton) findViewById(R.id.image_button);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	initAll();
            	startAnimation();
            }
        });
        
    }
    
    class Starter implements Runnable { 
        public void run() {
            animation.start();        
        }
    }
    private void startAnimation() {
        //animation.setVisible(true,true);
        //animation.run();
        renderer.setRotateStatus(true, false);
    }

    private void stopAnimation() {
        //animation.stop();
        animation.setVisible(false,false);
    }
        
    private void showJumDialog(int num) {
	    new AlertDialog.Builder(SimpleFortuneTell3D1.this)
		.setTitle("주역점 결과보기")
		.setMessage("간단결과를 확인 하시려면 결과보기를 누르세요!") //줄였음
		.setPositiveButton("결과보기", new DialogInterface.OnClickListener(){
		    public void onClick(DialogInterface dialog, int whichButton){
		    	Intent i = new Intent(SimpleFortuneTell3D1.this,SimpleFortuneTellResult.class);
		    	i.putExtra("num",resultNum+"");
		    	startActivityForResult(i,1);
		    }
		})
		.setNegativeButton("취    소", new DialogInterface.OnClickListener(){
		    public void onClick(DialogInterface dialog, int whichButton){
		    	return; 
		    }
		})
		.show();
    }
    
    private void showStartDialog(Context mContext) {
    	
    	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    	View layout = inflater.inflate(R.layout.iching_start_dialog,(ViewGroup) findViewById(R.id.layout_root));
    	TextView text = (TextView) layout.findViewById(R.id.text);
    	String tt = "점치고자 하는 내용을 주의 깊게 생각해 주십시요\n\n";
    	tt+="생각이 정리되면 창을 닫고, 기기를 힘차게 여섯번 흔들어 주십시요\n\n";
    	tt+="기기를 흔들때마다 음,양의 효가 하나씩 생성됩니다.\n\n";
    	tt+="육효가 완성되면 점친 결과를 볼수 있습니다.\n\n";
    	tt+="행운을 빕니다!!";
    	text.setText(tt); 
    	ImageView image = (ImageView) layout.findViewById(R.id.image);
    	image.setImageResource(R.drawable.simple_on);
    	
    			
	    new AlertDialog.Builder(SimpleFortuneTell3D1.this)
		.setTitle("주역점치기")
		.setPositiveButton("창닫기", new DialogInterface.OnClickListener(){
		    public void onClick(DialogInterface dialog, int whichButton){
		    	//do nothing;;
		    	return; 
		    }
		})
//		.setNegativeButton("취    소", new DialogInterface.OnClickListener(){
//		    public void onClick(DialogInterface dialog, int whichButton){
//		        
//		    }
//		})
		.setView(layout)
		.show();
    }
    
    
    
    private void drawText(int ititle) {
    	
    	TextView textTmp = (TextView)findViewById(R.id.TextViewTmp);   	textTmp.setText(ititle);
    	String strResult = (String)textTmp.getText();
    	int sp = strResult.indexOf("(");
    	String strKor = strResult.substring(0, sp);
    	String strChi = strResult.substring(sp);
    	TextView textKor = (TextView)findViewById(R.id.TextView03);    	textKor.setText(strKor);
    	TextView textChi = (TextView)findViewById(R.id.TextView04);    	textChi.setText(strChi);
    }
    
    private void drawHyo(int num, int drw) {
    	//int drw = -1;
    	//int i = iching.getRandom();
    	
    	if(drw==IChing.YANG) drw =R.drawable.yang;
    	else drw = R.drawable.yin;
    	
    	switch (num) {
    	case IChing.FIRST_HYO :
    		ImageView one = (ImageView)findViewById(R.id.one);
            one.setImageResource(drw);
            one.setVisibility(View.VISIBLE);
            break;
    	case IChing.SECOND_HYO :
    		ImageView two = (ImageView)findViewById(R.id.two);
            two.setImageResource(drw);
            two.setVisibility(View.VISIBLE);
            break;
    	case IChing.THIRD_HYO :
    		ImageView three = (ImageView)findViewById(R.id.three);
            three.setImageResource(drw);
            three.setVisibility(View.VISIBLE);
            break;
    	case IChing.FOURTH_HYO :
    		ImageView four = (ImageView)findViewById(R.id.four);
            four.setImageResource(drw);
            four.setVisibility(View.VISIBLE);
            
            break;
    	case IChing.FIFTH_HYO :
    		ImageView five = (ImageView)findViewById(R.id.five);
            five.setImageResource(drw);
            five.setVisibility(View.VISIBLE);
            break;
    	case IChing.SIXTH_HYO :
    		ImageView six = (ImageView)findViewById(R.id.six);
            six.setImageResource(drw);
            six.setVisibility(View.VISIBLE);
            break;
    	}
    }
    
    
    private void pickHyo() {
    	
    	if(animationFlag) { 
        	//if(animation.isRunning()) { 
                //stopAnimation();
        		Toast toast = Toast.makeText(this, strKorOrder[currentHyo]+"번째 효를 뽑았습니다.", Toast.LENGTH_SHORT);
        		toast.setGravity(Gravity.BOTTOM, 0, 0);
        		toast.show();
        		Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        		vibe.vibrate(100); 
        		
                currentHyo ++;
                int yinyang = iching.getRandom();
                drawHyo(currentHyo,yinyang);
                iching.setHYO(currentHyo,yinyang );
                if(currentHyo == 6) {
                	//stopAnimation();
                	renderer.setRotateStatus(false, false);
                	// 추후 정지 코드 추가
                	animationFlag = false;
                	resultNum = iching.getSelectedHyoNum();
                	ImageButton ib = (ImageButton)findViewById(R.id.image_button);
            		ib.setVisibility(View.VISIBLE);
            		
                	//XmlResourceParser data = getResources().getXml(iching.getLayout(resultNum));
                	//SimpleIchingDataVO idvo = iching.getIchingDataVO(data);
            		int ititle = iching.getIchingTitle(resultNum);
                	drawText(ititle);
                	Log.e("hyo","total num of hyo >> >>>>>>>>>>>> " + resultNum);
                	showJumDialog(resultNum) ;
                }else {
//                    try{
//                    	Thread.sleep(500);
//                    	//startAnimation();
//                    }catch (Exception ie){};
                	renderer.setRotateStatus(false, true);
                }
            //} else { 
                //startAnimation();		//3d 
            	
            //}
    	} 
    }
    
    private void initAll() {

		// motion sensor
		sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		boolean accelSupported = sensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
		if (!accelSupported) {
			sensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
		}

    	animationFlag = true;
    	currentHyo = 0;
    	ImageView one = (ImageView)findViewById(R.id.one);
    	one.setVisibility(View.INVISIBLE);
		ImageView two = (ImageView)findViewById(R.id.two);
		two.setVisibility(View.INVISIBLE);
		ImageView three = (ImageView)findViewById(R.id.three);
		three.setVisibility(View.INVISIBLE);
		ImageView four = (ImageView)findViewById(R.id.four);
		four.setVisibility(View.INVISIBLE);
		ImageView five = (ImageView)findViewById(R.id.five);
		five.setVisibility(View.INVISIBLE);
		ImageView six = (ImageView)findViewById(R.id.six);
		six.setVisibility(View.INVISIBLE);
		TextView tv01 = (TextView)findViewById(R.id.TextViewTmp);
		tv01.setText("");
		TextView tv02 = (TextView)findViewById(R.id.TextView03);
		tv02.setText("");
		TextView tv04 = (TextView)findViewById(R.id.TextView04);
		tv04.setText("");
		
		ImageButton ib = (ImageButton)findViewById(R.id.image_button);
		ib.setVisibility(View.INVISIBLE);
    }
    
    
    private void setAniData(AnimationDrawable animation) {
    	int delay = 30;
    	animation.addFrame(getResources().getDrawable(R.drawable.a01), delay); 
    	//animation.addFrame(getResources().getDrawable(R.drawable.a02), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a03), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a04), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a05), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a06), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a07), delay); 
    	//animation.addFrame(getResources().getDrawable(R.drawable.a08), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a09), delay);
//   	animation.addFrame(getResources().getDrawable(R.drawable.a10), delay);

    	animation.addFrame(getResources().getDrawable(R.drawable.a11), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a12), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a13), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a14), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a15), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a16), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a17), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a18), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a19), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a20), delay);


    	animation.addFrame(getResources().getDrawable(R.drawable.a21), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a22), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a23), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a24), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a25), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a26), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a27), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a28), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a29), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a30), delay);


    	animation.addFrame(getResources().getDrawable(R.drawable.a31), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a32), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a33), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a34), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a35), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a36), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a37), delay);
    	//animation.addFrame(getResources().getDrawable(R.drawable.a38), delay);
    	animation.addFrame(getResources().getDrawable(R.drawable.a39), delay);
    	//nimation.addFrame(getResources().getDrawable(R.drawable.a40), delay);
    }

	public void onSensorChanged(int sensor, float[] values) {
		if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
			long curTime = System.currentTimeMillis();
			
			// pick it up one time each 1000ms
			if((curTime-lastPickup)<1000) return;
			
			long diffTime = curTime - lastUpdate;
			if (diffTime>100) {
				lastUpdate = curTime;

				x_new = values[SensorManager.DATA_X];
				y_new = values[SensorManager.DATA_Y];
				z_new = values[SensorManager.DATA_Z];

				float speed = Math.abs( (x_new+y_new+z_new) - (x_old+y_old+z_old) ) / diffTime * 10000;
				if (speed > SHAKE_THRESHOLD) {
					// Shake !!
					lastPickup = curTime;
					//Toast.makeText(this, "Shake !!! " + speed, Toast.LENGTH_SHORT).show();
					pickHyo();
				}
				
				x_old = x_new;
				y_old = y_new;
				z_old = z_new;
			}
		}		
	}

	
	public void onAccuracyChanged(int sensor, int accuracy) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void onPause() {
		paused = true;

		if (sensorMgr != null) {
			sensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
			sensorMgr = null;
		}		

		super.onPause();
		surface.onPause();
	}

	@Override
	protected void onResume() {
		paused = false;
		super.onResume();
		surface.onResume();
	}

	protected void onStop() {
		//renderer.stop();
		super.onStop();
	}
}

package com.naver.cafe.android;

import kr.co.taoists.android.util.gps.GPSManager;
import kr.co.taoists.android.util.net.NetManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.naver.cafe.android.chemistry.ChemistryChoice;
import com.naver.cafe.android.chemistry.ChemistryMain;
import com.naver.cafe.android.dayfortune.DayFortuneMain;
import com.naver.cafe.android.help.HelpMain;
import com.naver.cafe.android.simple.GlApp;
import com.naver.cafe.android.simple.SimpleFortuneTell3D1;

public class FortuneTellMain extends Activity {
	
	IChingElement iching = new IChingElement();
	
	boolean animationFlag = true;
	int currentHyo = 0;
    AnimationDrawable animation;
    private Context context = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.fortunetellmain);
        
//        DayFortuneOverNetworkUtil dnutil = new DayFortuneOverNetworkUtil(this, R.layout.fortunetellmain, 1);
//        String test = "";
//        try{
//        	test = dnutil.getDaumData();
//        } catch (Exception e){}
        
        
        
        final ImageButton btn1 = (ImageButton)findViewById(R.id.simple_on);
        //final ImageButton btn2 = (ImageButton)findViewById(R.id.chemistry_on);
        final ImageButton btn3 = (ImageButton)findViewById(R.id.forturn_on);
        final ImageButton btn4 = (ImageButton)findViewById(R.id.harmony_on); 
        final ImageButton btn5 = (ImageButton)findViewById(R.id.help_on);
        
        
		//클릭시 눌린 이미지 출력후 다시 원래 이미지로 변경
        btn1.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn1.setImageResource(R.drawable.simple_off);
				} else  {
					btn1.setImageResource(R.drawable.simple_on);
					Intent intent = new Intent(FortuneTellMain.this, SimpleFortuneTell.class);
//					Intent intent = new Intent(FortuneTellMain.this, SimpleFortuneTell3D1.class);
	        		startActivity(intent);
				}
				return false;
			}  
		});
        
//        btn2.setOnTouchListener(new View.OnTouchListener() {
//			public boolean onTouch(View v, MotionEvent event) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					btn2.setImageResource(R.drawable.detail_off);
//				} else  {
//					btn2.setImageResource(R.drawable.detail_on);
//					Intent intent = new Intent(FortuneTellMain.this, ChemistryMain.class);
//	        		startActivity(intent); 
//				}
//				return false;
//			}  
//		});
        
        btn3.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn3.setImageResource(R.drawable.fortune_off);
				} else  {
					btn3.setImageResource(R.drawable.fortune_on);
					Intent intent = new Intent(FortuneTellMain.this, DayFortuneMain.class);
	        		startActivity(intent);
				}
				return false;
			}  
		});
        
        btn4.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn4.setImageResource(R.drawable.harmony_off);
				} else  {
					btn4.setImageResource(R.drawable.harmony_on);
					makeChoice();
				}
				return false;
			}  
		});
        
        btn5.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn5.setImageResource(R.drawable.help_off);
				} else  {
					btn5.setImageResource(R.drawable.help_on);
					Intent intent = new Intent(FortuneTellMain.this, HelpMain.class);

	        		startActivity(intent);
				}
				return false;
			}  
		});
        
//		if(!NetManager.enableNet(context)){
//			setMsg("무선 인터넷","Wifi 및 3G 를 확인 후 시도해 주세요");
//		}        
        
        
//        Button menuBtn01 = (Button)findViewById(R.id.menuBtn01); 
//        Button menuBtn03 = (Button)findViewById(R.id.menuBtn03);
//        Button menuBtn04 = (Button)findViewById(R.id.menuBtn04); 
//        Button menuBtn05 = (Button)findViewById(R.id.menuBtn05);
//
//        menuBtn01.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(FortuneTellMain.this, SimpleFortuneTell.class);
//        		startActivity(intent);
//        	}
//        });
//        
//        menuBtn03.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(FortuneTellMain.this, DayFortuneMain.class);
//        		startActivity(intent);
//        	}
//        });
//        
//        menuBtn04.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(FortuneTellMain.this, IChingHistList.class);
//        		startActivity(intent);
//        	}
//        });
//        
//        menuBtn05.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(FortuneTellMain.this, HelpMain.class);
//        		startActivity(intent);
//        	}
//        });
    }
    
	private void makeChoice() {
		Intent intent = new Intent(FortuneTellMain.this, ChemistryMain.class);
		startActivity(intent);
		
//		new AlertDialog.Builder(FortuneTellMain.this).setMessage(
//				"모드를 선택 해주세요").setPositiveButton("혼자 보기",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int whichButton) {
//							Intent intent = new Intent(FortuneTellMain.this, ChemistryMain.class);
//			        		startActivity(intent);
//			        		dialog.cancel();
//						}
//				}).setNegativeButton("같이 보기",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int whichButton) {
//							
//							if((new GPSManager(context)).enableGps()){
//								if(NetManager.enableNet(context)){
//									Intent intent = new Intent(FortuneTellMain.this, ChemistryChoice.class);
//					        		startActivity(intent);
//					        		dialog.cancel();
//								} else {
//									setMsg("무선 인터넷","Wifi 및 3G 를 확인 후 시도해 주세요");
//								}    								
//							} else {
//								//setMsg("GPS","GPS 정보를 확인 후 시도해 주세요");
//								new AlertDialog.Builder(FortuneTellMain.this).setMessage(
//								"GPS 정보가 꺼져 있거나 \n현재 실내 입니다.").setPositiveButton("최근 정보로 보기",
//									new DialogInterface.OnClickListener() {
//										public void onClick(DialogInterface dialog, int whichButton) {
//											Intent intent = new Intent(FortuneTellMain.this, ChemistryChoice.class);
//							        		startActivity(intent);
//							        		dialog.cancel();
//										}
//								}).setNegativeButton("나중에 보기",
//										new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog, int whichButton) {
//						        		dialog.cancel();
//									}
//								})
//
//						.show();								
//							}
//
//						}
//
//				})
//
//		.show();
	}    
	
	public void setMsg(String msg, String msg2){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				return;
			}
		});
		
    	adb.setTitle(msg+" 기능 확인");
    	adb.setMessage(msg2);
    	adb.show();	
    	return ;
	}	
}

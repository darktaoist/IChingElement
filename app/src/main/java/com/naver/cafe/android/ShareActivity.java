package com.naver.cafe.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.naver.cafe.android.chemistry.ChemistryMain;
import com.naver.cafe.android.dayfortune.DayFortuneMain;
import com.naver.cafe.android.help.HelpMain;
import com.naver.cafe.android.simple.SimpleFortuneTell3D1;


/**
 * Activity 아래쪽 공통의 패널임 
 * @author taoist
 *
 */
public class ShareActivity extends Activity {
	
	public void setBtn(int ctx) {
		this.setContentView(ctx);
		final ImageButton btn1 = (ImageButton)findViewById(R.id.btn6);
		final ImageButton btn2 = (ImageButton)findViewById(R.id.btn1);
		final ImageButton btn3 = (ImageButton)findViewById(R.id.btn3);
		final ImageButton btn4 = (ImageButton)findViewById(R.id.btn4);
		final ImageButton btn5 = (ImageButton)findViewById(R.id.btn5);
		
		//클릭시 눌린 이미지 출력후 다시 원래 이미지로 변경
        btn1.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn1.setImageResource(R.drawable.btn_06_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn1.setImageResource(R.drawable.btn_06);
					Intent intent = new Intent(ShareActivity.this, FortuneTellMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		startActivity(intent);
				} 
				return false;
			} 
		}); 
         
        btn2.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn2.setImageResource(R.drawable.btn_01_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn2.setImageResource(R.drawable.btn_01);
					Intent intent = new Intent(ShareActivity.this, SimpleFortuneTell.class);
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		startActivity(intent);
				}
				return false;
			}
		});
        
        btn3.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn3.setImageResource(R.drawable.btn_03_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn3.setImageResource(R.drawable.btn_03);
					Intent intent = new Intent(ShareActivity.this, DayFortuneMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		startActivity(intent);
				}
				return false;
			}
		});
        
        btn4.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn4.setImageResource(R.drawable.btn_04_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn4.setImageResource(R.drawable.btn_04);
					Intent intent = new Intent(ShareActivity.this, ChemistryMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		startActivity(intent);
				}
				return false;
			}
		});
        
        btn5.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn5.setImageResource(R.drawable.btn_05_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn5.setImageResource(R.drawable.btn_05);
					Intent intent = new Intent(ShareActivity.this, HelpMain.class);
					intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
	        		startActivity(intent);
				}
				return false;
			}
		});
        
        
        
        
        
//        
//        
//        ImageView btn2 = (ImageView)findViewById(R.id.btn2); 
//        btn2.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(ShareActivity.this, SimpleFortuneTell.class);
//        		startActivity(intent);
//        	} 
//        });
//        
//        ImageView btn3 = (ImageView)findViewById(R.id.btn3); 
//        btn3.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(ShareActivity.this, IChingHistList.class);
//        		startActivity(intent);
//        	} 
//        });
//        
//        ImageView btn4 = (ImageView)findViewById(R.id.btn4); 
//        btn4.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(ShareActivity.this, FortuneTellMain.class);
//        		startActivity(intent);
//        	} 
//        }); 
//        
//        ImageView btn5 = (ImageView)findViewById(R.id.btn5); 
//        btn5.setOnClickListener(new Button.OnClickListener(){
//        	public void onClick(View v) { 
//        		Intent intent = new Intent(ShareActivity.this, FortuneTellMain.class);
//        		startActivity(intent);
//        	} 
//        });
	}
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	}
	
}


/*

이미지 버튼 변수 = (ImageButton) findViewById(R.id.xml에서 지정해준 아이디);
  이미지 버튼 변수.setOnTouchListener(new View.OnTouchListener() {
   public boolean onTouch(View v, MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
 이미지 버튼을 눌렀을때 이벤트 

이곳에 

 이미지 버튼 변수
       .setImageResource(R.drawable.바꿔줄 이미지);

     
 } else if (event.getAction() == MotionEvent.ACTION_UP) {

이미지 버튼을 땠을때 

 이미지 버튼 변수
       .setImageResource(R.drawable.바꿔줄 이미지);

해주면 이미지버튼을 눌렀을때 이미지를 바꿔주고 띠었을때 이미지를 바꿔는 설정해줄수도 있고 

이벤트를 넣어줄 수도 있습니다. 
    }
    return false;
   }
  });

*/

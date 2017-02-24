package com.naver.cafe.android;

import com.naver.cafe.android.chemistry.ChemistryMain;
import com.naver.cafe.android.dayfortune.DayFortuneMain;
import com.naver.cafe.android.help.HelpMain;
import com.naver.cafe.android.simple.SimpleFortuneTell3D1;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class CmnnBtnUtil {

	private static Activity act = null;
	
	public CmnnBtnUtil(){
		
	}
	public void setIChingCmnnBtn(Activity act , int ctx) {
		this.act = act;
		
		act.setContentView(ctx);
		final ImageButton btn1 = (ImageButton)act.findViewById(R.id.btn6);
		final ImageButton btn2 = (ImageButton)act.findViewById(R.id.btn1);
		final ImageButton btn3 = (ImageButton)act.findViewById(R.id.btn3);
		final ImageButton btn4 = (ImageButton)act.findViewById(R.id.btn4);
		final ImageButton btn5 = (ImageButton)act.findViewById(R.id.btn5);
		
		//클릭시 눌린 이미지 출력후 다시 원래 이미지로 변경
        btn1.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					btn1.setImageResource(R.drawable.btn_06_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					btn1.setImageResource(R.drawable.btn_06);
					Intent intent = new Intent(CmnnBtnUtil.act, FortuneTellMain.class);
					CmnnBtnUtil.act.startActivity(intent);
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
					Intent intent = new Intent(CmnnBtnUtil.act, SimpleFortuneTell3D1.class);
					CmnnBtnUtil.act.startActivity(intent);
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
					Intent intent = new Intent(CmnnBtnUtil.act, DayFortuneMain.class);
					CmnnBtnUtil.act.startActivity(intent);
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
					Intent intent = new Intent(CmnnBtnUtil.act, ChemistryMain.class);
					CmnnBtnUtil.act.startActivity(intent);
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
					Intent intent = new Intent(CmnnBtnUtil.act, HelpMain.class);
					CmnnBtnUtil.act.startActivity(intent);
				}
				return false;
			}
		});
	}
}


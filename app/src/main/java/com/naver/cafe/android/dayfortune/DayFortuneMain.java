package com.naver.cafe.android.dayfortune;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;

public class DayFortuneMain extends ShareActivity {
	
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.day_fortune_main);
	    setBtn(R.layout.day_fortune_main);
	    
	    final ImageButton mouse = (ImageButton)findViewById(R.id.mouse);
        mouse.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mouse.setImageResource(R.drawable.mouse_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					mouse.setImageResource(R.drawable.mouse);
					callIntent("1");
				}
				return false;  
			} 
		});
        
        final ImageButton cow = (ImageButton)findViewById(R.id.cow);
        cow.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					cow.setImageResource(R.drawable.cow_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					cow.setImageResource(R.drawable.cow);
					callIntent("2");
				}
				return false; 
			} 
		});
        
        final ImageButton tiger = (ImageButton)findViewById(R.id.tiger);
        tiger.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					tiger.setImageResource(R.drawable.tiger_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					tiger.setImageResource(R.drawable.tiger);
					callIntent("3");
				}
				return false; 
			} 
		});
        
        
        final ImageButton rabbit = (ImageButton)findViewById(R.id.rabbit);
        rabbit.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					rabbit.setImageResource(R.drawable.rabbit_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					rabbit.setImageResource(R.drawable.rabbit);
					callIntent("4");
				}
				return false; 
			} 
		});
        
        final ImageButton dragon = (ImageButton)findViewById(R.id.dragon);
        dragon.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					dragon.setImageResource(R.drawable.dragon_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					dragon.setImageResource(R.drawable.dragon);
					callIntent("5");
				}
				return false; 
			} 
		});
        
        
        final ImageButton snake = (ImageButton)findViewById(R.id.snake);
        snake.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					snake.setImageResource(R.drawable.snake_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					snake.setImageResource(R.drawable.snake);
					callIntent("6");
				}
				return false; 
			} 
		});
        
        final ImageButton horse = (ImageButton)findViewById(R.id.horse);
        horse.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					horse.setImageResource(R.drawable.horse_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					horse.setImageResource(R.drawable.horse);
					callIntent("7");
				}
				return false; 
			} 
		});
        
        final ImageButton lamb = (ImageButton)findViewById(R.id.lamb);
        lamb.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					lamb.setImageResource(R.drawable.lamb_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					lamb.setImageResource(R.drawable.lamb);
					callIntent("8");
				}
				return false; 
			} 
		});
        
        final ImageButton monkey = (ImageButton)findViewById(R.id.monkey);
        monkey.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					monkey.setImageResource(R.drawable.monkey_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					monkey.setImageResource(R.drawable.monkey);
					callIntent("9");
				}
				return false; 
			} 
		});
        
        final ImageButton chicken = (ImageButton)findViewById(R.id.chicken);
        chicken.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					chicken.setImageResource(R.drawable.chicken_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					chicken.setImageResource(R.drawable.chicken);
					callIntent("10");
				}
				return false; 
			} 
		});
        
        
        final ImageButton dog = (ImageButton)findViewById(R.id.dog);
        dog.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					dog.setImageResource(R.drawable.dog_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					dog.setImageResource(R.drawable.dog);
					callIntent("11");
				}
				return false; 
			} 
		});
        
        final ImageButton pig = (ImageButton)findViewById(R.id.pig);
        pig.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					pig.setImageResource(R.drawable.pig_);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					pig.setImageResource(R.drawable.pig);
					callIntent("12");
				}
				return false; 
			} 
		});
         
        
        
	 }
	
	private void callIntent(String tti) {
		Intent i = new Intent(DayFortuneMain.this,DayFortuneResult.class);
    	i.putExtra("num",tti+"");
    	startActivityForResult(i,1);  
	}

}

package com.naver.cafe.android.chemistry;


import java.text.DecimalFormat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;

public class ChemistryMain extends ShareActivity {

	public String sMale = "";
	public String sFemale = "";

	ImageButton maleImg = null;
	ImageButton femaleImg = null;
	TextView titleTextView = null;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.chemistry_main);

		maleImg = (ImageButton)findViewById(R.id.maleTti);
		maleImg.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					maleImg.setImageResource(R.drawable.mouse_);
					sMale = "";
				}
				return false;
			}
		});

		femaleImg = (ImageButton)findViewById(R.id.femaleTti);
		femaleImg.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					femaleImg.setImageResource(R.drawable.mouse_);
					sFemale = "";
				}
				return false;
			}
		});


		final ImageButton mouse = (ImageButton)findViewById(R.id.mouse);
		mouse.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mouse.setImageResource(R.drawable.mouse_);
					setChoice(1);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					mouse.setImageResource(R.drawable.mouse);
				}
				return false;
			}
		});

		titleTextView = (TextView) findViewById(R.id.chemistryFortuneResultTitle);

		final ImageButton cow = (ImageButton)findViewById(R.id.cow);
		cow.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					setChoice(2);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					cow.setImageResource(R.drawable.cow);
				}
				return false;
			}
		});

		final ImageButton tiger = (ImageButton)findViewById(R.id.tiger);
		tiger.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					tiger.setImageResource(R.drawable.tiger_);

					setChoice(3);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					tiger.setImageResource(R.drawable.tiger);

				}
				return false;
			}
		});


		final ImageButton rabbit = (ImageButton)findViewById(R.id.rabbit);
		rabbit.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					rabbit.setImageResource(R.drawable.rabbit_);
					setChoice(4);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					rabbit.setImageResource(R.drawable.rabbit);

				}
				return false;
			}
		});

		final ImageButton dragon = (ImageButton)findViewById(R.id.dragon);
		dragon.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					dragon.setImageResource(R.drawable.dragon_);
					setChoice(5);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					dragon.setImageResource(R.drawable.dragon);

				}
				return false;
			}
		});


		final ImageButton snake = (ImageButton)findViewById(R.id.snake);
		snake.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					snake.setImageResource(R.drawable.snake_);
					setChoice(6);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					snake.setImageResource(R.drawable.snake);

				}
				return false;
			}
		});

		final ImageButton horse = (ImageButton)findViewById(R.id.horse);
		horse.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					horse.setImageResource(R.drawable.horse_);
					setChoice(7);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					horse.setImageResource(R.drawable.horse);

				}
				return false;
			}
		});

		final ImageButton lamb = (ImageButton)findViewById(R.id.lamb);
		lamb.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					lamb.setImageResource(R.drawable.lamb_);
					setChoice(8);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					lamb.setImageResource(R.drawable.lamb);

				}
				return false;
			}
		});

		final ImageButton monkey = (ImageButton)findViewById(R.id.monkey);
		monkey.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					monkey.setImageResource(R.drawable.monkey_);
					setChoice(9);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					monkey.setImageResource(R.drawable.monkey);

				}
				return false;
			}
		});

		final ImageButton chicken = (ImageButton)findViewById(R.id.chicken);
		chicken.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					chicken.setImageResource(R.drawable.chicken_);
					setChoice(10);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					chicken.setImageResource(R.drawable.chicken);

				}
				return false;
			}
		});


		final ImageButton dog = (ImageButton)findViewById(R.id.dog);
		dog.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					dog.setImageResource(R.drawable.dog_);
					setChoice(11);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					dog.setImageResource(R.drawable.dog);

				}
				return false;
			}
		});

		final ImageButton pig = (ImageButton)findViewById(R.id.pig);
		pig.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					pig.setImageResource(R.drawable.pig_);
					setChoice(12);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					pig.setImageResource(R.drawable.pig);

				}
				return false;
			}
		});


		final ImageButton result = (ImageButton)findViewById(R.id.chemistry_result);
		result.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if(!sMale.equals("") && !sFemale.equals("")) {
						callIntent();
					} else {
						System.out.println("not choosed yet!!!!");
						//later gotta change dialog box;
						viewWaring();
					}
				}
				return false;
			}
		});

	}

	private void viewWaring(){
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		adb.setTitle("선택");
		if(sMale.equals(""))
			adb.setMessage("남자 파트너를 선택하세요");
		else
			adb.setMessage("여자 파트너를 선택하세요");

		adb.show();
	}

	private void setChoice(int tti){
		DecimalFormat dcf = new DecimalFormat("00");
		if("".equals(sMale)){
			maleImg.setImageResource(getTTiImg(""+tti));
			sMale = dcf.format(tti);
		}else{
			femaleImg.setImageResource(getTTiImg(""+tti));
			sFemale = dcf.format(tti);
		}


//	    ttStr2 = getTTi(tti);
//	    titleTextView.setText(ttStr+" 과(의) "+ttStr2+" 애정  띠 궁합은 ");
//	    idx2 = ""+tti;
	}

	private int getTTiImg(String num) {
		int rtn = 0;
		int n = Integer.parseInt(num);
		switch (n) {
			case 1 : rtn 	= R.drawable.mouse_; 		break;
			case 2 : rtn 	= R.drawable.cow_; 		break;
			case 3 : rtn 	= R.drawable.tiger_; 		break;
			case 4 : rtn 	= R.drawable.rabbit_; 		break;
			case 5 : rtn 	= R.drawable.dragon_; 		break;
			case 6 : rtn 	= R.drawable.snake_; 		break;
			case 7 : rtn 	= R.drawable.horse_; 		break;
			case 8 : rtn 	= R.drawable.lamb_; 		break;
			case 9 : rtn 	= R.drawable.monkey_; 		break;
			case 10 : rtn 	= R.drawable.chicken_; 		break;
			case 11 : rtn 	= R.drawable.dog_; 		break;
			case 12 : rtn 	= R.drawable.pig_; 		break;
			default:rtn		=	0;
		}
		return rtn;
	}

	private void makeChoice() {
//		new AlertDialog.Builder(ChemistryMain.this).setMessage(
//				"선택하신 띠의 성별을 선택해 주십시요").setPositiveButton("남 자",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int whichButton) {
//							sMale = cChoice;
//							System.out.println("current man :" +sMale + ",current woman:" + sFemale);
//						}
//				}).setNegativeButton("여 자",
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int whichButton) {
//							sFemale = cChoice;
//							System.out.println("current man :" +sMale + ",current woman:" + sFemale);
//						}
//				})
//
//		.show();
	}


	private void callIntent() {
		Intent i = new Intent(ChemistryMain.this,ChemistryResult.class);
		i.putExtra("male",sMale);
		i.putExtra("female",sFemale);
		startActivityForResult(i,1);
	}
} 

package com.naver.cafe.android;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class IChingHistEdit extends Activity implements RatingBar.OnRatingBarChangeListener {
	
	RatingBar mIndicatorRatingBar;
    private int rating = 3;	
    private int maxRatingCnt = 5;	
    private int defaultRatingCnt = 5;
    private String idx;
    
    static final int DATE_DIALOG_ID = 1;
    private int mYear;
    private int mMonth;
    private int mDay;
    
	private IChingDBMangger mIChingDBManger;
	
	private String CheoriMode = "I";
	private String mAnswerTextVal = "1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.iching_hist_edit);
		CmnnBtnUtil btnUtil = new CmnnBtnUtil();
		btnUtil.setIChingCmnnBtn(this,R.layout.iching_hist_edit);
		
//		mIChingDBManger = new IChingDBMangger(this);
//		mIChingDBManger.open();
		
    	mIChingDBManger = IChingDBMangger.setDBManager(this);
		
		EditText mTitleText = (EditText) findViewById(R.id.iching_hist_list_title);
		EditText mAskText = (EditText) findViewById(R.id.iching_hist_list_ask);
		WebView mAnswerText = (WebView) findViewById(R.id.iching_hist_list_answer); 
		EditText mResultText = (EditText) findViewById(R.id.iching_hist_list_result);
		EditText mAskDateText = (EditText) findViewById(R.id.iching_hist_list_ask_date);
		Bundle extras = getIntent().getExtras();
		
        mIndicatorRatingBar = (RatingBar) findViewById(R.id.iching_hist_list_accuracy);
		
		if (extras != null) {
			
//			mRowId = extras.getLong(IChingDBMangger.KEY_IDX);
//			String title = extras.getString(IChingDBMangger.KEY_TITLE);
//			String ask = extras.getString(IChingDBMangger.KEY_ASK);
//			String result = extras.getString(IChingDBMangger.KEY_REAL_RESULT);
//			String askDate = extras.getString(IChingDBMangger.KEY_ASK_DATE);
//			rating = Integer.parseInt(extras.getString(IChingDBMangger.KEY_ACCURACY));
//			
//			if (title != null) {
//				mTitleText.setText(title);
//			}
//			if (ask != null) {
//				mAskText.setText(ask);
//			}
//			if (askDate != null) {
//				mAskDateText.setText(DateUtil.getDateFormat(askDate));
//			    mYear = DateUtil.getYmdInt(askDate, Calendar.YEAR);
//		        mMonth = DateUtil.getYmdInt(askDate, Calendar.MONTH);
//		        mDay = DateUtil.getYmdInt(askDate, Calendar.DAY_OF_MONTH);
//System.out.println("mYear:"+mYear+"mMonth:"+mMonth+"mDay:"+mDay)		        ;
//			}
//			
			CheoriMode = extras.getString("CheoriMode");
			
			if("U".equals(extras.getString("CheoriMode"))){
				Cursor c = mIChingDBManger.getIChingHist(extras.getString(IChingDBMangger.KEY_IDX));
				startManagingCursor(c);
				ArrayList<Map<String,String>> m_orders = mIChingDBManger.getIChingHistByIdx(c,extras.getString(IChingDBMangger.KEY_IDX));
				if(m_orders.size() > 0){
					Map<String,String> map = (HashMap<String,String>)m_orders.get(0);
					
					Log.d("result",map.toString());

					idx = extras.getString(IChingDBMangger.KEY_IDX);
					mTitleText.setText((String)map.get(IChingDBMangger.KEY_TITLE));
					mAskText.setText((String)map.get(IChingDBMangger.KEY_ASK));
//					mAnswerText.setText((String)map.get(IChingDBMangger.KEY_ANSWER));
					
					int xmlIdx = 1;
					try{
						mAnswerTextVal = (String)map.get(IChingDBMangger.KEY_ANSWER);
						xmlIdx = Integer.parseInt(mAnswerTextVal);
					} catch (Exception e){}
					
					IChingElement iching = new IChingElement();
				    
					//XmlResourceParser data = getResources().getXml(iching.getLayout(xmlIdx));
		        	//SimpleIchingDataVO idvo = iching.getIchingDataVO(data);
		        	//mAnswerText.setText(idvo.formatAll());
					
					String url = iching.getHtmlFile(xmlIdx);
				    try{ 
				    	mAnswerText.loadUrl(url);
				    	mAnswerText.setWebViewClient(new WebViewClient());
			        } catch (Exception e){ 
					}
		        	
					
					mResultText.setText((String)map.get(IChingDBMangger.KEY_REAL_RESULT));
					String askDate = (String)map.get(IChingDBMangger.KEY_ASK_DATE);
					mAskDateText.setText(DateUtil.getDateFormat(askDate));
				    mYear = DateUtil.getYmdInt(askDate, Calendar.YEAR);
			        mMonth = DateUtil.getYmdInt(askDate, Calendar.MONTH);
			        mDay = DateUtil.getYmdInt(askDate, Calendar.DAY_OF_MONTH);
			        
	                mIndicatorRatingBar.setRating(Float.parseFloat((String)map.get(IChingDBMangger.KEY_ACCURACY)));
				}
			} else {
				String date = DateUtil.getDate();
				if (date != null) {
					mAskDateText.setText(DateUtil.getDateFormat(date));
				    mYear = DateUtil.getYmdInt(date, Calendar.YEAR);
			        mMonth = DateUtil.getYmdInt(date, Calendar.MONTH);
			        mDay = DateUtil.getYmdInt(date, Calendar.DAY_OF_MONTH);				
				}
				
				int xmlIdx = 1;
				try{
					mAnswerTextVal = (String)extras.getString("num");
					xmlIdx = Integer.parseInt(mAnswerTextVal);
				} catch (Exception e){}
				
				IChingElement iching = new IChingElement();
			    //XmlResourceParser data = getResources().getXml(iching.getLayout(xmlIdx));
	        	//SimpleIchingDataVO idvo = iching.getIchingDataVO(data);
	        	//mAnswerText.setText(idvo.formatAll());
				String url = iching.getHtmlFile(xmlIdx);
			    try{ 
			    	mAnswerText.loadUrl(url);
			    	mAnswerText.setWebViewClient(new WebViewClient());
		        } catch (Exception e){ 
				}
			}
		}
	
		ImageButton confirmButton = (ImageButton) findViewById(R.id.iching_hist_list_confirm); 
		ImageButton cancelButton = (ImageButton) findViewById(R.id.iching_hist_list_cancle); 
		

		
		confirmButton.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View view) {
		        Bundle bundle = new Bundle();
				EditText mTitleText = (EditText) findViewById(R.id.iching_hist_list_title);
				EditText mAskText = (EditText) findViewById(R.id.iching_hist_list_ask);
				WebView mAnswerText = (WebView) findViewById(R.id.iching_hist_list_answer);
				EditText mResultText = (EditText) findViewById(R.id.iching_hist_list_result);
				EditText mAskDateText = (EditText) findViewById(R.id.iching_hist_list_ask_date);

		        bundle.putString(IChingDBMangger.KEY_TITLE, mTitleText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ASK, mAskText.getText().toString());
//		        bundle.putString(IChingDBMangger.KEY_ANSWER, mAnswerText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ANSWER, mAnswerTextVal);
		        
		        bundle.putString(IChingDBMangger.KEY_REAL_RESULT, mResultText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ACCURACY, ""+mIndicatorRatingBar.getRating());
		        bundle.putString(IChingDBMangger.KEY_ASK_DATE, mAskDateText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_REAL_DATE, mAskDateText.getText().toString());

Log.d("CheoriMode =",CheoriMode);


		        if("I".equals(CheoriMode)){
		        	mIChingDBManger = IChingDBMangger.setDBManager(null);
			        mIChingDBManger.insertIChingHist(
			        		idx, 
			        		mTitleText.getText().toString(), 
			        		mAskText.getText().toString(), 
			        		mAnswerText.toString(), 
			        		mResultText.getText().toString(), 
			        		""+mIndicatorRatingBar.getRating(),
			        		mAskDateText.getText().toString().replace("-",""),
			        		DateUtil.getDate()
		        		);			        	
		        }else if("U".equals(CheoriMode)){
		        	mIChingDBManger = IChingDBMangger.setDBManager(null);
			        mIChingDBManger.updateIChingHist(
			        		idx, 
			        		mTitleText.getText().toString(), 
			        		mAskText.getText().toString(), 
			        		mAnswerText.toString(), 
			        		mResultText.getText().toString(), 
			        		""+mIndicatorRatingBar.getRating(),
			        		mAskDateText.getText().toString().replace("-",""),
			        		DateUtil.getDate()
		        		);		        	
		        }

		        Intent intent = new Intent();
		        intent.putExtras(bundle);
		        setResult(RESULT_OK, intent);
		        finish();
		  }
		}); 
		cancelButton.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View view) {
		        Bundle bundle = new Bundle();
				EditText mTitleText = (EditText) findViewById(R.id.iching_hist_list_title);
				EditText mAskText = (EditText) findViewById(R.id.iching_hist_list_ask);
				EditText mResultText = (EditText) findViewById(R.id.iching_hist_list_result);
				EditText mAskDateText = (EditText) findViewById(R.id.iching_hist_list_ask_date);

		        bundle.putString(IChingDBMangger.KEY_TITLE, mTitleText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ASK, mTitleText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_REAL_RESULT, mTitleText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ASK_DATE, mTitleText.getText().toString());
		        bundle.putString(IChingDBMangger.KEY_ACCURACY, ""+mIndicatorRatingBar.getRating());
		                
		        Intent intent = new Intent();
		        intent.putExtras(bundle);
		        setResult(RESULT_CANCELED, intent);
		        finish();
		  }
		}); 

	    Button dateButton = (Button) findViewById(R.id.calendarBtn);
	    dateButton.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View view) {
	    	  showDialog(DATE_DIALOG_ID);
	      }
	    });
	    
//        mIndicatorRatingBar.setOnClickListener(new RatingBar.OnClickListener() {
//        onRatingChanged(mIndicatorRatingBar,rating, true);
        mIndicatorRatingBar.setOnRatingBarChangeListener(this);
//        mIndicatorRatingBar.setOnClickListener( new View.OnClickListener() {
//
//        	public void onClick(View view) {
//				int numStars = mIndicatorRatingBar.getNumStars();
//
////				if (numStars == maxRatingCnt) {
////					mIndicatorRatingBar.setNumStars(0);
////				}else{
////					mIndicatorRatingBar.setNumStars(numStars+1);
////				}
//				
//				mIndicatorRatingBar.setNumStars(5); 
//				mIndicatorRatingBar.setRating(5); 
//				mIndicatorRatingBar.setStepSize(5); 				
//			}
//		});
        
//		Button listBtn = (Button) findViewById(R.id.hist_list); 
//		
//		listBtn.setOnClickListener(new View.OnClickListener() {
//		    public void onClick(View view) {
//		        Bundle bundle = new Bundle();
//		        Intent intent = new Intent();
//		        intent.putExtras(bundle);
//		        setResult(RESULT_OK, intent);
//		        finish();
//		  }
//		}); 
	}
	
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {
						
		final int numStars = ratingBar.getNumStars(); 
		if (mIndicatorRatingBar.getNumStars() != numStars) { 
			mIndicatorRatingBar.setNumStars(numStars); 
		} 
		if (mIndicatorRatingBar.getRating() != rating) { 
			mIndicatorRatingBar.setRating(rating); 
		} 
		final float ratingBarStepSize = ratingBar.getStepSize(); 
		if (mIndicatorRatingBar.getStepSize() != ratingBarStepSize) { 
			mIndicatorRatingBar.setStepSize(ratingBarStepSize); 
		 
		} 
	}	
	
	protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            mDateSetListener,
                            mYear, mMonth, mDay);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }    

    private void updateDisplay() {

	((EditText) findViewById(R.id.iching_hist_list_ask_date)).setText(
	        new StringBuilder()
	                // Month is 0 based so add 1
	        .append(mYear).append("-")
			.append(new DecimalFormat("00").format(mMonth + 1)).append("-")
	        .append(new DecimalFormat("00").format(mDay)).append(""));
	    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear,
                        int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
           };
}

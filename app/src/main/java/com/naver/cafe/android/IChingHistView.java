package com.naver.cafe.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;
import android.widget.TextView;

public class IChingHistView extends Activity {
	
	RatingBar mIndicatorRatingBar;
    private String idx;
    
	private IChingDBMangger mIChingDBManger;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.iching_hist_view);
		CmnnBtnUtil btnUtil = new CmnnBtnUtil();
		btnUtil.setIChingCmnnBtn(this,R.layout.iching_hist_view);
//		mIChingDBManger = new IChingDBMangger(this);
//		mIChingDBManger.open();
		
    	mIChingDBManger = IChingDBMangger.setDBManager(this);
		
		TextView mTitleText = (TextView) findViewById(R.id.iching_hist_view_title);
		TextView mAskText = (TextView) findViewById(R.id.iching_hist_view_ask);
		WebView mAnswerText = (WebView) findViewById(R.id.iching_hist_view_answer);
		TextView mResultText = (TextView) findViewById(R.id.iching_hist_view_result);
		TextView mAskDateText = (TextView) findViewById(R.id.iching_hist_view_ask_date);
		Bundle extras = getIntent().getExtras();
		 
        mIndicatorRatingBar = (RatingBar) findViewById(R.id.iching_hist_view_accuracy);
		
		if (extras != null) {
			mIChingDBManger = IChingDBMangger.mIChingDBMangger;
			Cursor c = mIChingDBManger.getIChingHist(extras.getString(IChingDBMangger.KEY_IDX));
			startManagingCursor(c);
			ArrayList<Map<String,String>> m_orders = mIChingDBManger.getIChingHistByIdx(c,extras.getString(IChingDBMangger.KEY_IDX));
			
			Map<String,String> map = (HashMap<String,String>)m_orders.get(0);
			Log.d("result",map.toString());
			idx = extras.getString(IChingDBMangger.KEY_IDX);
			mTitleText.setText((String)map.get(IChingDBMangger.KEY_TITLE));
			mAskText.setText((String)map.get(IChingDBMangger.KEY_ASK));
//			mAnswerText.setText((String)map.get(IChingDBMangger.KEY_ANSWER));
			
			int xmlIdx = 1;
			try{
				xmlIdx = Integer.parseInt((String)map.get(IChingDBMangger.KEY_ANSWER));
			} catch (Exception e){}
			
			IChingElement iching = new IChingElement();
		    
//			XmlResourceParser data = getResources().getXml(iching.getLayout(xmlIdx));
//        	SimpleIchingDataVO idvo = iching.getIchingDataVO(data);
//        	mAnswerText.setText(idvo.formatAll());
			String url = iching.getHtmlFile(xmlIdx);
		    try{ 
		    	mAnswerText.loadUrl(url);
		    	mAnswerText.setWebViewClient(new WebViewClient());
	        } catch (Exception e){ 
			}
	        
			mResultText.setText((String)map.get(IChingDBMangger.KEY_REAL_RESULT));
			String askDate = (String)map.get(IChingDBMangger.KEY_ASK_DATE);
			mAskDateText.setText(DateUtil.getDateFormat(askDate));
            mIndicatorRatingBar.setRating(Float.parseFloat((String)map.get(IChingDBMangger.KEY_ACCURACY)));
            
            
		
		}
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
}

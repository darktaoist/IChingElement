package com.naver.cafe.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class SimpleFortuneTellResult extends ShareActivity {
	
	public String idx;
	IChingElement iching = new IChingElement();
	
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.simple_iching_result);
	    setBtn(R.layout.simple_iching_result);
	    Bundle extras = getIntent().getExtras();
	     
	    TextView title = (TextView) findViewById(R.id.title);
	    //TextView titleTextView = (TextView) findViewById(R.id.detail);
	    
	    if (extras != null) {
		    idx = extras.getString("num");
		     
		    //XmlResourceParser data = getResources().getXml(iching.getLayout(Integer.parseInt(idx)));
        	//SimpleIchingDataVO idvo = iching.getIchingDataVO(data);
        	title.setText(iching.getIchingTitle(Integer.parseInt(idx))); 
        	//titleTextView.setText(idvo.formatAll());
		    
		    WebView wb = (WebView)findViewById(R.id.detail);
		    wb.setBackgroundColor(0); 
		    String url = iching.getHtmlFile(Integer.parseInt(idx));
		    try{  
	        	wb.loadUrl(url);
	        	wb.setWebViewClient(new WebViewClient());
	        } catch (Exception e){} 
			}
	     
//	    ImageButton newButton = (ImageButton) findViewById(R.id.iching_new);
//	    newButton.setOnClickListener(new OnClickListener() { 
//	      public void onClick(View view) {
//	    	  Intent i = new Intent(SimpleFortuneTellResult.this, IChingHistEdit.class);
//	    	   Bundle bundle = new Bundle();
//	    	   bundle.putString("CheoriMode", "I");
//	    	   bundle.putString("num", idx);         // 점 본 결과 인덱스 입니다.
//	    	   i.putExtras(bundle);
//	    	   startActivity(i);
//	    	//  startActivityForResult(i,1);
//	      } 
//	    });
//	    
//	    ImageButton returnButton = (ImageButton) findViewById(R.id.iching_cancel);
//	    returnButton.setOnClickListener(new OnClickListener() {
//	      public void onClick(View view) {
//	    	  SimpleFortuneTellResult.this.setResult(RESULT_CANCELED);
//	        finish();
//	      } 
//	    });
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == 1) {
	      	if (resultCode == Activity.RESULT_OK) {
	      		SimpleFortuneTellResult.this.setResult(RESULT_OK);
		        finish();
		    }
	    }
	 }

}

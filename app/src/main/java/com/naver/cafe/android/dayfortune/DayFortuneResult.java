package com.naver.cafe.android.dayfortune;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.naver.cafe.android.DateUtil;
import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;

public class DayFortuneResult extends ShareActivity {
	
	public String idx = "";
	
	
	
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.day_fortune_result);
	    setBtn(R.layout.day_fortune_result);
	    Bundle extras = getIntent().getExtras();

	    TextView titleTextView = (TextView) findViewById(R.id.dayFortuneResultTitle);
	    
	    if (extras != null) { 
		    idx = extras.getString("num"); 
		    
		    String filename = getHtmlFileName(Integer.parseInt(idx));
		     
		    //XmlResourceParser data = getResources().getXml(getLayout(Integer.parseInt(idx)));
		    //DayFortuneHandler dh = new DayFortuneHandler();
		    //String content = "";
		    //try{
		    //	content = dh.inspectIchingXml(data);
		    //} catch (Exception e){} 
        	
		    titleTextView.setText(getTTi(Integer.parseInt(idx))+"띠 오늘의 운세");
 
	    
	  WebView wb = (WebView)findViewById(R.id.detail);
	  wb.setBackgroundColor(0);
	   
	  //wb.getSettings().setJavaScriptEnabled(true);
	  String url = "file:///android_asset/today/"+filename;

	    
//	    DayFortuneOverNetworkUtil dnutil = new DayFortuneOverNetworkUtil(this, R.layout.day_fortune_result, 1);
//        String test = "";
        
        try{
        	//test = dnutil.getDaumData();
        	//System.out.println("-------------------------------------------\n"+test);
        	
        	wb.loadUrl(url);
        	
        	//WebSettings webSettings = wb.getSettings();
        	//webSettings.setUseWideViewPort(true);
        	
        	
        	wb.setWebViewClient(new WebViewClient());
    	    //wb.loadDataWithBaseURL(url, test, "text/html", "UTF-8", null);
        } catch (Exception e){}
		}	    
	 }
	

	
	
	
	private String getHtmlFileName(int num) {
		String header_name = "12gi-today-0";
		String footer_name = ".htm";
		String mid_name = "";
		int today = Integer.parseInt(DateUtil.getDate());
		int month = Integer.parseInt(DateUtil.getMonth());
		int year = Integer.parseInt(DateUtil.getYear());
		int resultNum = (today*13+month*11+year*9+num*7) % 100;
		if(resultNum < 10) {
			mid_name="0"+resultNum;
		}else{
			mid_name=""+resultNum;
		}
		String rtn = header_name+mid_name+footer_name;
		System.out.println("selected file name = " + rtn);
		return rtn;
		//return "12gi-today-001.htm";
	}
	
	private String getTTi(int num) {
		String rtn = "";
		switch (num) {
			case 1 : rtn 	= "쥐"; 		break;
			case 2 : rtn 	= "소"; 		break;
			case 3 : rtn 	= "호랑이"; 	break;
			case 4 : rtn 	= "토끼"; 		break;
			case 5 : rtn 	= "용"; 		break;
			case 6 : rtn 	= "뱀"; 		break;
			case 7 : rtn 	= "말"; 		break;
			case 8 : rtn 	= "양"; 		break;
			case 9 : rtn 	= "원숭이"; 	break;
			case 10 : rtn 	= "닭"; 		break;
			case 11 : rtn 	= "개"; 		break;
			case 12 : rtn 	= "돼지"; 	break;
			default:rtn		=	"";
		}
		return rtn;
	}

}

package com.naver.cafe.android.chemistry;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.naver.cafe.android.DateUtil;
import com.naver.cafe.android.R;
import com.naver.cafe.android.ShareActivity;

public class ChemistryResult extends ShareActivity {
	
	public String male = "";
	public String female = "";
	
	public void onCreate(Bundle icicle) { 
	    super.onCreate(icicle);
	    setContentView(R.layout.day_fortune_result);
	    setBtn(R.layout.day_fortune_result);
	    Bundle extras = getIntent().getExtras();

	    TextView titleTextView = (TextView) findViewById(R.id.dayFortuneResultTitle);
	     
	    if (extras != null) { 
	    	male = extras.getString("male");
	    	female = extras.getString("female"); 
		    titleTextView.setText("오늘의애정궁합 "+getTTi(Integer.parseInt(male))+"띠(남),"+getTTi(Integer.parseInt(female))+"띠(여)");
		    WebView wb = (WebView)findViewById(R.id.detail);
		    wb.setBackgroundColor(0);
		    //wb.getSettings().setDefaultTextEncodingName("utf-8");
		    	      
        try{  
        	String rawData = getHtmlRawData(female,male);
        	rawData += "<br>"+getHtmlRawData2(female,male);
        	System.out.println("rawData <<<<<" + rawData + ">>"); 
        	//wb.loadData(rawData, "text/html", "utf-8");
        	String css = ""; 
        	css = "<STYLE type='text/css'>table {Color:#ffffff; Padding-top:6px; Padding-bottom:3px; Padding-left:4px; Line-height:18px; width:280px; border:2px} </STYLE> ";
        	wb.loadDataWithBaseURL(null, "<html><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-16le\">"+css+rawData, "text/html", "utf-8", "about:blank");
        	//wb.loadData("<html><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-16le\">"+rawData, "text/html", "utf-8");
        	//wb.loadData("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />" +rawData, "text/html", "utf-8");
        	//wb.setWebViewClient(new WebViewClient());
        	 
        } catch (Exception e){}  
		}
	 }
	
	    public String getHtmlRawData(String male, String female) throws Exception {
	        
	        InputStream is = getAssets().open("chemistry/12gi-to-"+male+female+".htm");
	        BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
 	        StringBuffer sb = new StringBuffer(); 
		    String line;

		    while((line=br.readLine())!=null){
		        sb.append(line);
		        sb.append("\n");   
		    }
		    is.close();  
		    return sb.toString();
	    }
	    
	    public String getHtmlRawData2(String male,String female) throws Exception {
	    	int today = Integer.parseInt(DateUtil.getDate());
			int month = Integer.parseInt(DateUtil.getMonth());
			int year = Integer.parseInt(DateUtil.getYear().substring(2));
			int imale = Integer.parseInt(male);
			int ifemale = Integer.parseInt(female);
			Log.d("taoist", "year = " + year);
				
			int rst = (today*13+month*11+year*9+imale*3+ifemale*7)%144;
	          
			Log.d("taoist", "rst = " + rst);
			
	        String rrst = "";
	        
	        if(rst < 10){
	        	rrst = "00"+rst;
	        } else if(rst < 100) { 
	        	rrst = "0"+rst;
	        }else {
	        	rrst = rst+""; 
	        } 
	         
	        Log.d("taoist", "RST >>>>>>>>>>>" + rrst);
	        
	        InputStream is = getAssets().open("chemistry/12gi-tohp-"+rrst+".htm");
	        
	        Log.d("taoist", "RST >>>>>>>>>>>" + "chemistry/12gi-tohp-"+rrst+".htm"); 
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
 	        StringBuffer sb = new StringBuffer(); 
		    String line;

		    while((line=br.readLine())!=null){  
		        sb.append(line);
		        sb.append("\n");   
		    }
		    is.close();  
		    return sb.toString();
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


package kr.co.taoists.android.app.httpurl;

import java.util.Properties;

import kr.co.taoists.cmnn.dto.HttpConDTO;
import kr.co.taoists.cmnn.net.HttpConAdaptor;
import kr.co.taoists.cmnn.util.JsonUtil;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

public class HttpUrlApp extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
    	HttpConDTO dto = new HttpConDTO();
//    	dto.setUrl("http://172.16.2.221/test/HttpJson.jsp");
//    	dto.setUrl("http://172.17.100.140/valid/HttpJson.jsp");
    	dto.setUrl("http://172.30.5.61:9998/valid/HttpJson.jsp");
    	dto.setCharSet("UTF-8");
    	dto.setBufSize(1024);
        dto.setTimeout(1000);
    	dto.setMethodType("POST");
    	
	    Properties prop = new Properties();
	    prop.setProperty("cmd", "checkName");
	    prop.setProperty("type", "noSession");
	    prop.setProperty("url", "/comm/Real_NameCheck_0901.jsp");
	    prop.setProperty("SAWON_CD", "9109162");
	    prop.setProperty("UPMU_GUBUN", "03");
	
	    prop.setProperty("CHECK_JUMIN", "1감ab");
	    prop.setProperty("CHECK_NAME", "rㅠㄷ가0ㄱ");
	    dto.setProp(prop);
	    
	    HttpConAdaptor adaptor = new HttpConAdaptor();
	    try{
	    	String jsonStr = adaptor.transfer(dto,"retrieve",false);
	    	 
	    	JSONObject jobj = JsonUtil.makeJsonToBean(jsonStr);
	    	
	    	System.out.println("result="+jobj);
	    	System.out.println("passo="+jobj.get("passo"));
	    	
	    } catch(Exception e){ e.printStackTrace();}
        
    }
}

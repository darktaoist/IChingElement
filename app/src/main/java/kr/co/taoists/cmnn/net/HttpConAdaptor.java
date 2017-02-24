package kr.co.taoists.cmnn.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import kr.co.taoists.cmnn.base.Adaptor;
import kr.co.taoists.cmnn.dto.BaseDTO;
import kr.co.taoists.cmnn.dto.HttpConDTO;
import kr.co.taoists.cmnn.exception.AdaptorException;
import kr.co.taoists.cmnn.util.JsonUtil;

//import org.apache.http.conn.ssl.AllowAllHostnameVerifier;


public class HttpConAdaptor implements Adaptor {

    private HttpURLConnection conn;
    private URL url; 
    
    public HttpConAdaptor(){
    	if(conn != null) conn.disconnect();
    }
 

    public InputStream transfer (BaseDTO sDto)throws AdaptorException {
    	BufferedOutputStream out = null;
        try {
        	HttpConDTO dto = (HttpConDTO)sDto;
        	        	
            url = new URL(dto.getUrl());
            conn = (HttpURLConnection)url.openConnection();
//            if(conn.getClass().toString().contains("HttpsURLConnection"))
//                ((HttpsURLConnection)conn).setHostnameVerifier(new AllowAllHostnameVerifier());
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setReadTimeout(dto.getTimeout());
            conn.setConnectTimeout(dto.getTimeout());
            conn.setRequestMethod(dto.getMethodType());
            dto.setStartTime(System.currentTimeMillis());

            if(dto.getData()!= null) {
                conn.setDoOutput(true);
                conn.connect();
                out = new BufferedOutputStream(conn.getOutputStream(), 1000);
                out.write(dto.getData());
                out.close();
            }
            InputStream in = conn.getInputStream();
            return in;
        } catch(Exception e){
            throw new AdaptorException("Exception while doing http request");
        } finally{
        	try{if(out != null) out.close();}catch(IOException ioe){}
        	if(conn != null) conn.disconnect();
        }
    }
    
    
    public String transfer(BaseDTO sDto, String flag, boolean charSetYn)throws AdaptorException {
    	BufferedOutputStream out = null;
    	BufferedInputStream in = null;
    	StringBuffer sb = new StringBuffer("");
        try {
        	HttpConDTO dto = (HttpConDTO)sDto;

        	url = new URL(dto.getUrl());
        	
//        	if(conn.getClass().toString().contains("HttpsURLConnection"))
//                ((HttpsURLConnection)conn).setHostnameVerifier(new AllowAllHostnameVerifier());
            
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 헤더값을 설정한다.
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setReadTimeout(dto.getTimeout());
            conn.setConnectTimeout(dto.getTimeout());
            conn.setRequestMethod(dto.getMethodType());// 전달 방식을 설정한다. POST or GET, 기본값은 GET 이다.
            conn.setDoInput(true); // 서버로부터 메세지를 받을 수 있도록 한다. 기본값은 true이다.
            conn.setDoOutput(true);// 서버로 데이터를 전송할 수 있도록 한다. GET방식이면 사용될 일이 없으나, true로 설정하면 자동으로 POST로 설정된다. 기본값은 false이다.

            dto.setStartTime(System.currentTimeMillis());

            if(dto.getProp()!= null) {
            	
        	    OutputStream out_stream = null;
        	    
        	    try{
	        	    out_stream = conn.getOutputStream();
	                out = new BufferedOutputStream(out_stream, dto.getBufSize());
	                if(charSetYn)
	                	out.write(encodeString(dto.getProp()).getBytes(dto.getCharSet()));
	                else
	                	out.write(encodeString(dto.getProp()).getBytes());
	                
	                out.close();
        		} catch (Exception e){
        			e.printStackTrace(System.out);
        		} finally{
        			 try{if(out_stream != null)out_stream.close();} catch(IOException ioe){}
        		}
            }
            InputStream in_stream = null;
    	    try{
        	    in_stream = conn.getInputStream();
                in = new BufferedInputStream(in_stream, dto.getBufSize());

                int bytesRead = 0;
                
                byte[] buffer = new byte[dto.getBufSize()];
                while ((bytesRead = in.read(buffer)) != -1) {
                	sb.append(new String(buffer,0,bytesRead));
                }
                in_stream.close();
    		} catch (Exception e){
    			e.printStackTrace(System.out);
    		} finally{
				try{if(in_stream != null)in_stream.close();} catch(IOException ioe){}
    		}            

            return sb.toString();
        } catch(Exception e){
        	e.printStackTrace();
            throw new AdaptorException("Exception while doing http request");
        } finally{
        	try{if(out != null) out.close();}catch(IOException ioe){}
        	if(conn != null) conn.disconnect();
        }
    }
    
    public String transferToStr (String urlString, String queryString, String methodType, String token) {
        String content = "";
        try {
        	conn.setDoOutput(true);
            conn.setRequestMethod(methodType.toUpperCase());

            PrintWriter pw = new PrintWriter(conn.getOutputStream());

            pw.write("url=" + url);
            pw.write(queryString);

            pw.flush();
            pw.close();

            int code = conn.getResponseCode();
            if (mustRedirect(code)) {
                secureRedirect(conn.getHeaderField("Location"));
            }
            content = readContents(token);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return content;

    }

    private boolean mustRedirect(int code) {
    	System.out.println("code="+code);
        if (code == HttpURLConnection.HTTP_MOVED_PERM ||
                code == HttpURLConnection.HTTP_MOVED_TEMP) {
            return true;
        } else
            return false;
    }

    private void secureRedirect(String location) throws IOException {
    	System.out.println("location="+location);
        URL url = new URL(location);
        conn = (HttpsURLConnection) url.openConnection();
    }

    private String readContents(String token) {

        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
                sb.append(token);
            }
            in.close();
        } catch (IOException e) {
        	System.out.println("readContents IOException ="+e.getMessage());
        } finally {
        	try{if(in != null) in.close();}catch(IOException ioe){}        	
        }
        return sb.toString();

    }
    
    public static String encodeString(Properties params) {
        StringBuffer sb = new StringBuffer(256);
        Enumeration names = params.propertyNames();

        while (names.hasMoreElements()) {
           String name = (String) names.nextElement();
           String value = params.getProperty(name);
           sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value) );

           if (names.hasMoreElements()) sb.append("&");
        }
        return sb.toString();
    }    
    
    public static void main(String[] args){
    	
    	HttpConDTO dto = new HttpConDTO();
//    	dto.setUrl("http://172.16.2.221/test/HttpJson.jsp");
    	dto.setUrl("http://172.17.100.140/valid/HttpJson.jsp");
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
	    	String jsonStr = adaptor.transfer(dto,"retrieve", false);
	    	 
	    	JSONObject jobj = JsonUtil.makeJsonToBean(jsonStr);
	    	
	    	System.out.println("result="+jobj);
	    	
	    } catch(Exception e){ e.printStackTrace();}

    }

}

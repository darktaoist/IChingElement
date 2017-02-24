package kr.co.taoists.cmnn.util;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
	public JsonUtil (){
		
	}

	public static JSONObject makeJsonToBean (String jsonData) throws Exception {
		JSONObject json = new JSONObject(jsonData);
//		JSONArray jarray = new JSONArray(jsonData);
//		for (int i=0; i < jarray.length(); i++) {
//			JSONObject jobj = (JSONObject)jarray.get(i);
//			Iterator subIt = jobj.keys();
//			while(subIt.hasNext()){
//				String key = (String)subIt.next();
//				JSONObject dto = (JSONObject)_hm.get(key) ;
//				jarray.put(i,dto);
//			}
//		}  
		return json;
	}
	public static JSONArray makeJsonToBeans (String jsonData) throws Exception {

		JSONArray jarray = null;
		try{   
			jarray = new JSONArray(jsonData);
		}catch(Exception e){
			System.out.println("array");
			jarray = new JSONArray("["+jsonData+"]");
			
		}
//		for (int i=0; i < jarray.length(); i++) {
//			JSONObject jobj = (JSONObject)jarray.get(i);
//			Iterator subIt = jobj.keys();
//			while(subIt.hasNext()){
//				String key = (String)subIt.next();
//				JSONObject dto = (JSONObject)_hm.get(key) ;
//				jarray.put(i,dto);
//			}
//		}  
		return jarray;
	}
	
	public static String makeJASONHashString(HashMap _hm) throws Exception {

		Iterator it = _hm.keySet().iterator();
		while(it.hasNext()){
			String hKey = (String)it.next();
			
			if("Header".equals(hKey)){
				if(_hm.get(hKey) != null){
					_hm.put(hKey, new JSONObject(_hm.get(hKey)));
				}else {
					_hm.put(hKey, "");
				}
			}else if("Message".equals(hKey)){
				HashMap rInfo = (HashMap)_hm.get(hKey);
				Iterator subIt = rInfo.keySet().iterator();
				while(subIt.hasNext()){
					String key = (String)subIt.next();
					rInfo.put(key, new JSONObject(rInfo.get(key)));
				}	
			}else if("_sInfo".equals(hKey)){
				if(_hm.get(hKey) != null){
					_hm.put(hKey, _hm.get(hKey));
				}else {
					_hm.put(hKey, "");
				}
			}else{
				if(_hm.containsKey(hKey)){
					if(_hm.get(hKey) == null){
						_hm.put(hKey, "");
					} else {
						HashMap hm = (HashMap)_hm.get(hKey);
						Iterator subIt = hm.keySet().iterator();
						while(subIt.hasNext()){
							String key = (String)subIt.next();
							hm.put(key, new JSONObject(hm.get(key)));
						}
					}
				}
			}
		}
		return new JSONObject(_hm).toString();   
	} 		
}

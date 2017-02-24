/*
 * 작성된 날짜: 2009-09-03
 *
 * TODO 생성된 파일에 대한 템플리트를 변경하려면 다음으로 이동하십시오.
 * 창 - 환경 설정 - Java - 코드 스타일 - 코드 템플리트
 */
package kr.co.taoists.cmnn.msg;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Userid
 *
 * TODO 생성된 유형 주석에 대한 템플리트를 변경하려면 다음으로 이동하십시오.
 * 창 - 환경 설정 - Java - 코드 스타일 - 코드 템플리트
 */
public class MessageManager {

    public static void addMsg(HashMap _rInfo, String key, String gbn, String code) throws Exception {
    	_rInfo.put(key, new Message(gbn, code));
    }
    public static void addMsg(HashMap _rInfo, String key, String gbn, String code, Object[] args) throws Exception {
    	_rInfo.put(key, new Message(gbn, code, args));
    }
    public static Message getResBascMsg(HashMap _rInfo, String key, String gbn, String code) throws Exception {
    	Iterator it = (_rInfo.keySet()).iterator();
    	if(it.hasNext()){
    		return (Message)_rInfo.get((String)it.next());
    	}else{
    		Message msg = new Message(gbn, code);
    		addMsg (_rInfo, key, gbn, code);
    		return msg;
    	}
    }
    public static Message getResBascMsg(HashMap _rInfo, String key, String gbn, String code, Object[] args) throws Exception {
    	Iterator it = (_rInfo.keySet()).iterator();
    	if(it.hasNext()){
    		return (Message)_rInfo.get((String)it.next());
    	}else{
    		Message msg = new Message(gbn, code, args);
    		addMsg (_rInfo, key, gbn, code, args);
    		return msg;
    	}
    }
    
}

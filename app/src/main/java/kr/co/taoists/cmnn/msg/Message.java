/*
 * 작성된 날짜: 2009-09-03
 *
 * TODO 생성된 파일에 대한 템플리트를 변경하려면 다음으로 이동하십시오.
 * 창 - 환경 설정 - Java - 코드 스타일 - 코드 템플리트
 */
package kr.co.taoists.cmnn.msg;

import java.text.MessageFormat;

import kr.co.taoists.cmnn.dto.BaseDTO;
import kr.co.taoists.cmnn.exception.BusinessException;

/**
 * @author Userid
 *
 * TODO 생성된 유형 주석에 대한 템플리트를 변경하려면 다음으로 이동하십시오.
 * 창 - 환경 설정 - Java - 코드 스타일 - 코드 템플리트
 */
public class Message extends BaseDTO {

    private String gbn = null;
    private String code = null;
    private String msg = null;
    private Object args = null;
    private String resType= "0";

    public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getGbn() {
		return gbn;
	}
	public void setGbn(String gbn) {
		this.gbn = gbn;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getArgs() {
		return args;
	}
	public void setArgs(Object args) {
		this.args = args;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Message(String gbn,String code) throws BusinessException {
    	setGbn(gbn);
    	setCode(code);
    	setMsg(getMsg());
    }
    public Message(String gbn,String code,Object [] args) throws BusinessException {
    	setGbn(gbn);
    	setCode(code);
    	setArgs(args);
    	setMsg(getMsg());
    }

	/**
	 * @return msg을 리턴합니다.
	 */
	public String getMsg() throws BusinessException {
        try {
            MessageFormat form = new MessageFormat(msg);
            msg = form.format(args);
        } catch (Exception e) {
            throw new BusinessException(" 메시지 코드 오류["+gbn+","+code +"]", e);
        }
		return msg;
	}


}

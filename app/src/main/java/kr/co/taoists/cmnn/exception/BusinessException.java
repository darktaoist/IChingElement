package kr.co.taoists.cmnn.exception;

/**
 *
 * @author : Administrator
 */
public class BusinessException extends Exception {

    private String msg = null;
    private Exception ex = null;

    /**
     * BusinessException 占쏙옙占쏙옙占쏙옙 占쌍쇽옙.
     */
    public BusinessException() {
        super();
    }

    /**
     * BusinessException 占쏙옙占쏙옙占쏙옙 占쌍쇽옙.
     * @param s java.lang.String
     */
    public BusinessException(String message) {
        super(message);
        this.msg = message;
    }

    /**
     * BusinessException 占쏙옙占쏙옙占쏙옙 占쌍쇽옙.
     */
    public BusinessException(String message, Exception e) {
        super(message);
        this.msg = message;
        this.ex = e;
    }

    /**
     * Insert the method's description here.
     * Creation date: (2001-11-12 占쏙옙占쏙옙 2:01:27)
     * @return java.lang.Exception
     */
    public Exception getException() {
        return ex;
    }

    /**
     * Insert the method's description here.
     * Creation date: (2001-11-12 占쏙옙占쏙옙 1:57:31)
     * @return java.lang.String 占쏙옙占쌤메쇽옙占쏙옙
     */
    public String getMsg() {
        return msg;
    }
}

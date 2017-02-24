package kr.co.taoists.cmnn.base;

import kr.co.taoists.cmnn.dto.BaseDTO;
import kr.co.taoists.cmnn.exception.AdaptorException;

public interface Adaptor{
    //public abstract InputStream transfer(BaseDTO dto)throws AdaptorException ;
    public abstract String transfer(BaseDTO dto, String gbn, boolean charSetYn)throws AdaptorException ;
}

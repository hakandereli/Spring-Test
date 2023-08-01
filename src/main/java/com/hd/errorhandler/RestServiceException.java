package com.hd.errorhandler;

/**
 * @author hdereli
 * @since 8/1/2023
 */
public class RestServiceException extends RuntimeException{

    Integer errorCode = null;

    public RestServiceException(RestErrorCode restErrorCode) {
        super(restErrorCode.getErrorMessage());
        this.errorCode = restErrorCode.getErrorCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}

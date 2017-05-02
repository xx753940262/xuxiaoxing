package com.xiaoxing.common.http;

import com.xiaoxing.common.util.AbJsonUtil;

/**
 * 描述：
 * 作者：yougudongli on 17/2/13 13:01
 * 邮箱：xx@yougudongli.com
 */
public class M_Base {

    /**
     * isError : false
     * errorType : 0
     * errorMessage : Success
     * result : ""
     */

    private boolean isError;
    private int errorType;
    private String errorMessage;
    private String result;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public M_Base(String json) {
        M_Base result = AbJsonUtil.fromJson(json, this.getClass());
        this.isError = result.isError();
        this.errorType = result.getErrorType();
        this.errorMessage = result.getErrorMessage();
        this.result = result.getResult();
    }
}

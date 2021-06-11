package com.chb.learning.auth.entity;

import java.io.Serializable;

/**
 * @author caihongbin
 * @date 2021/6/10 11:04
 */
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int errCode;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(int errCode, String msg, T data) {
        super();
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
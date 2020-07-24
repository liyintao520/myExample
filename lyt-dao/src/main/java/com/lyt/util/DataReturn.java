package com.lyt.util;


import java.io.Serializable;
import java.util.Map;

/**
 * 返回类型Bean
 */
public class DataReturn implements Serializable {
    private String code;// 状态码, 0:成功, 否则失败
    private String message;// 失败后错误信息
    private Object data;// 成功时返回数据内容

    public DataReturn() {
    }

    public DataReturn(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String temp = "";
        if (data!=null){
            if(data instanceof Map){
                temp = data.toString();
            }else if(data instanceof String){
                temp = data.toString();
            }else{
                temp = JsonUtil.bean2json(data);
            }

        }
        return "DataReturn [code=" + code + ", message=" + message + ", data="
                + temp + "]";
    }
}


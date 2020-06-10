package com.autonomy.controller;

import com.autonomy.common.BaseErrorInfoInterface;
import com.autonomy.common.HttpResult;
import com.autonomy.common.ResultCode;
import com.autonomy.utils.I18nUtil;

public abstract class BaseController{


    /**
     * 登录成功
     * @param data
     * @return
     */
    public static HttpResult responseLoginSucc(Object data){
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(ResultCode.LOGIN_SUCCESS.getResultCode());
        httpResult.setMessage(I18nUtil.getMessage(ResultCode.LOGIN_SUCCESS.getResultMsg()));
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 登录失败
     * @param
     * @return
     */
    public static HttpResult responseLoginErr(Object data){
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(ResultCode.LOGIN_ERROR.getResultCode());
        httpResult.setMessage(I18nUtil.getMessage(ResultCode.LOGIN_ERROR.getResultMsg()));
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 返回成功
     * @param data
     * @param <T>
     * @return
     */
    public static HttpResult responseSuccess(Object data){
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(ResultCode.SUCCESS.getResultCode());
        httpResult.setMessage(I18nUtil.getMessage(ResultCode.SUCCESS.getResultMsg()));
        httpResult.setData(data);
        return httpResult;
    }


    /**
     * 返回错误
     * @param <T>
     * @return
     */
    public static HttpResult responseErrMsg(Object data){
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(ResultCode.ERROR.getResultCode());
        httpResult.setMessage(I18nUtil.getMessage(ResultCode.ERROR.getResultMsg()));
        httpResult.setData(data);
        return httpResult;
    }

    /**
     * 失败
     */
    public static HttpResult error(BaseErrorInfoInterface errorInfo) {
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(errorInfo.getResultCode());
        httpResult.setMessage(I18nUtil.getMessage(errorInfo.getResultMsg()));
        httpResult.setData(null);
        return httpResult;
    }

    /**
     * 失败
     */
    public static HttpResult error(Integer code,String msg) {
        HttpResult httpResult=new HttpResult();
        httpResult.setCode(code);
        httpResult.setMessage(I18nUtil.getMessage(msg));
        httpResult.setData(null);
        return httpResult;
    }
}

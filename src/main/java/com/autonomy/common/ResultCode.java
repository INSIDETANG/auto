package com.autonomy.common;

public enum ResultCode implements BaseErrorInfoInterface{
    // 数据操作错误定义
    SUCCESS(200,"msg.executionSuccessful"),
    ERROR(500,"msg.serverInternalError"),
    LOGIN_SUCCESS(200,"msg.loginSuccess"),
    LOGIN_ERROR(500,"msg.loginFailed"),
    BODY_NOT_MATCH(400,"msg.theRequestedDataFormatDoesNotMatch"),
    SIGNATURE_NOT_MATCH(401,"msg.theRequestedDigitalSignatureDoesNotMatch"),
    NOT_FOUND(404, "msg.theResourceWasNotFound"),
    SERVER_BUSY(503,"msg.theServerIsBusy.PleaseTryAgainLater"),
    UNKNOWNACCOUNTEXCEPTION(-1,"msg.unknownAccountException"),
    INCORRECTCREDENTIALSEXCEPTION(-2,"msg.incorrectCredentialsException"),
    LOCKEDACCOUNTEXCEPTION(-3,"msg.lockedAccountException"),
    EXCESSIVEATTEMPTSEXCEPTION(-4,"msg.excessiveAttemptsException"),
    AUTHENTICATIONEXCEPTION(-5,"msg.authenticationException");

    /** 错误码 */
    private Integer resultCode;

    /** 错误描述 */
    private String resultMsg;

    ResultCode(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
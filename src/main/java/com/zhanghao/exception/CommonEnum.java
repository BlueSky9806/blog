package com.zhanghao.exception;

public enum CommonEnum implements BaseErrorInfoInterface{
    BLOG_NOT_EXIST(404, "该博客已被删除!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器正忙!")
    ;

    // 错误码
    private final Integer resultCode;

    // 错误描述
    private final String resultMsg;

    CommonEnum(Integer resultCode, String resultMsg) {
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

    @Override
    public String toString() {
        return "CommonEnum{" +
                "resultCode=" + resultCode +
                ", resultMsg='" + resultMsg + '\'' +
                '}';
    }
}

package com.demo.document.entiry;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Date 2022/10/28 22:55
 * @Version 1.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 2581681983863845559L;

    /**
     * 成功码
     */
    private static final String SUCCESS_CODE = "00000";

    /**
     * 失败码
     */
    private static final String FAIL_CODE = "999";

    /**
     * 请求结果码
     */
    private String code;

    /**
     * 请求成功数据
     */
    private T data;

    /**
     * 错误消息
     */
    private String errorMsg;

    public Result() {
        this.code = SUCCESS_CODE;
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<>();
        result.setCode(FAIL_CODE);
        result.setErrorMsg(message);
        return result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}

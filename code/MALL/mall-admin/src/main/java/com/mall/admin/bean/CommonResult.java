package com.mall.admin.bean;

/**
 * 通用返回对象
 * Created by macro on 2019/8/21
 */
public class CommonResult {
    public static final int SUCCESS = 200;//操作成功

    public static  final int FAILED = 500;//操作失败

    public  static  final  int VALIDATE_FAILED=404;//参数校验失败
    private int code;
    private String message;
    private Object data;

    /**
     * 操作成功
     * @param data 获取的数据
     * @return
     */
    public CommonResult success(Object data){
        this.code = SUCCESS;
        this.message="操作成功";
        this.data = data;
        return  this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonResult validateFailed(String message) {
        this.code = VALIDATE_FAILED;
        this.message = message;
        return this;
    }

    /**
     * 操作失败
     * @param message
     * @return
     */
    public CommonResult failed(String message){
        this.code = FAILED;
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.icegone.spring.boot.blog.vo.general;

/**
 * 返回前端数据
 * @author Icegone
 * @date 2019/6/1
 */
public class ResultMap {
    /** @description: 状态 1代表成功 0代表失败 */
    private Integer success;
    /** @description: 返回的消息 */
    private  String message;
    /** @description: 返回的数据 */
    private Object data;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
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

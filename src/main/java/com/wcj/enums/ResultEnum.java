package com.wcj.enums;

import lombok.Getter;

/**
 * 返回结果枚举类型
 * @author wcj
 * @Date 2020/3/23 13:03
 * @Version 1.0
 */
@Getter
public enum ResultEnum {

    /**
     * 返回结果枚举，每个枚举代表一个返回状态
     */
    SUCCESS(20000, "操作成功!"),
    ERROR(40000, "操作失败!"),
    DATA_NOT_FOUND(40001, "查询失败!"),
    PARAMS_NULL(40002, "参数不能为空!"),
    PARAMS_ERROR(40005,"参数错误"),
    NOT_LOGIN(40003, "当前帐号未登录!");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 构造方法
     * @param code
     * @param msg
     */
    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

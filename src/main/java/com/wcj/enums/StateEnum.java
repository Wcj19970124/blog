package com.wcj.enums;

import lombok.Getter;

/**
 * 状态码枚举，所有的状态码都在这里定义
 * @author wcj
 * @Date 2020/3/23 13:11
 * @Version 1.0
 */
@Getter
public enum StateEnum {

    /**
     * 逻辑删除状态码
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态码
     */
    ENABLED(1, "已启用"),
    NOT_ENABLE(0, "未启用"),

    /**
     * 性别状态码
     */
    SEX_MAN(1, "男"),
    SEX_WOMAN(2, "女"),

    /**
     * 请求状态码
     */
    STATUS_SUCCESS(1,"正常"),
    STATUS_ERROR(0,"异常"),

    /**
     * 用户身份标识字段
     */
    ADMIN(0,"后台管理员"),
    USER(1,"前台用户");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码信息
     */
    private String msg;

    /**
     * 构造方法
     * @param code
     * @param msg
     */
    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

package com.wcj.vo;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * 时间轴vo类
 *
 * @author wcj
 * @Date 2020/4/1 18:36
 * @Version 1.0
 */
@Data
public class TimeLineVo {

    /**
     * 月份
     */
    private String month;

    /**
     * 月份对应的博客集合
     */
    private List<BlogVo> blogVoList;

    /**
     * 重写equals方法
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeLineVo that = (TimeLineVo) o;
        return month.equals(that.month);
    }

    /**
     * 重写hashCode方法
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
}

package com.wcj.utils;

import com.wcj.vo.TimeLineVo;

import java.util.List;

/**
 * 从时间轴集合中取出对应的时间轴对象
 *
 * @author wcj
 * @Date 2020/4/1 20:13
 * @Version 1.0
 */
public class TimeLineUtils {

    public static TimeLineVo getTimeLineVo(List<TimeLineVo> timeLineVoList, TimeLineVo timeLineVo) {
        for(TimeLineVo lineVo:timeLineVoList){
            if(lineVo.equals(timeLineVo)) {
                return lineVo;
            }
        }
        return null;
    }
}

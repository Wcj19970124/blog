package com.wcj.utils;

import com.wcj.pojo.Log;
import lombok.Data;

/**
 * 本地线程变量
 *
 * @author wcj
 * @Date 2020/3/23 16:23
 * @Version 1.0
 */
@Data
public class ThreadLocalContext {

    private Log logger = new Log();

    private boolean isLog = false;

    private static ThreadLocal<ThreadLocalContext> threadLocal = new ThreadLocal<>();

    public static ThreadLocalContext get() {
        if (threadLocal.get() == null) {
            ThreadLocalContext threadLocalContext = new ThreadLocalContext();
            threadLocal.set(threadLocalContext);
        }
        ThreadLocalContext threadLocalContext = threadLocal.get();
        return threadLocalContext;
    }

    public void remove() {
        this.logger = null;
        this.isLog = false;
        threadLocal.remove();
    }
}

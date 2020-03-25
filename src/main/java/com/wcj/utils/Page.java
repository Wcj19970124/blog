package com.wcj.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wcj
 * @Date 2020/3/25 9:23
 * @Version 1.0
 */
@Data
public class Page<T> implements Serializable {

    public static final String SORT_ASC = "asc";
    public static final String SORT_DESC = "desc";

    /**
     * 当前页
     */
    private Integer currentPage =1;

    /**
     * 每页显示的条数
     */
    private Integer pageSize = 20;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    /**
     * 总条数
     */
    private Integer totalCount = 0;

    /**
     * 数据
     */
    private List<T> list;

    /**
     * 条件参数
     */
    private Map<String, Object> params = new HashMap<>();

    /**
     * 排序列
     */
    private String sortColumn;

    /**
     * 排序方式
     */
    private String sortMethod;

    /**
     * 获取当前页
     *
     * @return
     */
    public Integer getCurrentPage() {
        if (this.currentPage < 1) {
            return 1;
        }
        return this.currentPage;
    }

    /**
     * 设置排序列
     *
     * @param sortColumn
     */
    public void setSortColumn(String sortColumn) {
        if (StringUtils.isBlank(sortColumn)) {
            this.sortColumn = null;
        } else {
            this.sortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        }
    }

    /**
     * 获取每一页第一条数据的index
     *
     * @return
     */
    public Integer getIndex() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * 设置总条数的时候计算总页数
     *
     * @param totalCount
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
    }

    /**
     * 设置排序方式
     *
     * @param sortMethod
     */
    public void setSortMethod(String sortMethod) {
        if (StringUtils.isBlank(sortMethod)) {
            this.sortMethod = SORT_ASC;
        }
        if (sortMethod.toUpperCase().startsWith(SORT_ASC)) {
            this.sortMethod = SORT_ASC;
        } else if (sortMethod.toUpperCase().startsWith(SORT_DESC)) {
            this.sortMethod = SORT_DESC;
        } else {
            this.sortMethod = SORT_ASC;
        }
    }
}

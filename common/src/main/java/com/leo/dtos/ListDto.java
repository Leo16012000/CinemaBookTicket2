package com.leo.dtos;

import java.util.List;

public class ListDto<T> {
    private List<T> list;
    private Integer totalCount;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

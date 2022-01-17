package com.yun.entity;

import lombok.Data;

import java.util.List;

/**
 * @ fileName:LayUiTree
 * @ description:
 * @ author:zyk
 * @ createTime:2021/12/3 16:38
 * @ version:1.0.0
 */
@Data
public class LayUiTree {
    private String title;
    private int id;
    private String field;
    private boolean checked;
    private boolean spread;
    private String url;
    private List<LayUiTree> children;
    private String icon;

    @Override
    public String toString() {
        return "LayUiTree{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", field='" + field + '\'' +
                ", checked=" + checked +
                ", spread=" + spread +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}

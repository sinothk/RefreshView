package com.sinothk.refresh.demo.temp;

/**
 * Created by 梁玉涛 on 2017/12/03.
 * 功能：
 */
public class News {
    /** 单图布局样式 */
    public static final int TYPE_SINGLE_PICTURE   = 0;
    /** 无图布局样式 */
    public static final int TYPE_NONE_PICTURE     = 1;

    public News() {
    }

    public News(int newsType, String title) {
        this.newsType = newsType;
        this.title = title;
    }

    private int newsType;
    private String title;

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

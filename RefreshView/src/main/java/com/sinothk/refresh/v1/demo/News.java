package com.sinothk.refresh.v1.demo;

/**
 * <pre>
 *  创建:  LiangYT 2018/5/23/023 on 17:47
 *  项目: RefreshViewLib
 *  描述:
 *  更新:
 * <pre>
 */
public class News {
    public static int TYPE_NONE_PICTURE = 0;
    public static int TYPE_SINGLE_PICTURE = 1;

    private int newsType;
    private String title;

    public News() {
    }

    public News(int newsType, String title) {
        this.newsType = newsType;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNewsType() {
        return newsType;
    }

    public String getTitle() {
        return title;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }
}

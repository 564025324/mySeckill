package com.spilder;

/**
 * 项目: 实现网络资源的下载
 * 功能: 创建实体类
 *
 * @author Lijingwen
 */
public class Urldb {


    private int id;
    private String url;
    private String url_type;

    // 是否已经被解析过
    private Integer analysised;

    public Urldb(int id, String url, String url_type) {
        this.id = id;
        this.url = url;
        this.url_type = url_type;
    }

    public Urldb(int id, String url, String url_type, Integer analysised) {
        this.id = id;
        this.url = url;
        this.url_type = url_type;
        this.analysised = analysised;
    }

    @Override
    public String toString() {
        return "Urldb{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", url_type='" + url_type + '\'' +
                ", analysised=" + analysised +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_type() {
        return url_type;
    }

    public void setUrl_type(String url_type) {
        this.url_type = url_type;
    }

    public Integer getAnalysised() {
        return analysised;
    }

    public void setAnalysised(Integer analysised) {
        this.analysised = analysised;
    }
}   // end class

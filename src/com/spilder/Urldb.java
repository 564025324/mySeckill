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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the url_type
     */
    public String getUrl_type() {
        return url_type;
    }

    /**
     * @param url_type the url_type to set
     */
    public void setUrl_type(String url_type) {
        this.url_type = url_type;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Urldb [id=" + id + ", url=" + url + ", url_type=" + url_type + "]";
    }

}

package com.rocko.update;

/**
 * 更新信息实体类
 *
 * @author Mr.Zheng
 * @date 2014年7月9日10:45:51
 */
public class UpdateInfo {
    /**
     * 对应服务器上update.xml的version节点信息
     */
    private String version;
    /**
     * 对应服务器上update.xml的description节点信息
     */
    private String description;
    /**
     * 对应服务器上update.xml的apkurl节点信息
     */
    private String url;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

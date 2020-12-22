package org.example.config.oss;

import org.example.common.util.JsonUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author walle&eva
 * @version V1.0
 * @since 2020-12-07 09:57
 */
@Component
@ConfigurationProperties(prefix = "oss.qiniu")
public class QiniuConfig {

    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String prefixLink;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPrefixLink() {
        return prefixLink;
    }

    public void setPrefixLink(String prefixLink) {
        this.prefixLink = prefixLink;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}

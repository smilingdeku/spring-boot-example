package org.example.module.common.file.domain.response;

import io.swagger.annotations.ApiModelProperty;
import org.example.common.util.JsonUtil;

import java.io.Serializable;


public class FileInfoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件地址")
    private String fileUrl;
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return JsonUtil.toJSONString(this);
    }
}

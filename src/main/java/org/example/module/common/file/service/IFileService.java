package org.example.module.common.file.service;

import org.example.module.common.file.domain.dto.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version V1.0
 * @since 2020-12-07 10:02
 */
public interface IFileService {

    /**
     * 上传文件资源
     *
     * @param files 文件数组
     * @return List
     */
    List<FileInfoDTO> upload(MultipartFile[] files);
}

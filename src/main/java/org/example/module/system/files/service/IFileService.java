package org.example.module.system.files.service;

import java.util.List;

import org.example.module.system.files.domain.dto.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

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

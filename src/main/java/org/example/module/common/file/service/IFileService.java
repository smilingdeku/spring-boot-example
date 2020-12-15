package org.example.module.common.file.service;

import com.google.common.collect.Lists;
import org.example.module.common.file.domain.dto.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version V1.0
 * @since 2020-12-07 10:02
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param files 文件列表
     * @return List<FileInfoDTO>
     */
    default List<FileInfoDTO> upload(MultipartFile[] files) {
        List<FileInfoDTO> dtoList = Lists.newArrayListWithExpectedSize(files.length);
        for (MultipartFile file : files) {
            FileInfoDTO dto = upload(file);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return FileInfoDTO
     */
    FileInfoDTO upload(MultipartFile file);

}

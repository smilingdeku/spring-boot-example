package org.example.module.common.file.controller;

import org.example.common.domain.response.Result;
import org.example.module.common.file.domain.dto.FileInfoDTO;
import org.example.module.common.file.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version V1.0
 * @since 2020-12-07 09:53
 */
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Resource(name = "qiniuFileService")
    private IFileService filesService;

    @PostMapping("/upload")
    public Result<List<FileInfoDTO>> upload(@RequestParam("files") MultipartFile[] files) {
        List<FileInfoDTO> list = filesService.upload(files);
        return Result.success(list);
    }

}

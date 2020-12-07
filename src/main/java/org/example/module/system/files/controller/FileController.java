package org.example.module.system.files.controller;

import java.util.List;

import javax.annotation.Resource;

import org.example.common.domain.response.Result;
import org.example.module.system.files.domain.dto.FileInfoDTO;
import org.example.module.system.files.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version V1.0
 * @since 2020-12-07 09:53
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private IFileService filesService;

    /**
     * 上传文件
     *
     * @param files files
     * @return Result
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("files") MultipartFile[] files) {
        List<FileInfoDTO> list = filesService.upload(files);
        return Result.success(list);
    }
}

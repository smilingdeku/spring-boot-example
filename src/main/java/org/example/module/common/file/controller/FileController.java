package org.example.module.common.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.example.common.domain.response.Result;
import org.example.common.util.MapperUtil;
import org.example.module.common.file.domain.dto.FileInfoDTO;
import org.example.module.common.file.domain.response.FileInfoResponse;
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
@Api(tags = {"文件接口"})
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Resource(name = "qiniuFileService")
    private IFileService filesService;

    @ApiOperation(value = "上传文件")
    @ApiImplicitParam(paramType="formData", name = "files", value = "文件列表", required = true, allowMultiple = true, dataType = "file")
    @PostMapping("/upload")
    public Result<List<FileInfoResponse>> upload(@RequestParam("files") MultipartFile[] files) {
        List<FileInfoDTO> dtoList = filesService.upload(files);
        List<FileInfoResponse> responseList = MapperUtil.mapList(dtoList, FileInfoDTO.class, FileInfoResponse.class);
        return Result.success(responseList);
    }

}

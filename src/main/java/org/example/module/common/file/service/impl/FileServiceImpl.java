package org.example.module.common.file.service.impl;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.example.common.util.FileUtil;
import org.example.common.util.JsonUtil;
import org.example.config.oss.QiniuConfig;
import org.example.module.common.file.domain.dto.FileInfoDTO;
import org.example.module.common.file.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author walle
 * @version V1.0
 * @since 2020-12-07 10:02
 */
@Service
public class FileServiceImpl implements IFileService {

    private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private QiniuConfig qiniuConfig;

    @Override
    public List<FileInfoDTO> upload(MultipartFile[] files) {
        if (Objects.isNull(files) || files.length == 0) {
            return Collections.emptyList();
        }

        List<FileInfoDTO> list = new ArrayList<>();
        for (MultipartFile f : files) {
            FileInfoDTO fileInfoDTO = uploadResourceIntoQiniuOss(f);
            list.add(fileInfoDTO);
        }
        return list;
    }

    /**
     * 上传文件到七牛云
     *
     * @param file 文件数据
     * @return FileInfoDTO
     */
    private FileInfoDTO uploadResourceIntoQiniuOss(MultipartFile file) {
        FileInfoDTO uploadFileInfo = new FileInfoDTO();
        uploadFileInfo.setFileName(file.getOriginalFilename());
        uploadFileInfo.setFileSize(file.getSize());
        try {
            //构造一个带指定 Region 对象的配置类
            Configuration cfg = new Configuration(Region.region0());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = FileUtil.buildUniqueFileName(file.getOriginalFilename());
            //...生成上传凭证，然后准备上传
            Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
            String upToken = auth.uploadToken(qiniuConfig.getBucketName());
            Response response = uploadManager.put(file.getInputStream(), key, upToken, null, null);

            //解析上传成功的结果
            String link = qiniuConfig.getPrefixLink() + key;
            uploadFileInfo.setFileUrl(link);
            log.info("Function[uploadResourceIntoQiniuOss]response:{}", JsonUtil.toJSONString(response));
        } catch (Exception ex) {
            log.error("Function[uploadResourceIntoQiniuOss]", ex);
        }
        return uploadFileInfo;
    }

}

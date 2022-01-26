package plus.extvos.example.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.extvos.builtin.upload.config.UploadConfig;
import plus.extvos.builtin.upload.controller.AbstractUploadController;
import plus.extvos.builtin.upload.entity.UploadFile;
import plus.extvos.builtin.upload.entity.UploadResult;
import plus.extvos.builtin.upload.service.StorageService;
import plus.extvos.common.exception.ResultException;

import java.io.File;
import java.util.Map;

/**
 * @author Mingcai SHEN
 */
@Slf4j
@RestController
@Api(tags = {"文件上传服务"})
@RequestMapping("/petite/upload")
public class UploadControllerImpl extends AbstractUploadController implements StorageService {

    @Autowired
    private UploadConfig uploadConfig;


    @Override
    protected StorageService processor() {
        return this;
    }

    @Override
    public UploadResult process(UploadFile uploadFile, String s, Map<String, String> map) throws ResultException {
        log.info("process:> {}, {}, {}",uploadFile, s, map);
        return new UploadResult(uploadFile, false);
    }

    @Override
    public boolean exists(String s, String s1) {
        log.info("exists:> {}, {}",s, s1);
        return new File(s).exists();
    }

    @Override
    public boolean useTemporary() {
        return false;
    }

    @Override
    public String root() {
        return uploadConfig.getRoot();
    }

    @Override
    public String prefix() {
        return uploadConfig.getPrefix();
    }

    @Override
    public String temporary() {
        return uploadConfig.getTemporary();
    }
}

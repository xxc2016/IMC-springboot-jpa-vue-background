package com.xxcgroup.SmartDevelopCloud.controller;

import com.xxcgroup.SmartDevelopCloud.jsonhelper.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@RestController
public class FileController {
    @PostMapping("/upload")
    public JsonResult<String> fileUpload(@RequestParam(value = "file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "//root//picture//"; // 上传后的路径
//        String filePath="C:\\Users\\sss\\Desktop\\";
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return JsonResult.success("/picture/"+ fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.failMessage("上传失败");

    }
}

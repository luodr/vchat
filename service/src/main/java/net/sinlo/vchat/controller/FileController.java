package net.sinlo.vchat.controller;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("api/file")
@Api("朋友")
//@UserLoginToken
public class FileController {
    @Value("${upload.file.path}")
    private String uploadPath;
    private Random random = new Random();
    private SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");

    @PostMapping("upload/one")
    public String uploadOne(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "file is empty";
        }
        String fileName = file.getOriginalFilename();
        Date date = new Date();
        String newName = format.format(date) + "_"+random.nextInt(3) +"_"+ fileName;
        String path = uploadPath + "/" + newName;
        System.out.println("文件保存路径:"+path);
        File file1 = new File(path);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/upload/"+newName;
    }

    @PostMapping("uploadBlob/one")
    public String uploadBlob(@RequestParam("file") MultipartFile file, @RequestParam("suffix") String suffix) {
        if (file.isEmpty()) {
            return "file is empty";
        }
        String fileName = file.getOriginalFilename();
        Date date = new Date();
        String newName = format.format(date) + "_"+random.nextInt(3) +"_"+ fileName+'.'+suffix;
        String path = uploadPath + "/" + newName;
        System.out.println("Blob保存路径:"+path);
        File file1 = new File(path);
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/upload/"+newName;
    }
}

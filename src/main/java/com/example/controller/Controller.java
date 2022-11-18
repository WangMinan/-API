package com.example.controller;

import com.example.pojo.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;

/**
 * @author : [wangminan]
 * @description : [一句话描述该类的功能]
 */
@RestController
public class Controller{

    @Value("${file.path.windows}")
    private String windowsFilePath;
    @Value("${file.path.linux}")
    private String linuxFilePath;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping("/uploadFile")
    public R uploadImg(@RequestPart("file")MultipartFile file, @RequestParam("password")String password){
        try{
            if(!bCryptPasswordEncoder.matches("********",password)){
                return R.error("秘钥未通过核验,请重试或联系管理员");
            }
            if(!file.isEmpty()){
                String os = System.getProperty("os.name");
                BufferedOutputStream out;
                if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
                    out = new BufferedOutputStream(
                            Files.newOutputStream(new File(
                                    windowsFilePath + file.getOriginalFilename()).toPath()));
                } else { //linux和mac系统
                    out = new BufferedOutputStream(
                            Files.newOutputStream(new File(
                                    linuxFilePath + file.getOriginalFilename()).toPath()));
                }
                System.out.println(file.getOriginalFilename());
                out.write(file.getBytes());
                out.flush();
                out.close();
                return R.ok("上传成功");
            } else {
                return R.error("请求头中未见文件");
            }
        } catch (Exception e){
            return R.error(e.getMessage());
        }
    }
}

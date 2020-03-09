package com.leyou.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg");

    private static final Logger log = LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //校验文件类型
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            log.error("文件类型不合法{}",originalFilename);
            return null;
        }
        //校验文件内容
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if(bufferedImage == null){
            log.error("文件内容不合法{}",originalFilename);
            return null;
        }
        //保存到服务器
        file.transferTo(new File("C:\\upload\\"+originalFilename));
        //返回url进行回显
        return "http://image.leyou.com/"+originalFilename;//这里返回的是图片路径
    }
}

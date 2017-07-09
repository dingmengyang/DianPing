package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.bean.Ad;
import org.springframework.web.multipart.MultipartFile;

/**
 * 业务传输，对数据库对象的封装
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdDto extends Ad{

    private String img;

    private MultipartFile imgFile;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}

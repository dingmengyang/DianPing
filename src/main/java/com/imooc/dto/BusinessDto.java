package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.bean.Business;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商家业务实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business{

    private String img;
    private MultipartFile imgFile;
    private String keyword;
    private Integer star;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}

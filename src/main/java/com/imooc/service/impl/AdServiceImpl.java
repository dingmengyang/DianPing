package com.imooc.service.impl;

import com.imooc.bean.Ad;
import com.imooc.dao.AdDao;
import com.imooc.dto.AdDto;
import com.imooc.service.AdService;
import com.imooc.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AdService实现类
 */
@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private  AdDao adDao;

    private final static Logger logger = LoggerFactory
            .getLogger(AdService.class);

    //读取配置文件的值
    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    //TODO,根据具体错误原因可更加详细返回结果
    public boolean add(AdDto adDto) {
        Ad ad=new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        String fileName;
        if (adDto.getImgFile()!=null&&adDto.getImgFile().getSize()>0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(),adImageSavePath);
                ad.setImgFileName(fileName);
                int count=adDao.insert(ad);
                return count>0;
            } catch (IOException e) {
                logger.error("文件保存出错:"+e.getMessage());
                return false;
            }
        }else {
            return false;
        }
    }

    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result=new ArrayList<AdDto>();
        Ad adCondition= new Ad();
        BeanUtils.copyProperties(adDto,adCondition);
        List<Ad> list=adDao.queryByPage(adCondition);
        for (Ad ad : list) {
            AdDto dto=new AdDto();
            dto.setImg(adImageUrl+ad.getImgFileName());
            BeanUtils.copyProperties(ad,dto);
            result.add(dto);
        }
        return result;
    }

    public boolean deleteOne(Long id) {
        int count=adDao.deleteOne(id);
        return count > 0;
    }

    public AdDto queryById(Long id) {
        Ad ad=adDao.queryById(id);
        AdDto adDto=new AdDto();
        BeanUtils.copyProperties(ad,adDto);
        adDto.setImg(adImageUrl+ad.getImgFileName());
        return adDto;
    }

    public boolean modify(AdDto adDto) {
        Ad ad=new Ad();
        BeanUtils.copyProperties(adDto,ad);
        String fileName=null;
        if (adDto.getImgFile()!=null&&adDto.getImgFile().getSize()>0) {
            try {
                fileName = FileUtil.save(adDto.getImgFile(),adImageSavePath);
                ad.setImgFileName(fileName);
            } catch (IOException e) {
                logger.error("文件保存出错:"+e.getMessage());
                return false;
            }
        }
        int count=adDao.updateOne(ad);
        if (count!=1){
            return false;
        }
        //更新成功则删除之前的图片
        if (fileName!=null){
            FileUtil.delete(adImageUrl+ad.getImgFileName());
        }
        return true;
    }
}

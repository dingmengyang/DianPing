package com.imooc.service.impl;


import com.imooc.bean.Business;
import com.imooc.bean.Page;
import com.imooc.dao.BusinessDao;
import com.imooc.dao.CommentDao;
import com.imooc.dao.OrderDao;
import com.imooc.dto.BusinessDto;
import com.imooc.dto.BusinessListDto;
import com.imooc.dto.CommentListDto;
import com.imooc.service.BusinessService;
import com.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private CommentDao commentDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    public BusinessDto queryById(Long id) {
        Business business = businessDao.queryById(id);
        BusinessDto dto = new BusinessDto();
        BeanUtils.copyProperties(business, dto);
        dto.setImg(adImageUrl + business.getImgFileName());
        return dto;
    }

    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<BusinessDto>();
        Business condition = new Business();
        BeanUtils.copyProperties(businessDto, condition);
        List<Business> list = businessDao.queryByPage(condition);
        for (Business business : list) {
            BusinessDto dto = new BusinessDto();
            dto.setImg(adImageUrl + business.getImgFileName());
            BeanUtils.copyProperties(business, dto);
            result.add(dto);
        }
        return result;
    }

    public boolean add(BusinessDto dto) {
        Business business=new Business();
        BeanUtils.copyProperties(dto,business);
        String fileName;
        if (dto.getImgFile()!=null&&dto.getImgFile().getSize()>0) {
            try {
                fileName = FileUtil.save(dto.getImgFile(),adImageSavePath);
                business.setImgFileName(fileName);
                int count= businessDao.insert(business);
                return count>0;
            } catch (IOException e) {
                e.printStackTrace();//TODO,打印日志
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean remove(Long id) {
        int count=businessDao.deleteOne(id);
        return count>0;
    }

    public boolean modify(Long id, BusinessDto dto) {
        Business business=new Business();
        BeanUtils.copyProperties(dto,business);
        String fileName=null;
        if (dto.getImgFile()!=null&&dto.getImgFile().getSize()>0) {
            try {
                fileName = FileUtil.save(dto.getImgFile(),adImageSavePath);
                business.setImgFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();//TODO,打印日志
                return false;
            }
        }
        int count=businessDao.updateOne(business);
        if (count!=1){
            return false;
        }
        //更新成功则删除之前的图片
        if (fileName!=null){
            FileUtil.delete(adImageUrl+business.getImgFileName());
        }
        return true;
    }

    public BusinessListDto searchWithParams(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<BusinessDto>();
        BusinessListDto listDto=new BusinessListDto();
        Business condition = new Business();
        BeanUtils.copyProperties(businessDto, condition);
        List<Business> list = businessDao.searchByPage(condition);
        if (list!=null&&list.size()>0) {
            listDto.setHasMore(true);
            for (Business business : list) {
                BusinessDto dto = new BusinessDto();
                dto.setImg(adImageUrl + business.getImgFileName());
                BeanUtils.copyProperties(business, dto);
                result.add(dto);
            }
            listDto.setData(result);
        }else {
            listDto.setHasMore(false);
        }
        return listDto;
    }

    //评论列表
    public CommentListDto searchWithParams(int businessId, Page page) {
        return null;
    }

    public void updateNumber(String date) {
        businessDao.updateNumber(date);
    }

    //TODO,通过计算获得星级
    public void updateStar() {
        //获得comment中的商铺id
//        List<Integer> businessIdList=businessDao
//        Long amount=commentDao.getStarNum();
    }
}

package com.imooc.service.impl;

import com.imooc.bean.Business;
import com.imooc.bean.Comment;
import com.imooc.dao.CommentDao;
import com.imooc.dao.MemberDao;
import com.imooc.dao.OrderDao;
import com.imooc.dto.BusinessDto;
import com.imooc.dto.CommentDto;
import com.imooc.dto.CommentForSubmitDto;
import com.imooc.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommentServiceImpl  implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderDao orderDao;

    //提交评论的同时更新订单状态
    @Transactional
    public boolean commitComment(CommentForSubmitDto commentDto) {
        Comment comment=new Comment();
        comment.setContent(commentDto.getContent());
        comment.setStar(commentDto.getStar());
        comment.setOrdersId(commentDto.getId());
        int insertCount=commentDao.insertComment(comment);
        int updateCount=orderDao.updateState(commentDto.getId());
        return insertCount>0&&updateCount==1;
    }

    public List<CommentDto> searchByPage(CommentDto commentDto) {
        List<CommentDto> result = new ArrayList<CommentDto>();
        Comment condition = new Comment();
        BeanUtils.copyProperties(commentDto, condition);
        List<Comment> list = commentDao.queryByPage(condition);
        for (Comment comment : list) {
            CommentDto dto = new CommentDto();
            BeanUtils.copyProperties(comment, dto);
            dto.setUsername(""+memberDao.getPhoneFromComment(comment.getId()));
            result.add(dto);
        }
        return result;
    }
}

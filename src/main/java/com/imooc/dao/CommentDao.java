package com.imooc.dao;


import com.imooc.bean.Comment;

import java.util.List;

public interface CommentDao {

    int insertComment(Comment comment);

    List<Comment> queryByPage(Comment condition);
}

package com.imooc.service;


import com.imooc.dto.CommentDto;
import com.imooc.dto.CommentForSubmitDto;

import java.util.List;

public interface CommentService {

    boolean commitComment(CommentForSubmitDto commentDto);

    List<CommentDto> searchByPage(CommentDto commentDto);
}

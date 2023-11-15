package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Comment {


    void writeComment(CommentWriteDto commentWriteDto);

    List<CommentListDto> getCommentList(Long boardId);

    void writeReply(ReplyWriteDto replyWriteDto);

    List<ReplyListDto> getReplyList(ReplyDto replyDto);

    void deleteComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    List<myCommentsDto> myComments(Long userId);
}

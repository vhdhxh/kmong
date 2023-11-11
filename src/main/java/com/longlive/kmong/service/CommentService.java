package com.longlive.kmong.service;

import com.longlive.kmong.DAO.Comment;
import com.longlive.kmong.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final Comment comment;

    public void writeComment(CommentWriteDto commentWriteDto) {
        comment.writeComment(commentWriteDto);
    }

    public List<CommentListDto> getCommentList(Long boardId) {
        return comment.getCommentList(boardId);
    }

    public void writeReply(ReplyWriteDto replyWriteDto) {
        comment.writeReply(replyWriteDto);
    }

    public List<ReplyListDto> getReplyList(ReplyDto replyDto) {
        return comment .getReplyList(replyDto);
    }

    public void deleteComment(CommentDto commentDto) {
        comment.deleteComment(commentDto);
    }

    public void updateComment(CommentDto commentDto) {
        comment.updateComment(commentDto);
    }
}

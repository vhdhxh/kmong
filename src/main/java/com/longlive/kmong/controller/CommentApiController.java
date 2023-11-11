package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.*;
import com.longlive.kmong.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController

@RequestMapping("/api/comment")
public class CommentApiController {
    private final CommentService commentService;

//    댓글 리스트
    @GetMapping("/{id}")
    public ResponseEntity comment(@PathVariable("id") Long boardId) {
      List<CommentListDto> commentList = commentService.getCommentList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

//    댓글 쓰기
    @PostMapping("")
    public ResponseEntity commentWrite(CommentWriteDto commentWriteDto) {
        commentService.writeComment(commentWriteDto);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
// 대댓글 쓰기
    @PostMapping("/reply")
    public ResponseEntity replyWrite(ReplyWriteDto replyWriteDto) {
        commentService.writeReply(replyWriteDto);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
    //댓글 삭제
    @DeleteMapping("")
    public ResponseEntity deleteComment(CommentDto commentDto) {
        commentService.deleteComment(commentDto);
        return null;
    }
    //댓글 수정
    @PutMapping ("")
    public ResponseEntity updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);

return null;
    }

}

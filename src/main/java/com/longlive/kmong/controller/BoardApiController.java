package com.longlive.kmong.controller;


import com.longlive.kmong.DTO.BoardDTO;
import com.longlive.kmong.config.auth.PrincipalDetails;
import com.longlive.kmong.service.BoardService;
import com.longlive.kmong.service.FileUploadService;
import com.longlive.kmong.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {

    private final FileUploadService fileUploadService;
    private final BoardService boardService;
    private final S3UploadService s3UploadService;

    /*
    판매글쓰기
     */
    @PostMapping("")
        public ResponseEntity write(@RequestPart ("thumbnail") MultipartFile file, BoardDTO boardDTO, @AuthenticationPrincipal PrincipalDetails principalDetails ) throws IOException {
        System.out.println(file.getOriginalFilename());
        System.out.println(boardDTO);
//      String fileName = fileUploadService.upload(file);
        String fileName = s3UploadService.uploadFile(file);
        System.out.println(fileName);
      boardDTO.setBoard_thumbnail(fileName);
      boardDTO.setUser_Id(principalDetails.getDto().getUser_id());
        long boardId = boardService.insertboard(boardDTO);
        System.out.println(boardId);
        System.out.println(boardDTO.getBoard_Id());
        return ResponseEntity.status(HttpStatus.OK).body(boardDTO.getBoard_Id());
        }

        /*
    판매글불러오기
     */
        @GetMapping("/{board_id}")
        public ResponseEntity detail(@PathVariable String board_id) {
           BoardDTO boardDTO = boardService.findPost(board_id);
          return ResponseEntity.status(HttpStatus.OK).body(boardDTO);
        }

         /*
    판매글수정
     */
      @PostMapping("/{board_id}")
      public ResponseEntity update(@RequestPart("thumbnail") MultipartFile file,@PathVariable Long board_id,BoardDTO boardDTO) {
          String fileName = fileUploadService.upload(file);
          System.out.println(boardDTO);
          boardDTO.setBoard_thumbnail(fileName);
          boardDTO.setBoard_Id(board_id);
            boardService.updatePost(boardDTO);

          return ResponseEntity.status(HttpStatus.OK).body(board_id);
      }

        /*
    판매글삭제
     */
    @DeleteMapping("/{board_id}")
    public ResponseEntity delete(@PathVariable String board_id) {
        boardService.deletePost(board_id);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

//        /*
//        판매글 수정
//         */
//     @PostMapping("/{id}")
//       public ResponseEntity update(@RequestPart ("thumbnail") MultipartFile file, BoardDTO boardDTO,@PathVariable("id")String id) {
//
//         String fileName = fileUploadService.upload(file);
//         boardDTO.setThumbnail(fileName);
//         Map<String, Object> map =new HashMap<>();
//         map.put("id",id);
//         map.put("board",boardDTO);
//         boardservice.updateboard(map);
//         return ResponseEntity.status(HttpStatus.OK).body("");
//     }
//     /*
//     판매글 삭제
//     */
//
//    @DeleteMapping("/{id}")



}

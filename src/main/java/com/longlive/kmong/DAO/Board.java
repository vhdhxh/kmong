package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.BoardDTO;
import com.longlive.kmong.DTO.BoardListDto;
import com.longlive.kmong.DTO.ItemDto;
import com.longlive.kmong.DTO.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Board {
    long insertboard(BoardDTO boardDTO);

    List<BoardListDto> findAll(final SearchDto params);
    int count(SearchDto params);

    BoardDTO findPost(String boardId);

    void deletePost(String boardId);

    void updatePost(BoardDTO boardDTO);

    ItemDto getOrder(String id);


}



package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.BoardDTO;
import com.longlive.kmong.DTO.BoardListDto;
import com.longlive.kmong.DTO.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Board {
    long insertboard(BoardDTO boardDTO);

    List<BoardListDto> findAll(final SearchDto params);
    int count(SearchDto params);

    BoardDTO findPost(String boardId);
}



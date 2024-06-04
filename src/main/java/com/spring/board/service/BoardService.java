package com.spring.board.service;

import com.spring.board.dto.BoardDTO;
import com.spring.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<Board> boardList(String keyword, Pageable pageable) throws Exception;

    Board writeBoard(BoardDTO board) throws Exception;

    Board getBoard(Long id);

    Board updateBoard(BoardDTO boardDto);

    boolean deleteBoard(Long id, String password);
}

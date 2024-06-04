package com.spring.board.service;

import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Board;

public interface CommentService {
    Board newComment(CommentDTO commentDto);

    boolean deleteComment(Long id, String password);
}

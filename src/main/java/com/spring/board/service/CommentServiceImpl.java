package com.spring.board.service;

import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Board;
import com.spring.board.entity.Comment;
import com.spring.board.repository.CommentRepository;
import com.spring.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{

    final private CommentRepository commentRepository;
    @Override
    public Board newComment(CommentDTO commentDto) {
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .writer(commentDto.getWriter())
                .password(commentDto.getPassword())
                .createdAt(LocalDateTime.now())
                .board(commentDto.getBoard())
                .build();

        return commentRepository.save(comment).getBoard();
    }

    @Override
    public boolean deleteComment(Long id, String password) {
        Comment oldComment = commentRepository.findById(id).orElseThrow(() -> new CustomException("잘못된 댓글입니다.", "/board/list"));
        if (!oldComment.getPassword().equals(password)) {
            // 비밀번호가 일치하지 않는 경우에는 CustomException을 발생시킵니다.
//            throw new CustomException("비밀번호가 일치하지 않습니다.", "/board/" + id);
            return false;

        }
        commentRepository.deleteById(id);
        return true;
    }
}

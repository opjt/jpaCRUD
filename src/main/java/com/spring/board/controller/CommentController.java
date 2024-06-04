package com.spring.board.controller;

import com.spring.board.dto.BoardDTO;
import com.spring.board.dto.CommentDTO;
import com.spring.board.entity.Board;
import com.spring.board.service.BoardService;
import com.spring.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    final private CommentService commentService;
    final private BoardService boardService;

    @PostMapping("/comment/{id}")
    public String saveBoard(@PathVariable("id") Long id,  CommentDTO commentDto) throws Exception {
        log.info(commentDto.toString());
        Board board = boardService.getBoard(id);
        if (board == null) {
            String errorMessage = URLEncoder.encode("찾을 수 없는 글입니다", "UTF-8");
            return "redirect:/errorPage?errorMessage=" + errorMessage;
        }
        commentDto.setBoardId(id);
        commentDto.setBoard(board);
        Board resBoard = commentService.newComment(commentDto);
        return "redirect:/board/" + resBoard.getId();
    }

    @PostMapping("/comment/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteComment(@PathVariable("id") Long id, String password) throws Exception {
        boolean ret = commentService.deleteComment(id,password);
        if(!ret) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

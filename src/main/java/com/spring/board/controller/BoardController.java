package com.spring.board.controller;


import com.spring.board.dto.BoardDTO;
import com.spring.board.dto.PageDTO;
import com.spring.board.entity.Board;
import com.spring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;




    @GetMapping("/board/list")
    public String listBoards(PageDTO pageDTO, Model model) throws Exception {
        Pageable pageable = PageRequest.of(pageDTO.getPage()-1, pageDTO.getSize());
        Page<Board> boardPage = boardService.boardList(pageDTO.getKeyword(), pageable);
        pageDTO.setTotalPages(boardPage.getTotalPages());
        pageDTO.setStartPage(Math.max(1, pageDTO.getPage() - 2));
        pageDTO.setEndPage(Math.min(boardPage.getTotalPages(), pageDTO.getPage() + 2));
        model.addAttribute("boardPage", boardPage);
        model.addAttribute("page", pageDTO);
        return "board/list";
    }

    @GetMapping("/board/write")
    public String writeBoard(Model model) throws Exception {
        model.addAttribute("board", new BoardDTO());
        return "board/write";
    }

    @PostMapping("/board/")
    public String saveBoard(@ModelAttribute("board") BoardDTO board) throws Exception {
        Board updateBoard = boardService.writeBoard(board);
        return "redirect:/board/list";
    }

    @GetMapping("/board/{id}")
    public String selectBoard(@PathVariable("id") Long id, Model model) throws Exception {
        Board board = boardService.getBoard(id);
        if (board == null) {
            String errorMessage = URLEncoder.encode("찾을 수 없는 글입니다", "UTF-8");
            return "redirect:/errorPage?errorMessage=" + errorMessage;
        }
        model.addAttribute("board", board);
        return "board/read";
    }

    @GetMapping("/board/modify/{id}")
    public String modifyBoard(@PathVariable("id") Long id, Model model) throws Exception {
        Board board = boardService.getBoard(id);
        if (board == null) {
            String errorMessage = URLEncoder.encode("찾을 수 없는 글입니다", "UTF-8");
            return "redirect:/errorPage?errorMessage=" + errorMessage;
        }
        model.addAttribute("board", board);
        return "board/modify";
    }

    @PostMapping("/board/modify/{id}")
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute("board") BoardDTO boardDto) throws Exception {
        boardDto.setId(id); // 기존 게시물의 ID를 설정하여 업데이트를 수행
        log.info(boardDto.toString());
        Board board = boardService.updateBoard(boardDto);
        return "redirect:/board/list";
    }

    @PostMapping("/board/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteBoard(@PathVariable("id") Long id, String password) throws Exception {
        boolean ret = boardService.deleteBoard(id,password);
        if(!ret) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

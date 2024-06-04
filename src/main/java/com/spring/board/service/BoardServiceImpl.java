package com.spring.board.service;

import com.spring.board.dto.BoardDTO;
import com.spring.board.entity.Board;
import com.spring.board.repository.BoardRepository;
import com.spring.util.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public Page<Board> boardList(String keyword, Pageable pageable) throws Exception {
        if (StringUtils.hasText(keyword)) {
            return boardRepository.searchByKeyword(keyword, pageable);
        } else {
            return boardRepository.findAllByOrderByIdDesc(pageable);
        }
    }

    @Override
    public Board writeBoard(BoardDTO boardDTO) throws Exception {
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .password(boardDTO.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long id) {
//        return boardRepository.findById(id).orElseThrow(() -> new CustomException("해당 게시물을 찾을 수 없습니다.", "/board/list"));
        return boardRepository.findById(id).orElse(null);

    }

    @Override
    public Board updateBoard(BoardDTO boardDto) {

        Board oldBoard = boardRepository.findById(boardDto.getId())
                .orElseThrow(() -> new CustomException("해당 게시물을 찾을 수 없습니다.", "/board/list"));

        if (!oldBoard.getPassword().equals(boardDto.getPassword())) {
            // 비밀번호가 일치하지 않는 경우에는 CustomException을 발생시킵니다.
            throw new CustomException("비밀번호가 일치하지 않습니다.", "/board/" + boardDto.getId());
        }
        Board board = Board.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(oldBoard.getWriter())
                .password(boardDto.getPassword())
                .createdAt(oldBoard.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        return boardRepository.save(board);
    }

    @Override
    public boolean deleteBoard(Long id, String password) {
        Board oldBoard = boardRepository.findById(id).orElseThrow(() -> new CustomException("해당 게시물을 찾을 수 없습니다.", "/board/list"));
        if (!oldBoard.getPassword().equals(password)) {
            // 비밀번호가 일치하지 않는 경우에는 CustomException을 발생시킵니다.
//            throw new CustomException("비밀번호가 일치하지 않습니다.", "/board/" + id);
            return false;

        }
        boardRepository.deleteById(id);
        return true;
    }
}

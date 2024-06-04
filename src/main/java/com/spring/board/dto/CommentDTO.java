package com.spring.board.dto;

import com.spring.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long boardId; //
    private String content; //내용
    private String writer; //
    private String password;

    private Board board;
}

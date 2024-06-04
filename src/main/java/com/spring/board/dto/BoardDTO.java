package com.spring.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long Id; //게시판 일련번호
    private String title; //제목
    private String content; //내용
    private String writer; //
    private String password;

}

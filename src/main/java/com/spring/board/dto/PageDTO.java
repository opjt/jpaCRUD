package com.spring.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private int page = 1; //페이지 넘버
    private int size = 5; //한페이지에 보여지는 글 수
    private String keyword; //검색어

    private int totalPages; //총페이지수
    private int startPage; //첫번째 페이지
    private int endPage;  //마지막페이지
}

package com.hyelin.task.dto.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardRequest {
    private String title;
    private String writer;
    private String content;
    private String password;

}

package com.hyelin.task.dto.board;

import com.hyelin.task.domain.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    public BoardResponse(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.writer = entity.getWriter();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
    }
}

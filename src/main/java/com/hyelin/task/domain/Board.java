package com.hyelin.task.domain;

import com.hyelin.task.dto.board.BoardRequest;
import com.hyelin.task.dto.board.BoardUpdateRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "board")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    public Board (BoardRequest dto) {
        this.title = dto.getTitle();
        this.writer = dto.getWriter();
        this.content = dto.getContent();
        this.password = dto.getPassword();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void update(BoardUpdateRequest body) {
        String newTitle = body.getTitle() == null ? this.title : body.getTitle();
        String newCotent = body.getContent() == null ? this.content: body.getContent();

        this.setTitle(newTitle);
        this.setContent(newCotent);
    }
}

package com.hyelin.task.controller;

import com.hyelin.task.dto.board.BoardDeleteRequest;
import com.hyelin.task.dto.board.BoardRequest;
import com.hyelin.task.dto.board.BoardResponse;
import com.hyelin.task.dto.board.BoardUpdateRequest;
import com.hyelin.task.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    /**
     * 게시글 작성 API
     */
    @PostMapping("/new")
    public ResponseEntity<BoardResponse> create(@RequestBody BoardRequest body) {
        BoardResponse result = boardService.create(body);
        return ResponseEntity.status(201).body(result);
    }

    /**
     * 전체 게시글 조회 API
     * 생성일 내림차순으로 게시
     */
    @GetMapping("")
    public List<BoardResponse> getList() {
        return boardService.getList();

    }

    /**
     * 선택 게시글 조회 API
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getOne(@PathVariable(required = true) Long id) {
        return ResponseEntity.ok(boardService.getOne(id));
    }

    /**
     * 수정
     */
    @PutMapping("/{id}/edit")
    public ResponseEntity<BoardResponse> update(@PathVariable Long id, @RequestBody BoardUpdateRequest body) {
        BoardResponse result = boardService.update(id,body);
        return ResponseEntity.ok(result);
    }

    /**
     * 삭제 (id와 비번 일치 삭제)
     */
    @PostMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable(required = true) Long id, @RequestBody(required = true) BoardDeleteRequest body) {
        boolean result = boardService.delete(id, body);
        if (!result) {
            return ResponseEntity.badRequest().body("삭제를 실패했습니다");
        }
        return ResponseEntity.ok("success");
    }
}

package com.hyelin.task.service;

import com.hyelin.task.domain.Board;
import com.hyelin.task.dto.board.BoardDeleteRequest;
import com.hyelin.task.dto.board.BoardRequest;
import com.hyelin.task.dto.board.BoardResponse;
import com.hyelin.task.dto.board.BoardUpdateRequest;
import com.hyelin.task.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    
    public BoardResponse create(BoardRequest request) {
        Board result = boardRepository.save(new Board(request));
        return new BoardResponse(result);
    }

    public List<BoardResponse> getList() {
        return boardRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(BoardResponse::new)
                .toList();
    }

    public BoardResponse getOne(Long id) {
        return boardRepository.findBoardById(id)
                .map(BoardResponse::new)
                .orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다"));
    }

    public BoardResponse update(Long id, BoardUpdateRequest body) {
        Board board = boardRepository.findBoardByIdAndPasswordEquals(id, body.getPassword())
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 비밀 번호 입니다"));

        board.update(body);
        return new BoardResponse(board);
    }

    public boolean delete(Long id, BoardDeleteRequest body) {
        System.out.println(body.getPassword());
        Board board = boardRepository.findBoardById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다"));
        if (!board.getPassword().equals(body.getPassword())) {
            return false;
        }

        try {
            boardRepository.delete(board);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}

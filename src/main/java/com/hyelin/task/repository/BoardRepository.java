package com.hyelin.task.repository;

import com.hyelin.task.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

    Optional<Board> findBoardById(Long id);

    Optional<Board> findBoardByIdAndPasswordEquals(Long id, String password);
    void delete(Board board);
}

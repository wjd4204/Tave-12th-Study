package com.practice.taveboard.board.repository;

import com.practice.taveboard.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findById(int id);

    @Query("select f from BOARD_TABLE f order by f.createdTime")
    Page<Board> findAllByCreatedTimeDesc(Pageable pageable);


}

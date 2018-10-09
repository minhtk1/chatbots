package com.robot.respository;

import com.robot.model.Replies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRespository extends JpaRepository<Replies,Long> {
    @Query(value = "SELECT * FROM replies where contexts_id = ?1", nativeQuery = true)
    List<Replies> findAllByContexts(Long id);
}

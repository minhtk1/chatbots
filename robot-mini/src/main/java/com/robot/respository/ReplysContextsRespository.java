package com.robot.respository;

import com.robot.model.Replys_Contexs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplysContextsRespository extends JpaRepository<Replys_Contexs, Long> {

    @Query(value = "select * from replys_contexs rc where rc.key_question = ?1 and rc.key_remove_utf8 = ?2", nativeQuery = true)
    Replys_Contexs findByTwoKey(String keyQuestion,String keyRemoveUtf8);
}

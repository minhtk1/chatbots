package com.robot.respository;

import com.robot.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VocabulariesRespository extends JpaRepository<Vocabularies, Long> {

    @Query(value = "select * from vocabularies v where v.word = ?1 OR v.word_not_utf8 = ?2", nativeQuery = true)
    List<Vocabularies> findWordByKey(String word, String wordNotUtf8);
}

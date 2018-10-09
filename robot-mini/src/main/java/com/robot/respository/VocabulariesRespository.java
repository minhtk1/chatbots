package com.robot.respository;

import com.robot.model.Vocabularies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabulariesRespository extends JpaRepository<Vocabularies, Long> {

    Vocabularies findByWord(String word);
    Vocabularies findByWordNotUtf8(String word);
}

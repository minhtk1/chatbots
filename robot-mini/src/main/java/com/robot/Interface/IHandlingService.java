package com.robot.Interface;

import com.robot.model.Replies;
import com.robot.model.Replys_Contexs;
import com.robot.model.Vocabularies;

import java.util.List;

public interface IHandlingService {
    Replys_Contexs findByKeyQuestion(String keyQuestion, String keyRemoveUtf8);
    List<Vocabularies> findByVocabulary(String word,String wordNotUtf8);

    // reply
    List<Replies> findReplyByContexs(Long id);

    //vocabulary
    List<Vocabularies> allWord();
}

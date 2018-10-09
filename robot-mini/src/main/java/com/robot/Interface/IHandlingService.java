package com.robot.Interface;

import com.robot.model.Replys_Contexs;
import com.robot.model.Vocabularies;

public interface IHandlingService {
    Replys_Contexs findByKeyQuestion(String keyQuestion, String keyRemoveUtf8);
    Vocabularies findByVocabulary(String word);
    Vocabularies findByVocabularyNotUtf8(String word);
}

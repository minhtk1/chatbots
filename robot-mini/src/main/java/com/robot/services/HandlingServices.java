package com.robot.services;

import com.robot.Interface.IHandlingService;
import com.robot.model.Replys_Contexs;
import com.robot.model.Vocabularies;
import com.robot.respository.ReplysContextsRespository;
import com.robot.respository.VocabulariesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class HandlingServices implements IHandlingService{

    @Autowired
    private ReplysContextsRespository replysContextsRespository;

    @Autowired
    private VocabulariesRespository vocabulariesRespository;

    @Override
    public Replys_Contexs findByKeyQuestion(String keyQuestion, String keyRemoveUtf8) {
        return replysContextsRespository.findByTwoKey(keyQuestion,keyRemoveUtf8);
    }

    @Override
    public Vocabularies findByVocabularyNotUtf8(String word) {
        return vocabulariesRespository.findByWordNotUtf8(word);
    }

    @Override
    public Vocabularies findByVocabulary(String word) {
        return vocabulariesRespository.findByWord(word);
    }
}

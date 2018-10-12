package com.robot.services;

import com.robot.Interface.IHandlingService;
import com.robot.model.Replies;
import com.robot.model.Replys_Contexs;
import com.robot.model.Vocabularies;
import com.robot.respository.ReplyRespository;
import com.robot.respository.ReplysContextsRespository;
import com.robot.respository.VocabulariesRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class HandlingServices implements IHandlingService{

    @Autowired
    private ReplysContextsRespository replysContextsRespository;

    @Autowired
    private VocabulariesRespository vocabulariesRespository;

    @Autowired
    private ReplyRespository replyRespository;

    @Override
    public Replys_Contexs findByKeyQuestion(String keyQuestion, String keyRemoveUtf8) {
        return replysContextsRespository.findByTwoKey(keyQuestion,keyRemoveUtf8);
    }

    @Override
    public List<Vocabularies> findByVocabulary(String word, String wordNotUtf8) {
        return vocabulariesRespository.findWordByKey(word,wordNotUtf8);
    }

    //contexts

    @Override
    public List<Replies> findReplyByContexs(Long id) {
        return replyRespository.findAllByContexts(id);
    }

    //vocabulary

    @Override
    public List<Vocabularies> allWord() {
        return vocabulariesRespository.findAll();
    }
}

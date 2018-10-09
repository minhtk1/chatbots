package com.robot.controller;


import com.robot.Interface.IHandlingService;
import com.robot.common.interface_common.ICMethod;
import com.robot.model.Receives;
import com.robot.model.Replys_Contexs;
import com.robot.model.Vocabularies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HandlingController {

    @Autowired
    private ICMethod icMethod;

    @Autowired
    private IHandlingService handlingService;

    @RequestMapping(
            value = "/mainHandling",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity handling(@RequestBody Receives receives) {
        try {
            String keyQuestion = receives.getKeyQuestion();
            String keyRemoveUtf8 = icMethod.removePointCodeUtf8(keyQuestion);
            Replys_Contexs replys_contexs = handlingService.findByKeyQuestion(keyQuestion, keyRemoveUtf8);
            String[] splitKeyQuestion;
            if(replys_contexs == null){
                System.out.println("data not found");
                splitKeyQuestion = keyQuestion.split(" ");
                if(splitKeyQuestion.length > 0){
                    Map<String, String> wordNotFound = this.wordNotInDictionary(splitKeyQuestion);
                    if(wordNotFound.size() > 0){
                        System.out.println("word not in dictionary");
                        return new ResponseEntity(wordNotFound,HttpStatus.OK);
                    }else{
                        splitKeyQuestion = keyQuestion.split(" ");
                    }
                }
            }
            return new ResponseEntity(null,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Map<String,String> wordNotInDictionary(String[] keyQuestion){
        Map<String, String> result = new HashMap<>();
        int lengKey = keyQuestion.length;
        int index = 0;
        boolean checked;
        boolean stop;
        Vocabularies vocabulary;
        Vocabularies vocabularyRemoveUtf8;
        String temp = "";
        String testSplitLength[];
        for(int i = 0;i < keyQuestion.length;i++){
            if(keyQuestion[i] != null){
                temp = keyQuestion[i];
                checked = false;
                stop = false;
                index = i;
                //check long key means
                while (!stop){
                    String upperCase = temp.toUpperCase();
                    String removeUtf8 = this.icMethod.removePointCodeUtf8(temp).toUpperCase();
                    vocabulary = handlingService.findByVocabulary(upperCase);
                    vocabularyRemoveUtf8 = handlingService.findByVocabularyNotUtf8(removeUtf8);
                    if(vocabulary == null && vocabularyRemoveUtf8 == null){
                        if(index == lengKey - 1){
                            temp += " " + keyQuestion[index].toUpperCase();
                        }else if(index < lengKey - 2){
                            temp += " " + keyQuestion[index+1].toUpperCase();
                        }
                    }else{
                        stop = true;
                        checked = true;
                    }

                    if(index > lengKey){
                        stop = true;
                        checked = false;
                    }

                    index++;
                }

                if(!checked){
                    result.put(keyQuestion[i] ,"not_found");
                }else{
                    testSplitLength = temp.split(" ");
                    if(testSplitLength.length > 1){
                        if(testSplitLength[1] != null && !testSplitLength[1].isEmpty()){
                            i += testSplitLength.length - 1;
                        }
                    }
                }
            }
        }
        return result;
    }
}

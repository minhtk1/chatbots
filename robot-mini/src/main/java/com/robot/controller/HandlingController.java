package com.robot.controller;


import com.robot.Interface.IHandlingService;
import com.robot.common.interface_common.ICMethod;
import com.robot.dto.RepliesDto;
import com.robot.generic.IGeneric;
import com.robot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/api")
public class HandlingController<T> {

    private String reply;

    private String suggestion;

    private Map<String, String> sortVocabulary;

    private Map<String,Contexts> contextDangleWord;

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
    ResponseEntity handling(@RequestBody Receives receives, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            //get init
            List<Vocabularies> allWord = handlingService.allWord();
            String keyQuestion = receives.getKeyQuestion();
            String keyRemoveUtf8 = icMethod.removePointCodeUtf8(keyQuestion);
            Replys_Contexs replys_contexs = handlingService.findByKeyQuestion(keyQuestion, keyRemoveUtf8);
            String[] splitKeyQuestion;
            RepliesDto resultReply = new RepliesDto();
            if(replys_contexs == null){
                System.out.println("data not found");
                splitKeyQuestion = keyQuestion.split(" ");
                if(splitKeyQuestion.length > 0){
                    //Okie and handling
                    sortVocabulary = this.wordNotInDictionary(splitKeyQuestion);
                    contextDangleWord = this.getContextByWord(sortVocabulary,"means"); //word means

                    //suggestion false information
                    this.suggestions(allWord, keyQuestion, sortVocabulary,"not_found",session);
                    Set<String> questionBots = this.returnQuesttionBotsToYou("not_found"); // return word no meaning

                    //get reply
                    if(contextDangleWord.size() > 0){
                        reply = this.returnReply(contextDangleWord);
                    }

                    if(!reply.isEmpty()){
                        reply = reply.trim();
                    }

                    //end and return result replys.
                    resultReply.setReply(reply);
                    resultReply.setSuggestion(suggestion);
                    resultReply.setQuestionBots(questionBots);
                    resultReply.setFlagProcess(0);

                    return new ResponseEntity(resultReply ,HttpStatus.OK);
                }
            }
            return new ResponseEntity(null,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String returnReply(Map<String, Contexts> contextDangleWord){
        List<Replies> repliesList;
        Replies repliesObj;
        Map<String, List<Replies>> result = new HashMap<>();
        String resultReply = "";
        for (Map.Entry<String, Contexts> entry : contextDangleWord.entrySet()){
            repliesList = handlingService.findReplyByContexs(entry.getValue().getId());
            if(repliesList.size() > 0){
                result.put(entry.getKey(),repliesList);
            }
        }
        for (Map.Entry<String, List<Replies>> entry : result.entrySet()){
            repliesList = entry.getValue();
            repliesObj = IGeneric.getRandomList(repliesList);
            resultReply += repliesObj.getSymboyReplies() + " ";
        }
        return resultReply;
    }

    private Set<String> returnQuesttionBotsToYou(String status) {
        Set<String> result = new HashSet<>();
        String temp = "";
        for (Map.Entry<String, String> entry : sortVocabulary.entrySet()){
            if(status.equals(entry.getValue())){
                temp = "Từ " + entry.getKey().toLowerCase() + " tôi không hiểu";
                if(!temp.isEmpty()){
                    result.add(temp);
                }
            }
        }
        return result;
    }

    private void suggestions(List<Vocabularies> allWord,String fullkeyQuestion,Map<String, String> sortVocabulary,String status, HttpSession session){
        String result = "";
        int index;
        int limted;
        int lengthWord;
        int randomInt;
        boolean pos;
        List<Integer[]> listIndexChar;
        Integer[] indexChar;
        String[] splitWord;
        String temp;
        String fullKeyQuestionIsReplace = fullkeyQuestion;
        for (Map.Entry<String, String> entry : sortVocabulary.entrySet()){
            if(status.equals(entry.getValue()) && entry.getKey().length() < 7) {
                splitWord = entry.getKey().split("(?!^)");
                listIndexChar = new ArrayList<>();
                lengthWord = entry.getKey().length();
                limted = icMethod.factorial(lengthWord);
                indexChar = new Integer[lengthWord];
                Random rn = new Random();
                index = 0;
                while (listIndexChar.size() < limted){
                    randomInt = rn.nextInt(lengthWord);
                    pos = IGeneric.indexOfArrays(indexChar,randomInt);
                    if(!pos){
                        indexChar[index] = randomInt;
                        index++;
                    }
                    if(index % lengthWord == 0 && index > 0){
                        if(listIndexChar.size() > 0){
                            if(!IGeneric.listEqualArrays(listIndexChar,indexChar)){
                                listIndexChar.add(indexChar);
                            }
                        }else{
                            listIndexChar.add(indexChar);
                        }
                        indexChar = new Integer[lengthWord];
                        index = 0;
                    }
                }
                for(Integer[] element:listIndexChar){
                    temp = "";
                    for(Integer indexWord: element){
                        temp+=splitWord[indexWord];
                    }
                    try {
                        result = IGeneric.findStringOfList(allWord, temp);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    if(result != null & fullkeyQuestion != null){
                        String keyFind = entry.getKey().trim();
                        String keyReplace = result.trim();
                        fullKeyQuestionIsReplace = fullKeyQuestionIsReplace.toLowerCase().replace(keyFind.toLowerCase() , keyReplace.toLowerCase());
                    }
                }
            }
        }
        //suggestion
        suggestion = fullKeyQuestionIsReplace;
        // recursive reply is conrrect
        splitWord = fullKeyQuestionIsReplace.split(" ");
        sortVocabulary = this.wordNotInDictionary(splitWord);
        contextDangleWord = this.getContextByWord(sortVocabulary,"means"); //word means
        //set data
        setSortVocabulary(sortVocabulary);
        setContextDangleWord(contextDangleWord);
    }

    private Map<String,Contexts> getContextByWord(Map<String,String> wordMean,String status){
        Map<String, Contexts> result = new HashMap<>();
        List<Vocabularies> vocabulary;
        for (Map.Entry<String, String> entry : wordMean.entrySet()){
            if("means".equals(status)){
                String upperCase = entry.getKey().toUpperCase();
                String removeUtf8 = this.icMethod.removePointCodeUtf8(entry.getKey()).toUpperCase();
                vocabulary = handlingService.findByVocabulary(upperCase,removeUtf8);
                if(vocabulary.size() > 1){
                    for(Vocabularies data:vocabulary){
                        result.put(data.getWord(),data.getContexts());
                    }
                }else if(vocabulary.size() > 0){
                    result.put(vocabulary.get(0).getWord(),vocabulary.get(0).getContexts());
                }
            }
        }
        return result;
    }

    private Map<String,String> wordNotInDictionary(String[] keyQuestion){
        Map<String, String> result = new HashMap<>();
        int lengKey = keyQuestion.length;
        int index = 0;
        boolean checked;
        boolean stop;
        List<Vocabularies> vocabulary;
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
                    vocabulary = handlingService.findByVocabulary(upperCase,removeUtf8);
                    if(vocabulary.size() == 0){
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
                    result.put(keyQuestion[i].toUpperCase() ,"not_found");
                }else{
                    result.put(temp.toUpperCase() , "means");
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

    public void setSortVocabulary(Map<String, String> sortVocabulary) {
        this.sortVocabulary = sortVocabulary;
    }

    public void setContextDangleWord(Map<String, Contexts> contextDangleWord) {
        this.contextDangleWord = contextDangleWord;
    }
}

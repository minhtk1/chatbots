package com.robot.dto;

import java.util.Map;
import java.util.Set;

public class RepliesDto {
    private String reply;
    private String suggestion;
    private Set<String> questionBots;
    private Integer flagProcess;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Integer getFlagProcess() {
        return flagProcess;
    }

    public void setFlagProcess(Integer flagProcess) {
        this.flagProcess = flagProcess;
    }

    public Set<String> getQuestionBots() {
        return questionBots;
    }

    public void setQuestionBots(Set<String> questionBots) {
        this.questionBots = questionBots;
    }
}

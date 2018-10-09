package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Entity
@Table
public class Receives implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 2000)
    private String keyQuestion;

    @Column(length = 1)
    private int flagProcess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyQuestion() {
        return keyQuestion;
    }

    public void setKeyQuestion(String keyQuestion) {
        this.keyQuestion = keyQuestion;
    }

    public int getFlagProcess() {
        return flagProcess;
    }

    public void setFlagProcess(int flagProcess) {
        this.flagProcess = flagProcess;
    }
}

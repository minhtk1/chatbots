package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Replies implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "s_replies", length = 5000)
    private String symboyReplies;

    @Column(length = 1)
    private int level;

    @Column(length = 50)
    private String emotion;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "contexts_id",referencedColumnName = "id")
    private Contexts contexts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymboyReplies() {
        return symboyReplies;
    }

    public void setSymboyReplies(String symboyReplies) {
        this.symboyReplies = symboyReplies;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public Contexts getContexts() {
        return contexts;
    }

    public void setContexts(Contexts contexts) {
        this.contexts = contexts;
    }
}

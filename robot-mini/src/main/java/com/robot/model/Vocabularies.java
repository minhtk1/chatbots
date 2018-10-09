package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Vocabularies implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true)
    private String word;

    @Column(name = "word_not_utf8")
    private String wordNotUtf8;

    @Column(length = 50)
    private String language;

    @Column
    private int oftenUsed;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "m_contexts_id", referencedColumnName = "id")
    private Contexts contexts;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "m_typeVocabularies_id",referencedColumnName = "id")
    private TypeVocabularies typeVocabularies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordNotUtf8() {
        return wordNotUtf8;
    }

    public void setWordNotUtf8(String wordNotUtf8) {
        this.wordNotUtf8 = wordNotUtf8;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getOftenUsed() {
        return oftenUsed;
    }

    public void setOftenUsed(int oftenUsed) {
        this.oftenUsed = oftenUsed;
    }

    public Contexts getContexts() {
        return contexts;
    }

    public void setContexts(Contexts contexts) {
        this.contexts = contexts;
    }

    public TypeVocabularies getTypeVocabularies() {
        return typeVocabularies;
    }

    public void setTypeVocabularies(TypeVocabularies typeVocabularies) {
        this.typeVocabularies = typeVocabularies;
    }
}

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

    @Column(name = "word_not_utf8", unique = true)
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
}

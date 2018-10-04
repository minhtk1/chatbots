package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "handling")
public class Handling implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Receives receives;

    @Embedded
    private TypeVocabularies typeVocabularys;

    @Embedded
    private Cultural cultural;

    @Embedded
    private Replys_Contexs replys_contexs;

}

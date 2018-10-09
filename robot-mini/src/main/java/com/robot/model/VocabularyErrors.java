package com.robot.model;

import javax.persistence.*;

@Entity
@Table
public class VocabularyErrors {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}

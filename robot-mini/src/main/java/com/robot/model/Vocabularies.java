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

    @Column(length = 50)
    private String word;

    @Column(length = 50)
    private String language;
}

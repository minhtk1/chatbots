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

    @Column(length = 1)
    private int flagProcess;

    @Column(length = 2000)
    private String keyQuestion;

    @Column(length = 2000)
    private String keyRemoveUtf8;

    @Embedded
    private Cultural cultural;
}

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
}

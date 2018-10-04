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
}

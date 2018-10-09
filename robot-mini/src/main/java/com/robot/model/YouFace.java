package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "you_face")
@Entity
public class YouFace implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String fullName;

    @Column
    private String fullNameRemoveUtf8;

    @Column(length = 5)
    private String gender;

    @Column(length = 1)
    private int meted;

    @Column
    private Date dateVisited;
}

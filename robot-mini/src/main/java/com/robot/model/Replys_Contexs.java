package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Entity
@Table
public class Replys_Contexs implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 60, name = "context_name")
    private String contextName;

    @Column(length = 1)
    private int level;

    @Column(length = 50)
    private String emotion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "replies" , referencedColumnName = "id")
    private List<Replies> replies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contexts", referencedColumnName = "id")
    private List<Contexts> contexts = new ArrayList<>();
}
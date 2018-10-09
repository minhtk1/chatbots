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

    @Column(length = 2000,unique = true)
    private String keyQuestion;

    @Column(length = 2000,unique = true)
    private String keyRemoveUtf8;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Replys_Contexs_id" , referencedColumnName = "id")
    private List<Replies> replies = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "Replys_Contexs_id", referencedColumnName = "id")
    private List<Contexts> contexts = new ArrayList<>();
}

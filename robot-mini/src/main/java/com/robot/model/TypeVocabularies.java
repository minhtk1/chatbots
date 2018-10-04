package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Entity
@Table
public class TypeVocabularies implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    private String type;

    @Column(length = 1)
    private int level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vocabularies", referencedColumnName = "id")
    private List<Vocabularies> vocabularies = new ArrayList<>();

}

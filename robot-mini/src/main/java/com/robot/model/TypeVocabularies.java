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

    @Column(unique = true,length = 50)
    private String type;

    @Column(length = 1)
    private int level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TypeVocabularies_id", referencedColumnName = "id",nullable = true)
    private List<Vocabularies> vocabularies = new ArrayList<>();

}

package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Contexts implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String contextName;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "Contexts_id", referencedColumnName = "id",nullable = true)
    private List<Vocabularies> vocabularies = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public List<Vocabularies> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<Vocabularies> vocabularies) {
        this.vocabularies = vocabularies;
    }
}

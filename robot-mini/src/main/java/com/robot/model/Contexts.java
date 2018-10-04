package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Contexts implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String statement;
}

package com.robot.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Entity
@Table
public class Cultural implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "s_inornate")
    private String symboyInornate;

    @Column(length = 50)
    private String emotion;

}

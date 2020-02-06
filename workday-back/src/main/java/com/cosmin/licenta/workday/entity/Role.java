package com.cosmin.licenta.workday.entity;

import com.cosmin.licenta.workday.util.RoleType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "workday_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;

    public Role() {

    }
    public Role(RoleType name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}

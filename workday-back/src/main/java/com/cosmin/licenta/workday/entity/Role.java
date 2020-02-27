package com.cosmin.licenta.workday.entity;

import com.cosmin.licenta.workday.util.RoleTypeEnum;

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
    private RoleTypeEnum name;

    public Role() {

    }
    public Role(RoleTypeEnum name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleTypeEnum getName() {
        return name;
    }

    public void setName(RoleTypeEnum name) {
        this.name = name;
    }
}

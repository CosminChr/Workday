package com.cosmin.licenta.workday.entity;

import com.cosmin.licenta.workday.entity.keys.EmployeeRolePK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "workday_employee_role")
public class EmployeeRole implements Serializable {

    @EmbeddedId
    private EmployeeRolePK id;

    public EmployeeRolePK getId() {
        return id;
    }

    public void setId(EmployeeRolePK id) {
        this.id = id;
    }
}

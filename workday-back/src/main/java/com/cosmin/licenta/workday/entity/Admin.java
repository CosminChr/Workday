package com.cosmin.licenta.workday.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "workday_admin")
public class Admin extends User implements Serializable {

    public Admin() {
    }

    public Admin(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleReferential role;

    public RoleReferential getRole() {
        return role;
    }

    public void setRole(RoleReferential role) {
        this.role = role;
    }
}

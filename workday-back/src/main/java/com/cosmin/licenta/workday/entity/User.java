package com.cosmin.licenta.workday.entity;

import java.util.Collections;
import java.util.Set;

public abstract class User {

    public abstract Long getId();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public Role getRole() {
        return new Role();
    }

    public Set<Role> getRoles() {
        return Collections.EMPTY_SET;
    }
}

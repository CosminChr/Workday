package com.cosmin.licenta.workday.entity;

import java.util.Collections;
import java.util.Set;

public abstract class User {

    public abstract Long getId();

    public abstract String getUsername();

    public abstract String getEmail();

    public abstract String getPassword();

    public RoleReferential getRole() {
        return new RoleReferential();
    }

    public Set<RoleReferential> getRoles() {
        return Collections.EMPTY_SET;
    }
}

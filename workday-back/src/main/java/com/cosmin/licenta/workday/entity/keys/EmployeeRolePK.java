package com.cosmin.licenta.workday.entity.keys;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeeRolePK implements Serializable {

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "role_id", nullable = false)
    private long roleId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getRoleId() {
        return roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRolePK that = (EmployeeRolePK) o;
        return employeeId == that.employeeId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employeeId, roleId);
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}

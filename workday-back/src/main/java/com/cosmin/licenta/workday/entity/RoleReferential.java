package com.cosmin.licenta.workday.entity;

import com.cosmin.licenta.workday.util.RoleTypeEnum;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "workday_role_ref")
public class RoleReferential extends AbstractReferential implements Serializable {

}

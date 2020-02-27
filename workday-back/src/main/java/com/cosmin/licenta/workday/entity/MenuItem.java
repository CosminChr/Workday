package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workday_menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String path;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String collapse;

    @NotBlank
    @Size(max = 20)
    @Column(name = "icon_name")
    private String iconName;

    @OneToMany(mappedBy = "menuItem")
    @JsonManagedReference
    private List<SubMenuItem> subMenuItemList = new ArrayList<>();

    public MenuItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollapse() {
        return collapse;
    }

    public void setCollapse(String collapse) {
        this.collapse = collapse;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public List<SubMenuItem> getSubMenuItemList() {
        return subMenuItemList;
    }

    public void setSubMenuItemList(List<SubMenuItem> subMenuItemList) {
        this.subMenuItemList = subMenuItemList;
    }
}

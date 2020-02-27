package com.cosmin.licenta.workday.dto.response;

import com.cosmin.licenta.workday.entity.SubMenuItem;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDTO {

    @NotBlank
    @Size(max = 20)
    private String path;

    @NotBlank
    @Size(max = 20)
    private String name;

    @Nullable
    @Size(max = 20)
    private String collapse;

    @NotBlank
    @Size(max = 20)
    private String iconName;

    private List<SubMenuItem> subMenuItemList = new ArrayList<>();

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

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", collapse='" + collapse + '\'' +
                ", iconName='" + iconName + '\'' +
                ", subMenuItemList=" + subMenuItemList +
                '}';
    }
}

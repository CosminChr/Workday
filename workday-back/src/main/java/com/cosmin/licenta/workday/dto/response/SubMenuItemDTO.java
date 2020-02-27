package com.cosmin.licenta.workday.dto.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SubMenuItemDTO {

    @NotBlank
    @Size(max = 30)
    private String path;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 5)
    private String acronym;

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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Override
    public String toString() {
        return "SubMenuItemDTO{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }
}

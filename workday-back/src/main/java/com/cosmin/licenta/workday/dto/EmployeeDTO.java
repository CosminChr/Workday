package com.cosmin.licenta.workday.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDTO {

    private Long id;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 30)
    private String password;

    @Size(max = 40)
    private String lastName;

    @Size(max = 40)
    private String firstName;

    @NotNull
    private ReferentialDTO gender;

    @Size(max = 40)
    private String birthPlace;

    @Size(max = 13)
    private String personIdentifier;

    private LocalDate birthDate;

    @Size(max = 40)
    private String birthName;

    @Size(max = 10)
    private String homePhoneNumber;

    @Size(max = 10)
    private String mobilePhoneNumber;

    @NotNull
    private ReferentialDTO jobPosition;

    @Size(max = 30)
    private String entity;

    @Size(max = 30)
    private String location;

    @NotNull
    private ReferentialDTO department;

    private boolean ITDeduction;

    private LocalDate joiningDate;

    private LocalDate currentPositionStartingDate;

    @NotEmpty
    @NotNull
    private Set<ReferentialDTO> roles = new HashSet<>();
}

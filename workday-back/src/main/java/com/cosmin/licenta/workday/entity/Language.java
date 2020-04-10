package com.cosmin.licenta.workday.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "workday_language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "language_id")
    private LanguageReferential language;

    @OneToOne
    @JoinColumn(name = "reading_id")
    private LanguageLevelReferential reading;

    @OneToOne
    @JoinColumn(name = "writing_id")
    private LanguageLevelReferential writing;

    @OneToOne
    @JoinColumn(name = "speaking_id")
    private LanguageLevelReferential speaking;

    @OneToOne
    @JoinColumn(name = "overall_level_id")
    private LanguageLevelReferential overallLevel;

    @Lob
    private byte[] certification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LanguageReferential getLanguage() {
        return language;
    }

    public void setLanguage(LanguageReferential language) {
        this.language = language;
    }

    public LanguageLevelReferential getReading() {
        return reading;
    }

    public void setReading(LanguageLevelReferential reading) {
        this.reading = reading;
    }

    public LanguageLevelReferential getWriting() {
        return writing;
    }

    public void setWriting(LanguageLevelReferential writing) {
        this.writing = writing;
    }

    public LanguageLevelReferential getSpeaking() {
        return speaking;
    }

    public void setSpeaking(LanguageLevelReferential speaking) {
        this.speaking = speaking;
    }

    public LanguageLevelReferential getOverallLevel() {
        return overallLevel;
    }

    public void setOverallLevel(LanguageLevelReferential overallLevel) {
        this.overallLevel = overallLevel;
    }

    public byte[] getCertification() {
        return certification;
    }

    public void setCertification(byte[] certification) {
        this.certification = certification;
    }
}

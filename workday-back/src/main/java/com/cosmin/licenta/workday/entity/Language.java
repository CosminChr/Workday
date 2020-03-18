package com.cosmin.licenta.workday.entity;

import javax.persistence.*;

@Entity
@Table(name = "workday_language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private LanguageReferential language;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reading_id")
    private LanguageLevelReferential reading;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writing_id")
    private LanguageLevelReferential writing;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaking_id")
    private LanguageLevelReferential speaking;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "overall_level_id")
    private LanguageLevelReferential overallLevel;

    private byte [] certification;

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

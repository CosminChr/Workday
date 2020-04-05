package com.cosmin.licenta.workday.dto;

import com.cosmin.licenta.workday.entity.LanguageLevelReferential;
import com.cosmin.licenta.workday.entity.LanguageReferential;
import com.google.common.base.MoreObjects;

import javax.persistence.*;

public class LanguageDTO {

    private Long id;

    private LanguageReferential language;

    private LanguageLevelReferential reading;

    private LanguageLevelReferential writing;

    private LanguageLevelReferential speaking;

    private LanguageLevelReferential overallLevel;

    private byte [] certification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("language", language)
                .add("reading", reading)
                .add("writing", writing)
                .add("speaking", speaking)
                .add("overallLevel", overallLevel)
                .add("certification", certification)
                .toString();
    }
}

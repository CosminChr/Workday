package com.cosmin.licenta.workday.util;

public enum JobPositionReferentialEnum {
    PROGRAMMER_ANALYST("Programmer analyst"),
    DATA_ANALYST("Data analyst"),
    SOFTWARE_DEVELOPER("Software developer"),
    SOFTWARE_ENGINEER("Software engineer"),
    TEAM_LEAD("Team lead"),
    FULL_STACK_DEVELOPER("Full stack developer"),
    WEB_DEVELOPER("Web developer"),
    ETL_DEVELOPER("ETL developer"),
    RPA_DEVELOPER("RPA developer"),
    QA_ENGINEER("QA engineer"),
    QUALITY_CONTROLLER("Quality controller"),
    IT_BUSINESS_ANALYST("IT business analyst"),
    IT_SYSTEM_ADMINISTRATOR("IT system administrator"),
    IT_RECRUITER("IT recruiter"),
    IT_PROJECT_MANAGER("IT project manager"),
    RECRUITMENT_SPECIALIST("Recruitment specialist"),
    TRAINING_AND_RECRUITMENT_DIRECTOR("Training and recruitment director"),
    ADMINISTRATIVE_OFFICER("Administrative office"),
    KYC_ANALYST("KYC analyst"),
    KYC_ASSISTANT_ANALYST("KYC assistant analyst"),
    COMPLIANCE_ANALYST("Compliance analyst"),
    KYC_TEAM_COORDINATOR("KYC team coordinator"),
    ACCOUNTS_PAYABLE_ANALYST("Accounts payable analyst"),
    FINANCIAL_REPORTING_ANALYST("Financial reporting analyst"),
    TRAINING_ANALYST("Training analyst"),
    TECHNICAL_SUPPORT_ANALYST("Technical support analyst"),
    COORDINATOR("Coordinator"),
    HR_SPECIALIST("HR specialist"),
    FORMATION_ANALYST("Formation analyst"),
    IT_SECURITY_ANALYST("IT security analyst"),
    ACCOUNTING_ANALYST("Accounting analyst"),
    INTERNAL_AUDITOR("Internal auditor"),
    PROCESS_REFERENT_KYC("Process reference KYC"),
    DATA_LOSS_PREVENTION_AND_SPAM_PHISHING_ANALYST("Data loss prevention and spam phishing analyst"),
    PROJECT_MANAGER("Project manager"),
    SUPPORT_ANALYST("Support analyst"),
    LEARNING_AND_DEVELOPMENT_ANALYST("Learning and development analyst"),
    HR_PARTNER("HR partner"),
    FINANCIAL_CONTROLLER("Financing controller"),
    ACCOUNTING_AND_REPORTING_ANALYST("Accounting and reporting analyst"),
    TECHNICAL_ANALYST("Technical analyst"),
    COMMUNICATION_AND_PUBLIC_RELATION_MANAGER("Communication and public relation manager"),
    HR_ADVISOR("HR advisor"),
    CONFORMITY_OFFICER("Conformity officer"),
    COMPLIANCE_OFFICER_INTERNATIONAL_SANCTIONS_AND_EMBARGOES("Compliance officer international sanctions and embargoes"),
    IT_SUPPORT_ANALYST("It support analyst"),
    INNOVATION_MANAGER("Innovation manager"),
    AGILE_COACH("Agile coach"),
    DATA_PRIVACY_OFFICER("Data privacy officer"),
    ANALYST_OVERSIGHT_EMBARGO("Analyst oversight engineer"),
    TECNHICAL_SUPPORT_ENGINEER("Tecnhical support engineer");

    private String label;

    JobPositionReferentialEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}

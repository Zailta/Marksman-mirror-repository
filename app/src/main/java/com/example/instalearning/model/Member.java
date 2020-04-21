package com.example.instalearning.model;

public class Member {
    String name;
    String phoneNum;
    String profession;
    String domain;
    String cInstitute;

    public Member(String name, String phoneNum, String profession, String domain, String cInstitute) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.profession = profession;
        this.domain = domain;
        this.cInstitute = cInstitute;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getcInstitute() {
        return cInstitute;
    }

    public void setcInstitute(String cInstitute) {
        this.cInstitute = cInstitute;
    }
}

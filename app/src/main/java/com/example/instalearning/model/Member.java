package com.example.instalearning.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {
    String email;
    String name;
    String phoneNum;
    String profession;
    String domain;
    String cInstitute;
    String hour;
    String fees;
    String gender;

    public Member(String email, String name, String phoneNum, String profession, String domain, String cInstitute, String hour, String fees, String gender) {
        this.email = email;
        this.name = name;
        this.phoneNum = phoneNum;
        this.profession = profession;
        this.domain = domain;
        this.cInstitute = cInstitute;
        this.hour = hour;
        this.fees = fees;
        this.gender = gender;
    }

    public Member() {
    }

    protected Member(Parcel in) {
        email = in.readString();
        name = in.readString();
        phoneNum = in.readString();
        profession = in.readString();
        domain = in.readString();
        cInstitute = in.readString();
        hour = in.readString();
        fees = in.readString();
        gender = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(phoneNum);
        dest.writeString(profession);
        dest.writeString(domain);
        dest.writeString(cInstitute);
        dest.writeString(hour);
        dest.writeString(fees);
        dest.writeString(gender);
    }
}

package com.km.librarydata.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    private int memberId;
    private String lastname;
    private String firstname;
    private String address;
    private Integer phoneNumber;
    private Integer limit;

    @Id
    @Column(name = "memberID")
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "address_")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone_number")
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "limit_")
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberId != member.memberId) return false;
        if (lastname != null ? !lastname.equals(member.lastname) : member.lastname != null) return false;
        if (firstname != null ? !firstname.equals(member.firstname) : member.firstname != null) return false;
        if (address != null ? !address.equals(member.address) : member.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(member.phoneNumber) : member.phoneNumber != null) return false;
        if (limit != null ? !limit.equals(member.limit) : member.limit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (limit != null ? limit.hashCode() : 0);
        return result;
    }
}

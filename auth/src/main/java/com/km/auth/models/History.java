package com.km.auth.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
public class History {
    private int loanId;
    private int memberId;
    private int bookId;
    private Date loanDate;
    private Date returnDate;
    private Byte approved;

    public History() {

    }

    @Id
    @Column(name = "loanID")
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    @Basic
    @Column(name = "memberID")
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "bookID")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "loan_date")
    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Basic
    @Column(name = "return_date")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        History history = (History) o;

        if (loanId != history.loanId) return false;
        if (memberId != history.memberId) return false;
        if (bookId != history.bookId) return false;
        if (loanDate != null ? !loanDate.equals(history.loanDate) : history.loanDate != null) return false;
        if (returnDate != null ? !returnDate.equals(history.returnDate) : history.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loanId;
        result = 31 * result + memberId;
        result = 31 * result + bookId;
        result = 31 * result + (loanDate != null ? loanDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "approved")
    public Byte getApproved() {
        return approved;
    }


    public void setApproved(Byte approved) {
        this.approved = approved;
    }
}

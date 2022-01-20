package com.km.auth.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    int LoanId;
    String bookTitle;
    Date loanDate;
    Date returnDate;
    boolean isActive;

}

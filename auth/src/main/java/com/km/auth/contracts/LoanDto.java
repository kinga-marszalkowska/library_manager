package com.km.auth.contracts;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    int LoanId;
    int bookId;
    String bookTitle;
    Date loanDate;
    Date returnDate;
    boolean isActive;
    boolean approved;

}

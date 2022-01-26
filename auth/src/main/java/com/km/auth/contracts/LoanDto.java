package com.km.auth.contracts;

import lombok.*;

import java.sql.Date;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {
    int userId;
    int LoanId;
    int bookId;
    String bookTitle;
    Date loanDate;
    Date returnDate;
    boolean isActive;
    boolean approved;

}

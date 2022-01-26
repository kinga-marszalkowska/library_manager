package com.km.auth.services;

import com.km.auth.contracts.LoanDto;
import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.models.History;
import com.km.auth.repositories.LoanHistoryRepository;
import com.km.librarydata.contracts.BookDto;
import com.km.librarydata.model.Book;
import com.km.librarydata.repositiories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanHistoryService {
    private final LoanHistoryRepository repository;
    private final BookRepository bookRepository;

    public List<LoanDto> getLoans(int id){
        return repository.getLoans(id).stream()
                .map(loan ->new LoanDto(
                        loan.getLoanId(),
                        loan.getBookId(),
                        getBookTitleById(loan.getBookId()),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        isActive(loan.getReturnDate()),
                        isApprovedToBool(loan.getApproved())
                        )).collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks(){
        List<Book> allBooks = bookRepository.getBooks();

        repository.getAllHistories().stream()
                .filter(loan -> loan.getApproved() == 0)
                .forEach(loan ->allBooks.removeIf(book -> book.getBookId() == loan.getBookId()));

        return allBooks;
    }

    public List<BookDto> getAvailableBooksDto(){
        return getAvailableBooks().stream()
                .map(book ->new BookDto(
                        book.getIsbn(),
                        book.getTitle(),
                        book.getBookDescription(),
                        book.getAuthor(),
                        book.getPublishYear(),
                        book.getPublisher())).collect(Collectors.toList());
    }

    public boolean canLendBook(Book book){
        return getAvailableBooks().contains(book);
    }



    public String getBookTitleById(int id){
        return repository.getBookById(id).getTitle();
    }

    public boolean returnDatePassed(Date returnDate){
        if (returnDate == null) return false;
        return ChronoUnit.DAYS.between(LocalDate.parse(returnDate.toString()), LocalDate.parse(LocalDate.now().toString())) >= 0;
    }

    public boolean isActive(Date returnDate){
        boolean returnDatePassed = returnDatePassed(returnDate);
        return !returnDatePassed;
    }

    public boolean isApprovedToBool(Byte approved){
      return approved == 1;
//        return returnDate != null && (ChronoUnit.DAYS.between(LocalDate.parse(returnDate.toString()), LocalDate.parse(LocalDate.now().toString())) <= 5);
    }

    public Byte isApprovedToByte(boolean isApproved){
        if (isApproved) return (byte) 1;
        return (byte) 0;
    }

    public void returnBook(String loanId){
        History history = repository.getLoanByLoanId(Integer.parseInt(loanId));
        history.setReturnDate(Date.valueOf(LocalDate.now()));
        repository.updateLoanInDB(history);
    }


    public List<LoanDto> getCurrentUserLoans(){
        return getLoans(getUserId());
    }

    public int getUserId(){
        return repository.getUserId(getCurrentUser().getUsername());
    }

    public UserDetailsEx getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserDetailsEx) authentication.getPrincipal();
        }
        return null;
    }

    public void loanToDB(String returnDate, Integer bookId){
        repository.insertLoanToDB(new History(
                0,
                getUserId(),
                bookId,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(returnDate),
                (byte) 0
        ));
    }


}

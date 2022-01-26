package com.km.auth.controllers;

import com.km.auth.services.LoanHistoryService;
import com.km.librarydata.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Repository
@RequiredArgsConstructor

public class LoanHistoryController {
    private final LoanHistoryService loanHistoryService;
    private final BookService bookService;
    private final int DEFAULT_LOAN_DURATION_MONTHS = 2;

    @GetMapping("/")
    public String loans(Model model, @RequestParam(value= "keyword", required = false) String keyword) {
        model.addAttribute("loans", loanHistoryService.getCurrentUserLoans());
        if (keyword == null || keyword.equals("")) model.addAttribute("books", loanHistoryService.getAvailableBooks());
        else {
            model.addAttribute("keyword", keyword);
            model.addAttribute("books", bookService.getBooksBySearchFromAvailable(loanHistoryService.getAvailableBooksDto(), keyword));
        }
        return "loans_history";
    }

    @GetMapping("/lend")
    public String lend(Model model, @RequestParam(value= "lend", required = false) String isbn){
        if (isbn == null || isbn.equals("") || cannotLendBook(isbn)) {
            model.addAttribute("loans", loanHistoryService.getCurrentUserLoans());
            model.addAttribute("books", loanHistoryService.getAvailableBooks());
            return "loans_history";
        }

        model.addAttribute("lend", bookService.getBookByISBN(isbn));
        model.addAttribute("min_return_date", LocalDate.now());
        model.addAttribute("max_return_date", LocalDate.now().plusMonths(DEFAULT_LOAN_DURATION_MONTHS));

        return "lendBooks";
    }

    public boolean cannotLendBook(String isbn){
        return !loanHistoryService.canLendBook(bookService.getBookByISBN(isbn));
    }

    @GetMapping("/return")
    public String returnBook(Model model, @RequestParam(value="return") String loanId){
        loanHistoryService.returnBook(loanId);
        model.addAttribute("loans", loanHistoryService.getCurrentUserLoans());
        model.addAttribute("books", loanHistoryService.getAvailableBooks());
        return "loans_history";
    }

    @GetMapping("/confirm")
    public String confirm(Model model,
                          @RequestParam(value= "returnDate", required = false) String returnDate,
                          @RequestParam(value= "isbn", required = false) String isbn){
        if (returnDate != null) {
            model.addAttribute("returnDate", returnDate);
            model.addAttribute("isbn", bookService.getBookByISBN(isbn));
            loanHistoryService.loanToDB(returnDate, bookService.getBookByISBN(isbn).getBookId());
        }
        return "confirmation";

    }

}

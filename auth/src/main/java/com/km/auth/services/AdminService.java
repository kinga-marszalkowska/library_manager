package com.km.auth.services;

import com.km.auth.contracts.LoanDto;
import com.km.auth.models.History;
import com.km.auth.repositories.LoanHistoryRepository;
import com.km.auth.utils.comparators.HistoryComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final LoanHistoryRepository repository;
    private final LoanHistoryService loanHistoryService;

    public void approveReturn(String loanId){
        History history = repository.getLoanByLoanId(Integer.parseInt(loanId));
        history.setApproved((byte) 1);
        repository.updateLoanInDB(history);
    }

    public List<History> getAllHistories(){
        return repository.getAllHistories().stream().sorted(new HistoryComparator()).collect(Collectors.toList());
    }

    public List<LoanDto> getAllLoans(){
        return getAllHistories().stream()
                .map(loan ->new LoanDto(
                        loan.getMemberId(),
                        loan.getLoanId(),
                        loan.getBookId(),
                        loanHistoryService.getBookTitleById(loan.getBookId()),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        loanHistoryService.isActive(loan.getReturnDate()),
                        loanHistoryService.isApprovedToBool(loan.getApproved())
                )).collect(Collectors.toList());
    }
}

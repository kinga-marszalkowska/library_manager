package com.km.auth.repositories;

import com.km.auth.contracts.HistoryDto;
import com.km.auth.contracts.LoanDto;
import com.km.auth.models.History;
import com.km.librarydata.contracts.BookDto;
import com.km.librarydata.model.Book;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Arrays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class LoanHistoryRepository {
    private final EntityManager entityManager;

    public List<History> getLoans(int id){
        return entityManager.createQuery("SELECT h FROM History h WHERE h.memberId=" + id, History.class)
                .getResultList();

    }

    public History getLoanByLoanId(int id){
        return entityManager.createQuery("SELECT h FROM History h WHERE h.loanId=" + id, History.class)
                .getSingleResult();
    }

    public Book getBookById(int id){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.bookId='" + id + "'", Book.class).getSingleResult();
    }

    public int getUserId(String username){
        return entityManager.createQuery("SELECT u.id FROM User u WHERE u.username='" + username + "'", Integer.class).getSingleResult();
    }

    public List<History> getAllHistories(){
        return entityManager.createQuery("SELECT h FROM History h", History.class).getResultList();
    }

    @Transactional
    public void updateLoanInDB(History loan){
        this.entityManager.merge(loan);
        this.entityManager.flush();
    }

    @Transactional
    public void insertLoanToDB(History loan) {
        this.entityManager.persist(loan);
        this.entityManager.flush();
    }

}

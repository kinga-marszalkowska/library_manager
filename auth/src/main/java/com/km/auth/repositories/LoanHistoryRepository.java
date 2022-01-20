package com.km.auth.repositories;

import com.km.auth.models.History;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Arrays;
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

    public int getUserId(String username){
        return entityManager.createQuery("SELECT u.id FROM User u WHERE u.username='" + username + "'", Integer.class).getSingleResult();
    }

}

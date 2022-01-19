package com.km.librarydata.repositiories;

import com.km.librarydata.contracts.BookDto;
import com.km.librarydata.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepository {
    private final EntityManager entityManager;

    public List<Book> getBooks(){
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.bookId=1", Book.class)
                .getResultList();

    }

    public List<Book> getBooksFromDB(Map<SingularAttribute<Book, ?>, Object> params){
        CriteriaBuilder qb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = qb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root);

        params.forEach((field, value) -> query.where(qb.equal(root.get(field), value)));

        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public void insertBookToDB(BookDto bookDto) {
        this.entityManager.persist(bookDto);
    }
}

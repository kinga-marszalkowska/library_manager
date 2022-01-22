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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepository {
    private final EntityManager entityManager;

    public List<Book> getBooks(){
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();

    }

    public Book getBookByISBN(String isbn){
        return entityManager.createQuery(String.format("SELECT b FROM Book b WHERE b.isbn= '%s'", isbn), Book.class)
                .getSingleResult();

    }

    public List<Book> getTitleLike(String pattern){
        return entityManager.createNativeQuery(String.format("SELECT * FROM Book WHERE title LIKE '%s'", pattern), Book.class).getResultList();
    }

    public List<Book> exactMatch(String pattern){
        return entityManager.createNativeQuery(String.format("SELECT * FROM Book WHERE MATCH(title, author, book_description, publisher) AGAINST('%s')", pattern), Book.class).getResultList();
    }

    public List<Book> searchBar(String pattern){
        List<Book> likeMatch = getTitleLike(pattern);
        List<Book> textMatch =  exactMatch(pattern);
        List<Book> results = new ArrayList<>(textMatch);
        results.removeAll(likeMatch);
        likeMatch.addAll(results);
        return likeMatch;
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

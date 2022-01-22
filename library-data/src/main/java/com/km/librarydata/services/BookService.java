package com.km.librarydata.services;

import com.km.librarydata.contracts.BookDto;
import com.km.librarydata.model.Book;
import com.km.librarydata.repositiories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<BookDto> getBooks(){
        return bookRepository.getBooks()
                .stream()
                .map(book ->new BookDto(
                        book.getIsbn(),
                        book.getTitle(),
                        book.getBookDescription(),
                        book.getAuthor(),
                        book.getPublishYear(),
                        book.getPublisher())).collect(Collectors.toList());
    }

    public Book getBookByISBN(String isbn){
        return bookRepository.getBookByISBN(isbn);
    }

    public List<BookDto> getBooksBySearch(String search){
        return bookRepository.searchBar(search)
                .stream()
                .map(book ->new BookDto(
                        book.getIsbn(),
                        book.getTitle(),
                        book.getBookDescription(),
                        book.getAuthor(),
                        book.getPublishYear(),
                        book.getPublisher())).collect(Collectors.toList());
    }

    public List<Book> getBooksFromDB(Map<SingularAttribute<Book, ?>, Object> params){
        return bookRepository.getBooksFromDB(params);
    }

    public void putBookToRepository(BookDto bookDto) {
        bookRepository.insertBookToDB(bookDto);
    }
}

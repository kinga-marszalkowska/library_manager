package com.km.librarydata.wrappers;


import com.km.librarydata.contracts.BookDto;
import com.km.librarydata.model.Book;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BookCreationDto {
    private List<BookDto> books = new ArrayList<>();
    public void addBook(BookDto bookDto) {
        this.books.add(bookDto);
    }
}
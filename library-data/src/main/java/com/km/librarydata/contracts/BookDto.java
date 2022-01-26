package com.km.librarydata.contracts;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDto {
    private String isbn;
    private String title;
    private String bookDescription;
    private String author;
    private Integer publishYear;
    private String publisher;
}

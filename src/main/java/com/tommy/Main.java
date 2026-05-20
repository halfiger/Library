package com.tommy;

import com.tommy.library.Book;
import com.tommy.library.BookService;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();

//        2
//        Book book = new Book("dada", "baba", 11);
//        bookService.saveBook(book);

//        3
        Book book1 = bookService.getBookById(1L);
        Book book2 = bookService.getBookById(2L);

        if (book1 != null && book2 != null) {
            System.out.println(book1);
            System.out.println(book2);
        }
//        4


    }
}
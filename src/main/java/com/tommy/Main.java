package com.tommy;

import com.tommy.library.Book;
import com.tommy.library.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();

//        2
//        Book book = new Book("dada", "baba", 11);
//        bookService.saveBook(book);

//        3
//        Book book1 = bookService.getBookById(1L);
//        Book book2 = bookService.getBookById(2L);

//        if (book1 != null && book2 != null) {
//            System.out.println(book1);
//            System.out.println(book2);
//        }
//        4
//        List<Book> list = bookService.getAll();
//        System.out.println(list.toString());
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }

//        5
//        List <Book> list = bookService.getMuchPagesThen(6);
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }

//        6

//        List <Book> list = bookService.pagination(1,1);
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }
//        7
//        bookService.updateUserName(2L, "The Odyssey by Homer");
//        bookService.updateUserName(1L, "The Magic Mountain by Thomas Mann");
//        8
//        bookService.updateBookName(2L,"The Odyssey ");
//        bookService.updateBookAutor(2L, "Homer ");
//        bookService.updateBookName(1L,"The Magic Mountain ");
//        bookService.updateBookAutor(1L, "Thomas Mann ");
//        9
//        bookService.deleteById(18L);
//        bookService.deleteById(19L);
//        10
//        System.out.println(bookService.findByBookName("Ulysses").toString());
//
    }
}
package com.tommy.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookService {

    private final SessionFactory sessionFactory = HibernateUtil.getFactory();

    public void saveBook (Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    public Book getBookById (Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.getTransaction().commit();
        return book;
    }


}

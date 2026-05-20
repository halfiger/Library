package com.tommy.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BookService {

    SessionFactory sessionFactory = HibernateUtil.getFactory();

    public Book saveBook () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = new Book("mumu", "bubu", 2);
        session.save(book);
        session.getTransaction().commit();
        return book;

    }

}

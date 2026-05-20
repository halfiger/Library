package com.tommy.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public List<Book> getAll (){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List <Book> list = session.createQuery("From Book", Book.class).getResultList();
        session.getTransaction().commit();
        return list;
    }
}

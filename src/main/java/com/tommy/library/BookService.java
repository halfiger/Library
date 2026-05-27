package com.tommy.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BookService {

    private final SessionFactory sessionFactory = HibernateUtil.getFactory();

    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    public Book getBookById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.getTransaction().commit();
        return book;
    }

    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session.createQuery("From Book", Book.class).getResultList();
        session.getTransaction().commit();
        return list;
    }

    public List<Book> getMuchPagesThen(int n) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session.createQuery("From Book b where b.pages > :n", Book.class).setParameter("n", n).getResultList();
        session.getTransaction().commit();
        return list;
    }

    public List<Book> pagination(int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session.createQuery("from Book b order by b.id", Book.class).setFirstResult(page * size).setMaxResults(size).getResultList();
        session.getTransaction().commit();
        return list;
    }

    public void updateBookName(Long id, String newName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            book.setName(newName);
        }
        session.getTransaction().commit();
    }

    public void updateBookAutor (Long id, String newAutor) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            book.setAutor(newAutor);
        }
        session.getTransaction().commit();
    }

    public void deleteById (Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            session.remove(book);
        }
        session.getTransaction().commit();
    }

    public Book findByBookName (String bookName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.createQuery(
                "From Book b where b.name = :bookName", Book.class
        ).setParameter("bookName", bookName).getSingleResult();
        session.getTransaction().commit();
        return book;
    }

    public List <Book> findByPartOfBookName (String part) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List <Book> list = session
                .createQuery("From Book b where b.name like :part", Book.class)
                .setParameter("part", "%"+part+"%").getResultList();
        session.getTransaction().commit();
        return list;
    }

    public List <Book> allSortedByPages () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List <Book> list = session.createQuery("from Book b order by b.pages desc", Book.class).getResultList();
    session.getTransaction().commit();
    return list;
    }




}
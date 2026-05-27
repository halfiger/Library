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

    public void updateBookAutor(Long id, String newAutor) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            book.setAuthor(newAutor);
        }
        session.getTransaction().commit();
    }

    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        if (book != null) {
            session.remove(book);
        }
        session.getTransaction().commit();
    }

    public Book findByBookName(String bookName) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = session.createQuery(
                "From Book b where b.name = :bookName", Book.class
        ).setParameter("bookName", bookName).getSingleResult();
        session.getTransaction().commit();
        return book;
    }

    public List<Book> findByPartOfBookName(String part) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session
                .createQuery("From Book b where b.name like :part", Book.class)
                .setParameter("part", "%" + part + "%").getResultList();
        session.getTransaction().commit();
        return list;
    }

    public List<Book> allSortedByPages() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session.createQuery("from Book b order by b.pages desc", Book.class).getResultList();
        session.getTransaction().commit();
        return list;
    }

    public List<Book> getAllMorePages(int pa, int ges) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> list = session.createQuery("from Book b where b.pages > :pa and b.pages < :ges", Book.class)
                .setParameter("pa", pa)
                .setParameter("ges", ges)
                .getResultList();
        session.getTransaction().commit();
        return list;
    }

    public Long countBooks() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Long count = session.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
        session.getTransaction().commit();
        return count;
    }

    public Double getAverageValueOfPages() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Double avgValue = session.createQuery("SELECT AVG(b.pages) FROM Book b", Double.class).getSingleResult();
        session.getTransaction().commit();
        return avgValue;
    }

    public Integer getMaxPagesValue () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Integer maxValue = session.createQuery("SELECT MAX(b.pages) FROM Book b", Integer.class).getSingleResult();
        session.getTransaction().commit();
        return maxValue;
    }

    public Integer getMinPagesValue () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Integer minValue = session.createQuery("SELECT MIN(b.pages) FROM Book b", Integer.class).getSingleResult();
        session.getTransaction().commit();
        return minValue;
    }

    public List <BookInfoDTO> findAllUserInfo () {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List <BookInfoDTO> list = session
                .createQuery("SELECT new com.tommy.library.BookInfoDTO(b.name, b.autor) FROM Book b", BookInfoDTO.class)
                .getResultList();
        session.getTransaction().commit();
        return list;
    }

}
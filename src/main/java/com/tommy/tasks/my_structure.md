----------------------Структура----------------------

src
└── main
└── java
└── com.tommy.library
├── Main.java
├── Book.java
├── BookInfoDTO.java
├── BookService.java
└── HibernateUtil.java


----------------------Main.java----------------------

package com.tommy;

import com.tommy.library.Book;
import com.tommy.library.BookInfoDTO;
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
//        11
//          List <Book> list = bookService.findByPartOfBookName("re");
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }

//        List <Book> list = bookService.allSortedByPages();
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }

//        List <Book> list = bookService.getAllMorePages(333, 555);
//        for (Book b : list) {
//            if (b != null) {
//                System.out.println(b);
//            }
//        }

//        System.out.println(bookService.countBooks());
//        System.out.println(bookService.getAverageValueOfPages());
//        System.out.println(bookService.getMaxPagesValue());
//        System.out.println(bookService.getMinPagesValue());

        List <BookInfoDTO> list = bookService.findAllUserInfo();
        for (BookInfoDTO b : list) {
            if (b!=null) {
                System.out.println(b);
            }
        }
    }
}

------------------BookInfoDTO.java-------------------

package com.tommy.library;

public class BookInfoDTO {
private String name;
private String autor;

    public BookInfoDTO() {}

    public BookInfoDTO(String name, String autor) {
        this.name = name;
        this.autor = autor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "BookInfoDTO{" +
                "name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}

----------------------Book.java----------------------

package com.tommy.library;

import jakarta.persistence.*;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String autor;

    private int pages;

    public Book() {
    }

    public Book(String name, String autor, int pages) {
        this.name = name;
        this.autor = autor;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return autor;
    }

    public void setAuthor(String author) {
        this.autor = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", autor='" + autor + '\'' +
                ", pages=" + pages +
                '}';
    }
}

----------------------BookService.java----------------------

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
----------------------HibernateUtil.java----------------------

package com.tommy.library;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
    }

    public static SessionFactory getFactory () {
        return factory;
    }
}


----------------------hibernate.cfg.xml----------------------

<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="connection.url">jdbc:mysql://localhost:3306/my_db?useSSL=false&amp;serverTimezone=UTC</property>
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="connection.username">bestuser</property>
        <property name="connection.password">bestuser</property>

        <property name="current_session_context_class">thread</property>
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="show_sql">true</property>

    </session-factory>
</hibernate-configuration>

----------------------console result----------------------
C:\Java\jdk-25.0.2\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3.7\lib\idea_rt.jar=63996" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\Library\library\target\classes;C:\Users\jazzm\.m2\repository\org\hibernate\orm\hibernate-core\6.4.4.Final\hibernate-core-6.4.4.Final.jar;C:\Users\jazzm\.m2\repository\jakarta\transaction\jakarta.transaction-api\2.0.1\jakarta.transaction-api-2.0.1.jar;C:\Users\jazzm\.m2\repository\org\jboss\logging\jboss-logging\3.6.3.Final\jboss-logging-3.6.3.Final.jar;C:\Users\jazzm\.m2\repository\org\hibernate\common\hibernate-commons-annotations\6.0.6.Final\hibernate-commons-annotations-6.0.6.Final.jar;C:\Users\jazzm\.m2\repository\io\smallrye\jandex\3.1.2\jandex-3.1.2.jar;C:\Users\jazzm\.m2\repository\com\fasterxml\classmate\1.7.3\classmate-1.7.3.jar;C:\Users\jazzm\.m2\repository\net\bytebuddy\byte-buddy\1.17.8\byte-buddy-1.17.8.jar;C:\Users\jazzm\.m2\repository\jakarta\xml\bind\jakarta.xml.bind-api\4.0.4\jakarta.xml.bind-api-4.0.4.jar;C:\Users\jazzm\.m2\repository\jakarta\activation\jakarta.activation-api\2.1.4\jakarta.activation-api-2.1.4.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\jaxb-runtime\4.0.6\jaxb-runtime-4.0.6.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\jaxb-core\4.0.6\jaxb-core-4.0.6.jar;C:\Users\jazzm\.m2\repository\org\eclipse\angus\angus-activation\2.0.3\angus-activation-2.0.3.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\txw2\4.0.6\txw2-4.0.6.jar;C:\Users\jazzm\.m2\repository\com\sun\istack\istack-commons-runtime\4.1.2\istack-commons-runtime-4.1.2.jar;C:\Users\jazzm\.m2\repository\jakarta\inject\jakarta.inject-api\2.0.1\jakarta.inject-api-2.0.1.jar;C:\Users\jazzm\.m2\repository\org\antlr\antlr4-runtime\4.13.0\antlr4-runtime-4.13.0.jar;C:\Users\jazzm\.m2\repository\jakarta\persistence\jakarta.persistence-api\3.1.0\jakarta.persistence-api-3.1.0.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-context\6.1.6\spring-context-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-aop\7.0.7\spring-aop-7.0.7.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-beans\7.0.7\spring-beans-7.0.7.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-core\7.0.7\spring-core-7.0.7.jar;C:\Users\jazzm\.m2\repository\commons-logging\commons-logging\1.3.6\commons-logging-1.3.6.jar;C:\Users\jazzm\.m2\repository\org\jspecify\jspecify\1.0.0\jspecify-1.0.0.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-expression\7.0.7\spring-expression-7.0.7.jar;C:\Users\jazzm\.m2\repository\io\micrometer\micrometer-observation\1.16.5\micrometer-observation-1.16.5.jar;C:\Users\jazzm\.m2\repository\io\micrometer\micrometer-commons\1.16.5\micrometer-commons-1.16.5.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-orm\6.1.6\spring-orm-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-jdbc\7.0.7\spring-jdbc-7.0.7.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-tx\6.1.6\spring-tx-6.1.6.jar;C:\Users\jazzm\.m2\repository\com\mysql\mysql-connector-j\8.3.0\mysql-connector-j-8.3.0.jar;C:\Users\jazzm\.m2\repository\org\aspectj\aspectjrt\1.9.25.1\aspectjrt-1.9.25.1.jar;C:\Users\jazzm\.m2\repository\org\aspectj\aspectjweaver\1.9.25.1\aspectjweaver-1.9.25.1.jar;C:\Users\jazzm\.m2\repository\org\slf4j\slf4j-simple\2.0.13\slf4j-simple-2.0.13.jar;C:\Users\jazzm\.m2\repository\org\slf4j\slf4j-api\2.0.17\slf4j-api-2.0.17.jar com.tommy.Main
May 27, 2026 12:52:03 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.4.4.Final
May 27, 2026 12:52:03 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC]
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {user=bestuser, password=****}
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 27, 2026 12:52:03 PM org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl constructDialect
WARN: HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
Hibernate: select b1_0.name,b1_0.autor from books b1_0
BookInfoDTO{name='The Magic Mountain ', autor='Thomas Mann '}
BookInfoDTO{name='The Odyssey ', autor='Homer '}
BookInfoDTO{name='Ulysses', autor='James Joyce'}
BookInfoDTO{name='The Great Gatsby', autor='F. Scott Fitzgerald'}
BookInfoDTO{name='The Catcher in the Rye', autor='J. D. Salinger'}
BookInfoDTO{name='One Hundred Years of Solitude', autor='Gabriel García Márquez'}
BookInfoDTO{name='Nineteen Eighty Four', autor='George Orwell'}
BookInfoDTO{name='Moby-Dick', autor='Herman Melville'}
BookInfoDTO{name='Don Quixote', autor='Miguel de Cervantes'}
BookInfoDTO{name='The Sound and the Fury', autor='William Faulkner'}
BookInfoDTO{name='Pride and Prejudice', autor='Jane Austen'}
BookInfoDTO{name='To Kill a Mockingbird', autor='Harper Lee'}
BookInfoDTO{name='The Lord Of The Rings', autor='J. R. R. Tolkien'}
BookInfoDTO{name='The Trial', autor='Franz Kafka'}
BookInfoDTO{name='Madame Bovary', autor='Gustave Flaubert'}
BookInfoDTO{name='Adventures of Huckleberry Finn', autor='Mark Twain'}
BookInfoDTO{name='The Stranger', autor='Albert Camus'}

Process finished with exit code 0
----------------------Структура----------------------

src
└── main
└── java
└── org.entity.practice1
├── Main6.java
├── User.java
├── UserService.java
└── HibernateUtil.java


----------------------Main6.java----------------------

package org.entity.practice1;

public class Main6 {
public static void main(String[] args) {
UserService userService = new UserService();
userService.deleteById(6L);
userService.findAll();
}
}
----------------------User.java----------------------

package org.entity.practice1;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private int id;

    private String name;

    private String email;

    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User () {}

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}


----------------------UserService.java----------------------

package org.entity.practice1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    private SessionFactory factory = HibernateUtil.getFactory();

    public void saveUser(User user) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }

    public User findById(Long id) {

        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.getTransaction().commit();
        return user;
    }

    public List<User> findAll() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        System.out.println("find all done marker");
        return list;
    }

    public List<User> findUsersOlderThan(int age) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<User> list = session.createQuery("from User u where u.age > :age").setParameter("age", age).getResultList();
        session.getTransaction().commit();
        return list;
    }

    public User updateUserEmail(String newEmail, Long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            user.setEmail(newEmail);
        }
        session.getTransaction().commit();
        return user;
    }

    public User updateUserName(String newName, Long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            user.setName(newName);
        }
        session.getTransaction().commit();
        return user;
    }


    public List<User> pagination(int page, int size) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<User> list = session
                .createQuery(
                        "FROM User u ORDER BY u.id",
                        User.class
                )
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
        session.getTransaction().commit();
        return list;
    }

    public void deleteById (Long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.remove(user);
        session.getTransaction().commit();
    }
}

----------------------HibernateUtil.java----------------------

package org.entity.practice1;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static final SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
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
C:\Java\jdk-25.0.2\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2024.3.7\lib\idea_rt.jar=53749" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath D:\sprin9\sprin9\target\classes;C:\Users\jazzm\.m2\repository\org\hibernate\orm\hibernate-core\6.4.4.Final\hibernate-core-6.4.4.Final.jar;C:\Users\jazzm\.m2\repository\jakarta\transaction\jakarta.transaction-api\2.0.1\jakarta.transaction-api-2.0.1.jar;C:\Users\jazzm\.m2\repository\org\jboss\logging\jboss-logging\3.5.0.Final\jboss-logging-3.5.0.Final.jar;C:\Users\jazzm\.m2\repository\org\hibernate\common\hibernate-commons-annotations\6.0.6.Final\hibernate-commons-annotations-6.0.6.Final.jar;C:\Users\jazzm\.m2\repository\io\smallrye\jandex\3.1.2\jandex-3.1.2.jar;C:\Users\jazzm\.m2\repository\com\fasterxml\classmate\1.5.1\classmate-1.5.1.jar;C:\Users\jazzm\.m2\repository\net\bytebuddy\byte-buddy\1.14.11\byte-buddy-1.14.11.jar;C:\Users\jazzm\.m2\repository\jakarta\xml\bind\jakarta.xml.bind-api\4.0.0\jakarta.xml.bind-api-4.0.0.jar;C:\Users\jazzm\.m2\repository\jakarta\activation\jakarta.activation-api\2.1.0\jakarta.activation-api-2.1.0.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\jaxb-runtime\4.0.2\jaxb-runtime-4.0.2.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\jaxb-core\4.0.2\jaxb-core-4.0.2.jar;C:\Users\jazzm\.m2\repository\org\eclipse\angus\angus-activation\2.0.0\angus-activation-2.0.0.jar;C:\Users\jazzm\.m2\repository\org\glassfish\jaxb\txw2\4.0.2\txw2-4.0.2.jar;C:\Users\jazzm\.m2\repository\com\sun\istack\istack-commons-runtime\4.1.1\istack-commons-runtime-4.1.1.jar;C:\Users\jazzm\.m2\repository\jakarta\inject\jakarta.inject-api\2.0.1\jakarta.inject-api-2.0.1.jar;C:\Users\jazzm\.m2\repository\org\antlr\antlr4-runtime\4.13.0\antlr4-runtime-4.13.0.jar;C:\Users\jazzm\.m2\repository\jakarta\persistence\jakarta.persistence-api\3.1.0\jakarta.persistence-api-3.1.0.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-context\6.1.6\spring-context-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-aop\6.1.6\spring-aop-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-beans\6.1.6\spring-beans-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-core\6.1.6\spring-core-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-jcl\6.1.6\spring-jcl-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-expression\6.1.6\spring-expression-6.1.6.jar;C:\Users\jazzm\.m2\repository\io\micrometer\micrometer-observation\1.12.5\micrometer-observation-1.12.5.jar;C:\Users\jazzm\.m2\repository\io\micrometer\micrometer-commons\1.12.5\micrometer-commons-1.12.5.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-orm\6.1.6\spring-orm-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-jdbc\6.1.6\spring-jdbc-6.1.6.jar;C:\Users\jazzm\.m2\repository\org\springframework\spring-tx\6.1.6\spring-tx-6.1.6.jar;C:\Users\jazzm\.m2\repository\com\mysql\mysql-connector-j\8.3.0\mysql-connector-j-8.3.0.jar;C:\Users\jazzm\.m2\repository\com\google\protobuf\protobuf-java\3.25.1\protobuf-java-3.25.1.jar;C:\Users\jazzm\.m2\repository\org\aspectj\aspectjrt\1.9.25.1\aspectjrt-1.9.25.1.jar;C:\Users\jazzm\.m2\repository\org\aspectj\aspectjweaver\1.9.25.1\aspectjweaver-1.9.25.1.jar;C:\Users\jazzm\.m2\repository\org\slf4j\slf4j-simple\2.0.13\slf4j-simple-2.0.13.jar;C:\Users\jazzm\.m2\repository\org\slf4j\slf4j-api\2.0.13\slf4j-api-2.0.13.jar org.entity.practice1.Main6
May 19, 2026 6:06:27 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate ORM core version 6.4.4.Final
May 19, 2026 6:06:28 PM org.hibernate.cache.internal.RegionFactoryInitiator initiateService
INFO: HHH000026: Second-level cache disabled
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using built-in connection pool (not intended for production use)
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: Loaded JDBC driver class: com.mysql.cj.jdbc.Driver
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001012: Connecting with JDBC URL [jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC]
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {user=bestuser, password=****}
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH10001115: Connection pool size: 20 (min=1)
May 19, 2026 6:06:28 PM org.hibernate.engine.jdbc.dialect.internal.DialectFactoryImpl constructDialect
WARN: HHH90000025: MySQLDialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
Hibernate: select u1_0.id,u1_0.age,u1_0.email,u1_0.name from users u1_0 where u1_0.id=?
Hibernate: delete from users where id=?
Hibernate: select u1_0.id,u1_0.age,u1_0.email,u1_0.name from users u1_0
find all done marker

Process finished with exit code 0

package com.tommy.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        User user = new User();
        user.setName("Vano");
        user.setAge(77);

        session.persist(user);

        session.getTransaction().commit();

        session.close();
        factory.close();


    }
}
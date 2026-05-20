package com.tommy.library;

import org.hibernate.SessionFactory;

public class BookService {

    SessionFactory sessionFactory = HibernateUtil.getFactory();
    
}

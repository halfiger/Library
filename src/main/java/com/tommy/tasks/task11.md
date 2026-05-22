find by name contains


-------------------------


    public User findByEmail (String email) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = (User) session.createQuery("From User u where u.email := email").setParameter("email", email).getSingleResult();
        return user;
    }

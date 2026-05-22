update book autor


----------------


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

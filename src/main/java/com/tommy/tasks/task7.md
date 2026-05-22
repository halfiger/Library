update user name



-----------------------


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


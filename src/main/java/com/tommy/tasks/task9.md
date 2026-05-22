delete by id


----------------


    public Boolean deleteById(Long id) {
        Boolean deleted = false;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);

        if (user != null) {
            session.remove(user);
            deleted = true;
        }
        return deleted;
    }
🔥 1. SORTING
📌 Завдання
Метод:
findAllSortedByAge()
Умова

Повернути всіх користувачів:

від молодших до старших.
🧠 Що треба використати
ORDER BY
⚠️ Спробуй сам
✅ Приблизне рішення
public List<User> findAllSortedByAge() {

    Session session = factory.getCurrentSession();

    session.beginTransaction();

    List<User> users = session
            .createQuery(
                    "FROM User u ORDER BY u.age",
                    User.class
            )
            .getResultList();

    session.getTransaction().commit();

    return users;
}
🔥 Додатково
Спробуй сам:
DESC
сортування по name
ORDER BY age DESC, name ASC
⚠️ Це буде дуже часто в реальних API

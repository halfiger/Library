-------------------------------------------
🔥 2. DYNAMIC FILTERS
📌 Завдання
Метод:
findUsersOlderThan(int age)
Умова

Повернути:

усіх користувачів старших за age
🧠 Тут уже:
WHERE
parameter
comparison
✅ Рішення
public List<User> findUsersOlderThan(int age) {

Session session = factory.getCurrentSession();

session.beginTransaction();

List<User> users = session
.createQuery(
"FROM User u WHERE u.age > :age",
User.class
)
.setParameter("age", age)
.getResultList();

session.getTransaction().commit();

return users;
}
🔥 Далі сам спробуй
findUsersBetweenAge(min, max)
Підказка
BETWEEN
Або:
u.age >= :min AND u.age <= :max
BETWEEN :min AND :max
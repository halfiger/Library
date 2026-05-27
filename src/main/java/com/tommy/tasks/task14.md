------------------------
🔥 3. AGGREGATE FUNCTIONS

Оце вже дуже важлива SQL/JPQL тема.

📌 Завдання
Метод:
countUsers()
🧠 Треба:
COUNT
⚠️ І тут новий момент

Повертається:
Long

✅ Рішення
public Long countUsers() {

Session session = factory.getCurrentSession();

session.beginTransaction();

Long count = session
.createQuery(
"SELECT COUNT(u) FROM User u",
Long.class
)
.getSingleResult();

session.getTransaction().commit();

return count;
}

🔥 Наступні самостійно
averageAge()
Підказка
AVG
⚠️ AVG повертає:
Double
🔥 Ще:
maxAge()
MAX
minAge()
MIN
🧠 Це вже прям SQL-core речі
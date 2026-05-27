theory 0

entity
SessionFactory
CRUD
JPQL
pagination
LIKE
parameters
dirty checking
transaction
lifecycle

Entity

Entity — це Java-клас, який Hibernate прив’язує до таблиці в БД.

Тобто:

клас = таблиця
об’єкт = рядок таблиці

Наприклад, є таблиця users.

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String name;

    private int age;
}

Тепер Hibernate розуміє:

User → таблиця users
поле name → колонка name

Створення об’єкта:

User user = new User();
user.setName("Ivan");

після save() Hibernate зробить SQL приблизно таке:

INSERT INTO users (name) VALUES ('Ivan');
SessionFactory

SessionFactory — це “фабрика” для створення Session.

Вона:

читає конфігурацію Hibernate
підключається до БД
зберігає налаштування
створює сесії

Зазвичай одна на весь застосунок.

SessionFactory factory =
new Configuration()
.configure()
.buildSessionFactory();
Session

(Ти не питав окремо, але без цього важко зрозуміти інше.)

Session — це “робоче підключення” до БД.

Через неї:

зберігають
читають
оновлюють
видаляють дані
Session session = factory.openSession();
CRUD

CRUD — 4 базові операції з даними.

Буква	Значення	Hibernate
C	Create	save/persist
R	Read	get/query
U	Update	update/change
D	Delete	delete
CREATE
User user = new User();
user.setName("Oleg");

session.persist(user);
READ
User user = session.get(User.class, 1L);
UPDATE
User user = session.get(User.class, 1L);
user.setName("Max");

Hibernate сам зробить UPDATE при commit.

DELETE
session.remove(user);
JPQL

JPQL = Java Persistence Query Language.

Це мова запитів НЕ до таблиць, а до entity-класів.

SQL:

SELECT * FROM users

JPQL:

FROM User

Тут:

User → клас
не users таблиця

Приклад:

List<User> users =
session.createQuery(
"FROM User WHERE age > 18",
User.class
).list();

Hibernate сам перетворить це у SQL.

Pagination

Pagination = посторінкове завантаження даних.

Наприклад:

не 100000 користувачів одразу
а по 10
List<User> users = session.createQuery(
"FROM User",
User.class
)
.setFirstResult(0) // з якого елемента
.setMaxResults(10) // скільки взяти
.list();

Сторінка 2:

.setFirstResult(10)
.setMaxResults(10)
LIKE

LIKE — пошук по частині тексту.

SQL:

WHERE name LIKE '%an%'

Hibernate JPQL:

FROM User WHERE name LIKE :pattern

Приклад:

List<User> users = session.createQuery(
"FROM User WHERE name LIKE :pattern",
User.class
)
.setParameter("pattern", "%an%")
.list();

Знайде:

Andriy
Ivan
Diana
Parameters

Parameters — це параметри запиту.

Вони потрібні:

для безпеки
щоб не клеїти строки руками
щоб уникати SQL injection

Погано:

String hql = "FROM User WHERE name = '" + name + "'";

Добре:

String hql = "FROM User WHERE name = :name";

Передача параметра:

query.setParameter("name", "Ivan");
Dirty Checking

Оце дуже важливий механізм Hibernate.

Dirty checking = Hibernate сам помічає зміни в entity.

Тобі не треба писати update вручну.

Приклад:

Transaction tx = session.beginTransaction();

User user = session.get(User.class, 1L);

user.setName("Petro");

tx.commit();

Тут ти НЕ викликав:

update()
save()

Але Hibernate:

пам’ятає початковий стан entity
бачить зміну name
автоматично робить SQL UPDATE

Тобто:

user.setName("Petro");

цього вже достатньо.

Transaction Lifecycle

Transaction = транзакція.

Це “блок операцій”, який:

або виконається весь
або не виконається взагалі

Типовий lifecycle:

1. Початок
   Transaction tx = session.beginTransaction();
2. Робота
   session.persist(user);

або

user.setName("Test");
3. Commit
   tx.commit();

Тоді:

SQL реально піде в БД
зміни збережуться
4. Rollback (якщо помилка)
   tx.rollback();

Тоді зміни скасовуються.

Повний маленький приклад
Session session = factory.openSession();

Transaction tx = session.beginTransaction();

User user = new User();
user.setName("Ivan");

session.persist(user);

tx.commit();

session.close();

Що відбулося:

відкрили session
відкрили transaction
створили entity
зберегли її
commit
Hibernate зробив INSERT
закрили session
Як це все зв’язано в голові

Схема приблизно така:
Entity

Entity — це Java-клас, який Hibernate прив’язує до таблиці в БД.

Тобто:

клас = таблиця
об’єкт = рядок таблиці

Наприклад, є таблиця users.

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String name;

    private int age;
}

Тепер Hibernate розуміє:

User → таблиця users
поле name → колонка name

Створення об’єкта:

User user = new User();
user.setName("Ivan");

після save() Hibernate зробить SQL приблизно таке:

INSERT INTO users (name) VALUES ('Ivan');
SessionFactory

SessionFactory — це “фабрика” для створення Session.

Вона:

читає конфігурацію Hibernate
підключається до БД
зберігає налаштування
створює сесії

Зазвичай одна на весь застосунок.

SessionFactory factory =
new Configuration()
.configure()
.buildSessionFactory();
Session

(Ти не питав окремо, але без цього важко зрозуміти інше.)

Session — це “робоче підключення” до БД.

Через неї:

зберігають
читають
оновлюють
видаляють дані
Session session = factory.openSession();
CRUD

CRUD — 4 базові операції з даними.

Буква	Значення	Hibernate
C	Create	save/persist
R	Read	get/query
U	Update	update/change
D	Delete	delete
CREATE
User user = new User();
user.setName("Oleg");

session.persist(user);
READ
User user = session.get(User.class, 1L);
UPDATE
User user = session.get(User.class, 1L);
user.setName("Max");

Hibernate сам зробить UPDATE при commit.

DELETE
session.remove(user);
JPQL

JPQL = Java Persistence Query Language.

Це мова запитів НЕ до таблиць, а до entity-класів.

SQL:

SELECT * FROM users

JPQL:

FROM User

Тут:

User → клас
не users таблиця

Приклад:

List<User> users =
session.createQuery(
"FROM User WHERE age > 18",
User.class
).list();

Hibernate сам перетворить це у SQL.

Pagination

Pagination = посторінкове завантаження даних.

Наприклад:

не 100000 користувачів одразу
а по 10
List<User> users = session.createQuery(
"FROM User",
User.class
)
.setFirstResult(0) // з якого елемента
.setMaxResults(10) // скільки взяти
.list();

Сторінка 2:

.setFirstResult(10)
.setMaxResults(10)
LIKE

LIKE — пошук по частині тексту.

SQL:

WHERE name LIKE '%an%'

Hibernate JPQL:

FROM User WHERE name LIKE :pattern

Приклад:

List<User> users = session.createQuery(
"FROM User WHERE name LIKE :pattern",
User.class
)
.setParameter("pattern", "%an%")
.list();

Знайде:

Andriy
Ivan
Diana
Parameters

Parameters — це параметри запиту.

Вони потрібні:

для безпеки
щоб не клеїти строки руками
щоб уникати SQL injection

Погано:

String hql = "FROM User WHERE name = '" + name + "'";

Добре:

String hql = "FROM User WHERE name = :name";

Передача параметра:

query.setParameter("name", "Ivan");
Dirty Checking

Оце дуже важливий механізм Hibernate.

Dirty checking = Hibernate сам помічає зміни в entity.

Тобі не треба писати update вручну.

Приклад:

Transaction tx = session.beginTransaction();

User user = session.get(User.class, 1L);

user.setName("Petro");

tx.commit();

Тут ти НЕ викликав:

update()
save()

Але Hibernate:

пам’ятає початковий стан entity
бачить зміну name
автоматично робить SQL UPDATE

Тобто:

user.setName("Petro");

цього вже достатньо.

Transaction Lifecycle

Transaction = транзакція.

Це “блок операцій”, який:

або виконається весь
або не виконається взагалі

Типовий lifecycle:

1. Початок
   Transaction tx = session.beginTransaction();
2. Робота
   session.persist(user);

або

user.setName("Test");
3. Commit
   tx.commit();

Тоді:

SQL реально піде в БД
зміни збережуться
4. Rollback (якщо помилка)
   tx.rollback();

Тоді зміни скасовуються.

Повний маленький приклад
Session session = factory.openSession();

Transaction tx = session.beginTransaction();

User user = new User();
user.setName("Ivan");

session.persist(user);

tx.commit();

session.close();

Що відбулося:

відкрили session
відкрили transaction
створили entity
зберегли її
commit
Hibernate зробив INSERT
закрили session
Як це все зв’язано в голові

Схема приблизно така:

Entity -> Java об'єкт таблиці

SessionFactory
-> створює Session

Session
-> працює з БД

Transaction
-> контролює операції

JPQL
-> мова запитів до entity

Parameters
-> безпечна передача даних

LIKE
-> пошук по тексту

Pagination
-> сторінки даних

Dirty Checking
-> автоматичне відслідковування змін

Для Hibernate dirty checking і lifecycle session/transaction — 
це найважчі, але найважливіші речі. Якщо їх зрозуміти — 
далі Spring Data JPA вже набагато легше заходить.
Entity -> Java об'єкт таблиці

SessionFactory
-> створює Session

Session
-> працює з БД

Transaction
-> контролює операції

JPQL
-> мова запитів до entity

Parameters
-> безпечна передача даних

LIKE
-> пошук по тексту

Pagination
-> сторінки даних

Dirty Checking
-> автоматичне відслідковування змін

Для Hibernate dirty checking і lifecycle session/transaction — це найважчі, але найважливіші речі. Якщо їх зрозуміти — далі Spring Data JPA вже набагато легше заходить.
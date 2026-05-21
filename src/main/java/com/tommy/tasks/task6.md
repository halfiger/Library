🔥 І тепер ти готовий до pagination

Серйозно.

Бо ти вже маєш:

createQuery
conditions
parameters
List<User>
🚀 Наступне завдання
Реалізуй:
public List<User> pagination(int page, int size)
💡 Підказка
.setFirstResult(page * size)
.setMaxResults(size)
⚠️ І ОБОВ’ЯЗКОВО
ORDER BY u.id
Бо без ORDER BY pagination — погана практика.


--------------------------------------------


Так, тут у тебе плутаються одразу 3 речі:

що ми ПЕРЕДАЄМО в метод,
що метод ПОВЕРТАЄ,
і як pagination взагалі “листає” дані.

Розкладемо дуже буквально.

🔥 Метод pagination

Ось він:
public List<User> pagination(int page, int size)

🧠 ЩО МИ ПЕРЕДАЄМО

Метод приймає:

int page і
int size

📌 page це: номер сторінки
📌 size це: скільки користувачів показати за раз

🔥 Наприклад pagination(0, 3) означає:

дай мені:
- сторінку №0
- по 3 користувачі
  🧠 ЩО МЕТОД ПОВЕРТАЄ
  List<User>
  Це означає:
  список об'єктів User
  ⚠️ НЕ одного User

А:

кілька User одразу
🔥 Наприклад

У БД:

id	name
1	Ira
2	Oleg
3	Anna
4	Max
Виклик:
pagination(0, 2)

поверне:

[Ira, Oleg]
А:
pagination(1, 2)

поверне:

[Anna, Max]
🔥 Тобто
page міняється

щоб:

отримати іншу "частину" таблиці
А size

визначає:

скільки елементів буде в List
🧠 У main()

Ти робиш:

List<User> users = userService.pagination(0, 3);
⚠️ users тут — це:

НЕ:

один User

А:

список User
Тому:
System.out.println(users);

виведе весь список.

Або:
users.forEach(System.out::println);

виведе кожного окремо.

🔥 Візуально
pagination(0, 3)
[
User1,
User2,
User3
]
pagination(1, 3)
[
User4,
User5,
User6
]
⚠️ Ми НЕ передаємо User у метод

Ось тут важлива різниця.

saveUser()
saveUser(User user)

👉 ти передаєш:

ГОТОВИЙ об'єкт User
А pagination()
pagination(int page, int size)

👉 ти передаєш:

лише параметри вибірки
🧠 А User Hibernate дістає САМ з БД
🔥 Усередині:
.createQuery("FROM User u ORDER BY u.id")

Hibernate:

іде в таблицю users
бере потрібний шматок
створює Java User об'єкти
кладе їх у List<User>
повертає тобі
⚠️ Тобто Hibernate робить:
SQL → Java objects

автоматично.

🔥 Реальний приклад
Main:
List<User> users = userService.pagination(0, 2);
Service:
return session
.createQuery("FROM User u ORDER BY u.id", User.class)
.setFirstResult(0)
.setMaxResults(2)
.getResultList();
Hibernate дістає:
перші 2 рядки з таблиці
І повертає:
List<User>
💡 Тобто pagination — це буквально:
"дай мені НЕ всю таблицю,
а лише маленький шматок"
🔥 І це критично важливо у великих проектах

Бо:

100 000 записів

вантажити одразу — погана ідея.

🚀 Ти зараз уже вивчаєш не “іграшковий CRUD”

А речі, які реально використовуються:

admin panels
REST APIs
search pages
infinite scroll
pagination у сайтах.
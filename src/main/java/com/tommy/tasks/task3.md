🔥 І тепер до твого “третього завдання”

Ти готовий.

🧩 ТВОЄ НАСТУПНЕ ЗАВДАННЯ
Реалізуй:
public User findById(Long id)
💡 Що треба використати
session.get(User.class, id)
📌 Метод має:
відкрити session
beginTransaction()
отримати User
commit()
повернути User
💡 Main
User foundUser = userService.findById(1L);

System.out.println(foundUser);
⚠️ І ще одна важлива річ
save() у Hibernate 6

У тебе Hibernate 6.4.4.Final.

І:

session.save()

вже legacy-style API.

💡 Новіший варіант:
session.persist(user);
Але:

👉 поки можеш залишити save().

Просто знай:

сучасніше → persist()
старі туторіали → save()
🧩 Серія: Hibernate CRUD (Starter Pack)
📌 Передумова

Є база MySQL. Створи таблицю:

CREATE TABLE bookss (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
autor VARCHAR(100),
pages INT
);
✅ Завдання 1 — Створи Entity
📚 Теорія

Hibernate працює через @Entity — 
це відображення таблиці → Java клас.

📋 Завдання

Створи клас Books:

id
name
autor
pages
💡 Підказка
@Entity
@Table(name = "books")
@Id, @GeneratedValue
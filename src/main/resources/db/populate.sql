INSERT INTO customer (first_name, last_name)
VALUES ('Пётр', 'Иванов'),
       ('Никита', 'Михайлов'),
       ('Михаил', 'Валунов'),
       ('Елизавета', 'Петрова'),
       ('Ольга', 'Григорьева'),
       ('Иван', 'Дуров');

INSERT INTO product (product_name, product_price)
VALUES ('Минеральная вода', 49.99),
       ('Сыр', 120.99),
       ('Хлеб', 30),
       ('Сметана', 65),
       ('Колбаса', 150),
       ('Яйца', 90),
       ('Макароны', 59.99),
       ('Гречка', 68),
       ('Рис', 65),
       ('Молоко', 59.50);

INSERT INTO customer_product(customer_id, product_id, purchase_date)
VALUES (1, 3, CURRENT_DATE - 1),
       (1, 4, CURRENT_DATE - 1),
       (1, 5, CURRENT_DATE - 1),
       (2, 1, CURRENT_DATE - 1),
       (2, 2, CURRENT_DATE - 1),
       (2, 3, CURRENT_DATE - 1),
       (3, 6, CURRENT_DATE - 1),
       (3, 6, CURRENT_DATE),
       (3, 6, CURRENT_DATE),
       (4, 2, CURRENT_DATE),
       (4, 3, CURRENT_DATE),
       (4, 4, CURRENT_DATE),
       (4, 5, CURRENT_DATE),
       (4, 6, CURRENT_DATE),
       (5, 1, CURRENT_DATE),
       (5, 2, CURRENT_DATE),
       (5, 3, CURRENT_DATE),
       (5, 4, CURRENT_DATE),
       (5, 5, CURRENT_DATE),
       (5, 6, CURRENT_DATE),
       (5, 7, CURRENT_DATE),
       (5, 8, CURRENT_DATE),
       (5, 9, CURRENT_DATE),
       (5, 10, CURRENT_DATE);

INSERT INTO customer (first_name, last_name)
VALUES ('Пётр', 'Иванов');
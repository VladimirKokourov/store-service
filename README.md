# Store Service
Приложение, предоставляющее сервис работы с данными в БД. Данный сервис, на основании входных параметров(аргументы командной строки), типа операции и входного файла – извлекает необходимые данные из БД и формирует результат обработки в выходной файл. 
Все возможные ошибки обработаны и зафиксированы в выходном файле.

## Стэк:
- Java 8
- Maven
- PostgreSQL

## Инструкция по запуску:
1. Склонировать или скачать проект
2. Создать базу данных PostgreSQL
3. Заполнить её данными с помощью init.sql и populate.sql в src/main/resources/db/
4. Отредактировать файл config.properties для вашей БД
5. Открыть терминал и перейти к проекту (вы должны зайти в папку)
6. Ввести команду: mvn clean install
7. Выполнить следующую команду:

java -jar ./target/store-service-1.0-SNAPSHOT.jar search src/main/resources/input/input_search.json output.json

или 

java -jar ./target/store-service-1.0-SNAPSHOT.jar stat src/main/resources/input/input_stat.json output.json

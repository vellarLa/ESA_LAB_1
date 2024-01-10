# Лабораторная работа 1 (JavaEE)
## Glassfish
В качестве сервера приложений был использован Oracle GlassFish Server.

Команда для запуска сервера glassfish
```bash
asadmin start-domain domain1
```
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/f3606d98-df46-4f9a-be36-e05db623b66e" width="500">

Команда для остановки сервера glassfish
```bash
asadmin stop-domain domain1
```
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/57f2017a-38f4-4d9f-8655-b9081d7d1fdd" width="500"> 

После запуска сервера по адресу [http://localhost:4848](http://localhost:4848) доступна консоль администратора
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/08d38513-289f-4ad4-93d7-e91a6fca50bd" width="500">

# Для развертывания приложения на сервер glassfish была произведена интеграция glassfish с IntelliJ IDEA
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/8895ef1b-7e36-4c04-a428-078a9bc2067a" width="600">
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/ec7697c7-04bf-42d2-9efb-72c4eefbb802" width="600">

После развертывания ui приложения доступен по ссылкам [http://localhost:8080/javaEE-1.0-SNAPSHOT](http://localhost:8080/javaEE-1.0-SNAPSHOT) и 
![image](https://github.com/vellarLa/ESA_LAB_1/assets/83453185/60814306-1f79-4dbf-8c74-8cf5b6d57546)

## PostgreSQL
В качестве СУБД была использована PostgreSQL. При установке postgres на локальный компьютер была создана локальная база данных, она и использовалась в работе.

Параматры подключения к базе данных прописаны в файле [persistence.xml](https://github.com/vellarLa/ESA_LAB_1/blob/master/src/main/resources/META-INF/persistence.xml).

Параметер
```bash
<property name="jakarta.persistence.schema-generation.database.action" value="create"/>
```
включает автоматическую генерачию таблиц, если таковые еще не были созданы.

## Data layer
Предметная область - кинотеатр. В качестве сущность взяты:
- visitor - посетитель кинотеатра
- film - фильм в прокате
- timetable - расписание (зал, фильм, время)
- ticket - билет

Схема базы данных:
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/59a27b01-a791-420d-b807-d41cc6d41d22" width="500">

Для сокрытия деталей реализации хранения сущностей был смоделирован дополнительный слой DTO.
Для работы с базой данный был смоделирован DAO слой.

## Business layer
Логика приложения реализована в Service классах (Stateless Session Beans)
В бизнес-слое реализована логика поиска свободных мест (ряд и место) с учетом выбора сеанса.

## View layer
За обработку запросов отвечают сервлеты, пользовательский интерфейс реализован на jsp страницах.

### Демонстрация работоспособности
Стартовая страница, если пользователь не в системе. На ней посетитель вводит данные (на кого оформить билет) и указывает, имеет ли потенциальный посетитель какие-то льготы
(от этого зависит стоимость билета (310 - обычная цена; 200 - льготная))

<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/c4bcaebb-4c15-4954-8e69-beb6f75e6985" width="600">

Далее пользователю предоставляется выбор фильмов:

<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/8626d31b-31a4-41ba-8bb9-65ed460924aa" width="600">

После выбора фильма, пользователь может выбрать сеанс, на который хочет купить билет

<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/77c004cf-e239-45e6-874b-e44fbbd769ca" width="600">

После выбора сеанса мы видим список свободных мест

<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/91e30798-402d-48d8-a11b-aaf91cc784b4" width="600">

После покупки билета, эти места исчезают из списка свободных и отображаются по ссылке "my tickets". Из "my tickets" можно удалить билет билет, нажав на "delete"

<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/d408751a-ae66-472b-82dc-fed461b14a78" width="600">
<img src="https://github.com/vellarLa/ESA_LAB_1/assets/83453185/869e2031-9e5f-4a9c-a2b2-97fdcc0c02ea" width="600">
 



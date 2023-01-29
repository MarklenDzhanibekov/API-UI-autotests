Расположение тестов:
•	test.java.api – тесты для API
•	test.java.ui – тесты для UI

•   для удобства создан отдельный тестовый класс для запуска из под junit cucumber-сценария (src/test/java/ui/OpenPlayersListTest.java)

Команда для запуска всех тестов: mvn clean test

Отчеты о тестировании сохранаются в target\surefire-reports

Использованные библиотеки/плагины:
1) Maven Surefire Plugin - использован для запуска и генерации отчетов о тестировании
2) Junit5 – основной тестовы фреймворк
3) Rest-assured – библиотека для тестирования API
4) Gson – библиотека для работы с JSON файлами
5) Selenium – фреймворк для тестирования UI
6) Cucumber – для разработки BDD сценариев.
     
При проектировании тестов был использован паттерн PageObjectModel

Комментарии: 
Selenide намеренно не использован для проверки UI части.

Тестовое задание выполнил – Marklen Dzhanibekov
email: marklen.dzhanibekov@gmail.com
phone: +996 555 053101

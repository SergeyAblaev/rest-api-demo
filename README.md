# 
# For a start application:
1. Startup a docker container with PostgreSQL in 'start_local_env.sh'
2. Running a project in 'RestApiDemoApplication'
   (the configuration file for running is saved in '.run/RestApiDemoApplication.run.xml')

The project has a unit test in 'restrictionApiTest', and an integration test 'RestApiDemoApplicationIT'.

---------------------------------------------------------------------------------------------------
# Initial technical requirements:
- Write a spring-boot application that will contain 1 controller with one method that returns HTTP 200 and an empty body.

- Write a functional that will limit the number of requests from one IP address to this method in the amount of N pieces in X minutes. If the number of requests is greater, then a 502 error code should be returned until the number of requests in the given interval falls below N.

It should be possible to configure these two parameters through the configuration file.
- Make it so that this restriction can be applied quickly to new methods and not only to controllers, but also to methods of service layer classes.

The implementation should take into account the multi-threaded, highly loaded execution environment and consume as few resources as possible (important!).

- Also write a simple JUnit test that will emulate the operation of parallel requests from different IPs.
  !!! Do not use third-party libraries for throttling.

List of technologies and tools:
Code must be written in Java 11 (or higher)
Frameworks: Spring + Spring Boot
Use Gradle to build. Other support libraries are possible.

-Write a JUnit test using JUnit 5.x (Junit Jupiter)
- Write a simple dockerfile to wrap this application in docker

---------------------------------------------------------------------------------------------------
## RUS Translate:

Исходные технические требования:
- Написать spring-boot приложение, которое будет содержать 1 контроллер с одним методом, который возвращает HTTP 200 и пустое тело.

- Написать функционал, который будет ограничивать количество запросов с одного IP адреса на этот метод в размере N штук в X минут. Если количество запросов больше, то должен возвращаться 502 код ошибки, до тех пор, пока количество обращений за заданный интервал не станет ниже N.

Должна быть возможность настройки этих двух параметров через конфигурационный файл.
- Сделать так, чтобы это ограничение можно было применять быстро к новым методам и не только к контроллерам, а также к методам классов сервисного слоя.

Реализация должна учитывать многопоточную высоконагруженную среду исполнения и потреблять как можно меньше ресурсов (важно!).

- Также написать простой JUnit-тест, который будет эмулировать работу параллельных запросов с разных IP.
  !!! Не использовать сторонних библиотек для троттлинга.

Список технологий и инструментов:
Код должен быть описан на Java 11 (или выше)
Фреймворки: Spring + Spring Boot
Для сборки использовать Gradle. Возможны другие вспомогательные библиотеки.

-Написать JUnit тест с использованием JUnit 5.x (Junit Jupiter)
- Написать простой dockerfile для обёртки данного приложения в докер
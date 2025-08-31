Требуемая Java Java 11 (рекомендуется OpenJDK или Eclipse Temurin) Проверка установленной версии:
```
java -version

javac -version
```

1.Перейдите в корень сервиса:
```
cd TZ
```

2. Соберите проект
```
mvn clean package
```

3.Запуск приложения

```
java -jar target/название.jar (после сборки сервиса должно появится в папке таргет)

```

Развертывание Docker:
```

FROM maven:3.9.2-eclipse-temurin-11 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:11-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

Сборка и запуск Docker-образа:

```

docker build -t tz-app .

docker run -p 8080:8080 tz-app
```

В коробке с коробкой созданы все докер-образы, для быстрого запуска, сразу к пункту «Сборка и запуск Docker-образа»

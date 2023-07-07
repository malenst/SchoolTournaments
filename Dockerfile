# Используем образ с поддержкой Java
FROM openjdk:17-alpine

# Установим рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR файл из локальной директории в контейнер
COPY target/SchoolTournaments-0.0.1-SNAPSHOT.jar /app/tours.jar

# Определим команду для запуска вашего приложения
CMD ["java", "-jar", "tours.jar"]
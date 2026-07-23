FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY out/production/UserService .

COPY postgresql-42.7.13.jar .

CMD ["java", "-cp", ".:postgresql-42.7.13.jar", "user.Main"]
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/todo-java.jar /app/todo-java.jar
CMD ["java", "-jar", "/app/todo-java.jar"]

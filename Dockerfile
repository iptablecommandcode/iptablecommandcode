# 베이스: Java 17 런타임 (프로젝트 Java 버전에 맞게)
FROM eclipse-temurin:21-jre

WORKDIR /app

# Jenkins에서 미리 ./gradlew clean build 한 뒤,
# build/libs 안에 생성된 jar 파일을 이미지 안으로 복사
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

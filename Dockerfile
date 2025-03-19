FROM ubuntu:24.04

WORKDIR /app

RUN apt-get update && \
    apt-get install -y git=1:2.43.0-1ubuntu7 default-jdk-headless=2:1.21-75+exp1 --no-install-recommends && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*


COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build -x test && \
    mv build/libs/tasks.war /app/tasks.war

CMD ["java", "-jar", "tasks.war"]
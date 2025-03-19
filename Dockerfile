FROM ubuntu:24.04

WORKDIR /app

RUN apt-get update && \
    apt-get install -y git default-jdk --no-install-recommends && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/*.jar"]
FROM ubuntu:24.04

WORKDIR /app

RUN apt-get update && \
    apt-get install -y git=1.2.43.0-1.ubuntu7.2 default-jdk-headless=2.1.21-75 --no-install-recommends && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build -x test

CMD ["java", "-jar", "build/libs/*.jar"]
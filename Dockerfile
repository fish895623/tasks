FROM openjdk:23

WORKDIR /workspace

COPY . /workspace

RUN ./gradlew build -x test

CMD ["./gradlew", "bootRun"]

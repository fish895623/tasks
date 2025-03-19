FROM openjdk:23

WORKDIR /workspace

COPY . /workspace

RUN chmod +x ./gradlew && \
    ./gradlew build -x test

CMD ["./gradlew", "bootRun"]

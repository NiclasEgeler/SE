FROM sbtscala/scala-sbt:eclipse-temurin-jammy-17.0.5_8_1.8.2_3.2.2
COPY . /app
WORKDIR /app
CMD sbt run
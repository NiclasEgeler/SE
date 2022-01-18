FROM hseeberger/scala-sbt:8u222_1.5.5_3.1.0
RUN apt-get update && apt-get install -y sbt libxrender1 libxtst6 libxi6
COPY . /app
WORKDIR /app
CMD sbt run
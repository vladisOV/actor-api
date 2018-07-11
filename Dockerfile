FROM java:8-jre
MAINTAINER Vladislav Ovcharenko <vlad079@ya.ru>
ADD ./build/libs/actor-api-1.0-SNAPSHOT.jar /app/
CMD ["java", "-jar", "app/actor-api-1.0-SNAPSHOT.jar"]
EXPOSE 8080
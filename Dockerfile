FROM amazoncorretto:11-alpine-jdk
COPY /target/gamma.jar gamma.jar
ENTRYPOINT ["java","-jar","/gamma.jar"]

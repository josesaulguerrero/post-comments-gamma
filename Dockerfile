FROM amazoncorretto:11-alpine-jdk
COPY /target/gamma.jar agamma.jar
ENTRYPOINT ["java","-jar","/alpha.jar"]
